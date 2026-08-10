package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.consulta.DCeConsultaSituacaoRetorno;
import com.fincatto.documentofiscal.dce.classes.envio.DCeEnvioRetornoDados;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEventoRetorno;
import com.fincatto.documentofiscal.dce.classes.evento.cancelamento.DCeCancelamentoParametros;
import com.fincatto.documentofiscal.dce.classes.statusservico.DCeConsultaStatusServicoRetorno;
import com.fincatto.documentofiscal.utils.DFSocketFactory;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * Fachada publica para os Web Services da DC-e (Declaracao de Conteudo Eletronica).
 *
 * <p>Encapsula as 4 operacoes do Ambiente Nacional da DC-e (modelo 99,
 * Ajuste SINIEF 05/2021, autorizador unico hospedado pela SEFAZ-PR):
 * Status do Servico, Autorizacao, Consulta de Situacao e Recepcao de Evento
 * (Cancelamento).</p>
 *
 * <p>Construa uma {@link DCeConfig} com seu certificado A1/A3 e ambiente
 * (Producao/Homologacao) e use os metodos abaixo.</p>
 */
public class WSFacade {

    private final WSStatusServico statusServico;
    private final WSAutorizacao autorizacao;
    private final WSConsultaProtocolo consultaProtocolo;
    private final WSCancelamento cancelamento;

    public WSFacade(final DCeConfig config)
            throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        Protocol.registerProtocol("https", new Protocol("https", new DFSocketFactory(config), 443));

        this.statusServico = new WSStatusServico(config);
        this.autorizacao = new WSAutorizacao(config);
        this.consultaProtocolo = new WSConsultaProtocolo(config);
        this.cancelamento = new WSCancelamento(config);
    }

    /**
     * Consulta o status do servico DC-e.
     */
    public DCeConsultaStatusServicoRetorno consultaStatus() throws Exception {
        return this.statusServico.consultaStatus();
    }

    /**
     * Envia a DC-e para autorizacao no Ambiente Nacional.
     * A DC-e e assinada digitalmente com o certificado configurado em {@link DCeConfig}.
     */
    public DCeEnvioRetornoDados envia(final DCe dce) throws Exception {
        return this.autorizacao.envia(dce);
    }

    /**
     * Envia para autorizacao uma DC-e ja assinada externamente
     * (cenario tipico para certificados A3 que assinam fora do processo Java).
     */
    public DCeEnvioRetornoDados enviaAssinado(final String xmlAssinado) throws Exception {
        return this.autorizacao.enviaAssinado(xmlAssinado);
    }

    /**
     * Consulta a situacao de uma DC-e pela chave de acesso.
     */
    public DCeConsultaSituacaoRetorno consulta(final String chaveAcesso) throws Exception {
        return this.consultaProtocolo.consulta(chaveAcesso);
    }

    /**
     * Cancela uma DC-e pelo evento 110111.
     *
     * <p>Os parametros sao agrupados em {@link DCeCancelamentoParametros}, que
     * valida a xs:choice obrigatoria do XSD (exatamente um entre CNPJUsEmit,
     * CPFUsEmit ou IdOutrosUsEmit) e exige o tipo do emissor da DC-e original
     * para preencher corretamente o campo tpEmit do evento.</p>
     */
    public DCeEventoRetorno cancela(final DCeCancelamentoParametros params) throws Exception {
        return this.cancelamento.cancela(params);
    }

    /**
     * Envia um evento de cancelamento ja assinado externamente.
     */
    public DCeEventoRetorno cancelaAssinado(final String xmlEventoAssinado) throws Exception {
        return this.cancelamento.cancelaAssinado(xmlEventoAssinado);
    }

    /**
     * Retorna o XML assinado do evento de cancelamento, sem transmitir para a SEFAZ.
     * Util quando a transmissao sera feita por outro processo (ex.: integrador).
     */
    public String getXmlAssinadoCancelamento(final DCeCancelamentoParametros params) throws Exception {
        return this.cancelamento.getXmlAssinadoCancelamento(params);
    }
}
