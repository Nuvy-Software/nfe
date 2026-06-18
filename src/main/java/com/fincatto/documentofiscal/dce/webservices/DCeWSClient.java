package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.utils.MessageContextFactory;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAP12Constants;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

/**
 * Cliente SOAP 1.2 generico para os Web Services da DC-e usando AXIOM puro.
 *
 * <p>Para um XML cru, embrulha-o no elemento {@code dceDadosMsg} (variante
 * descompactada) e invoca o WS via {@code ServiceClient.sendReceive}. Para um
 * elemento ja montado (ex.: {@code dceDadosMsgZip} da autorizacao), envia-o
 * diretamente como corpo, sem re-embrulhar. Nao depende de stubs Axis2 gerados.</p>
 */
class DCeWSClient {

    private static final String DADOS_MSG_LOCAL_NAME = "dceDadosMsg";
    private static final String DADOS_MSG_NS_PREFIX = "dce";

    private final DCeConfig config;

    DCeWSClient(final DCeConfig config) {
        this.config = config;
    }

    /**
     * Invoca o Web Service especificado embrulhando o XML cru no elemento
     * {@code dceDadosMsg} (variante descompactada) e retorna o conteudo da resposta.
     *
     * @param endpoint qual servico chamar
     * @param xmlPayload XML cru do payload (sera embrulhado em dceDadosMsg)
     * @return OMElement com o conteudo da resposta da SEFAZ
     */
    OMElement sendReceive(final DCeEndpoint endpoint, final String xmlPayload) throws Exception {
        final OMFactory factory = OMAbstractFactory.getOMFactory();
        final OMNamespace ns = factory.createOMNamespace(endpoint.getWsdlNamespace(), DADOS_MSG_NS_PREFIX);
        final OMElement dadosMsg = factory.createOMElement(DADOS_MSG_LOCAL_NAME, ns);
        dadosMsg.addChild(AXIOMUtil.stringToOM(xmlPayload));
        return this.sendReceive(endpoint, dadosMsg);
    }

    /**
     * Invoca o Web Service especificado enviando o elemento {@code corpo} ja
     * montado (ex.: {@code dceDadosMsg} ou {@code dceDadosMsgZip}) diretamente
     * como corpo da mensagem, sem re-embrulhar.
     *
     * @param endpoint qual servico chamar
     * @param corpo elemento raiz do corpo (dceDadosMsg/dceDadosMsgZip) ja montado
     * @return OMElement com o conteudo da resposta da SEFAZ
     */
    OMElement sendReceive(final DCeEndpoint endpoint, final OMElement corpo) throws Exception {
        final String url = endpoint.getUrl(this.config.getAmbiente());

        final ServiceClient serviceClient = new ServiceClient();
        try {
            final Options options = serviceClient.getOptions();
            options.setTo(new EndpointReference(url));
            options.setSoapVersionURI(SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
            options.setProperty(org.apache.axis2.transport.http.HTTPConstants.HTTP_PROTOCOL_VERSION,
                    org.apache.axis2.transport.http.HTTPConstants.HEADER_PROTOCOL_11);
            options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
            options.setTimeOutInMilliSeconds(this.config.getTimeoutRequisicaoEmMillis());
            options.setAction(endpoint.getSoapAction());
            serviceClient.setOptions(options);

            // Aplica certificado digital / TLS via DFSocketFactory ja existente
            serviceClient.getServiceContext().setProperty(
                    org.apache.axis2.transport.http.HTTPConstants.CACHED_HTTP_CLIENT,
                    null);
            MessageContextFactory.INSTANCE.create(this.config);

            final OMElement resposta = serviceClient.sendReceive(corpo);
            // SEFAZ-PR envelopa a resposta em <dceXxxResult xmlns=".../wsdl/DCeXxx">
            // contendo <retXxx xmlns="http://www.portalfiscal.inf.br/dce">.
            // Desembrulha o filho para entregar a tag util (retXxx) ao parser.
            final OMElement filho = resposta.getFirstElement();
            return filho != null ? filho : resposta;
        } catch (final AxisFault e) {
            throw new IllegalStateException(
                    "Falha ao invocar Web Service da DC-e (" + endpoint.name() + ", " + url + "): " + e.getMessage(), e);
        } finally {
            try {
                serviceClient.cleanup();
            } catch (final AxisFault ignored) {
                // best-effort cleanup
            }
        }
    }
}
