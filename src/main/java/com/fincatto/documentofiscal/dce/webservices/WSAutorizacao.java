package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.DFLog;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.envio.DCeEnvioRetorno;
import com.fincatto.documentofiscal.dce.classes.envio.DCeEnvioRetornoDados;
import com.fincatto.documentofiscal.utils.DFAssinaturaDigital;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

/**
 * Cliente do WS DCeAutorizacao. Recebe a DC-e nao assinada,
 * aplica a assinatura digital, valida contra o XSD oficial,
 * compacta em GZip+Base64 (conforme variante DCeAutorizacaoZip)
 * e envia para o ambiente nacional.
 */
class WSAutorizacao implements DFLog {

    private static final String DADOS_MSG_ZIP_LOCAL = "dceDadosMsgZip";

    private final DCeConfig config;
    private final DCeWSClient client;

    WSAutorizacao(final DCeConfig config) {
        this.config = config;
        this.client = new DCeWSClient(config);
    }

    DCeEnvioRetornoDados envia(final DCe dce) throws Exception {
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(dce.toString(), "infDCe");
        final DCe dceAssinada = this.config.getPersister().read(DCe.class, xmlAssinado);
        return this.enviar(xmlAssinado, dceAssinada);
    }

    DCeEnvioRetornoDados enviaAssinado(final String xmlAssinado) throws Exception {
        final DCe dceAssinada = this.config.getPersister().read(DCe.class, xmlAssinado);
        return this.enviar(xmlAssinado, dceAssinada);
    }

    private DCeEnvioRetornoDados enviar(final String xmlAssinado, final DCe dceAssinada) throws Exception {
        DCeXMLValidador.validaDCe(xmlAssinado);
        this.getLogger().debug(xmlAssinado);

        final String conteudoCompactado = compactarGZipBase64(xmlAssinado);

        // Envelope da variante compactada: <dceDadosMsgZip>BASE64_GZIP</dceDadosMsgZip>
        final OMFactory factory = OMAbstractFactory.getOMFactory();
        final OMNamespace ns = factory.createOMNamespace(
                DCeEndpoint.AUTORIZACAO.getWsdlNamespace(), "dce");
        final OMElement dadosMsgZip = factory.createOMElement(DADOS_MSG_ZIP_LOCAL, ns);
        dadosMsgZip.setText(conteudoCompactado);

        final OMElement resposta = this.client.sendReceive(DCeEndpoint.AUTORIZACAO, dadosMsgZip);
        this.getLogger().debug(resposta.toString());
        final DCeEnvioRetorno retorno = this.config.getPersister().read(
                DCeEnvioRetorno.class, resposta.toString());
        return new DCeEnvioRetornoDados(retorno, dceAssinada);
    }

    private static String compactarGZipBase64(final String xml) throws Exception {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                gzipOutputStream.write(xml.getBytes(StandardCharsets.UTF_8));
            }
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        }
    }
}
