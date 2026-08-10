package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.DFLog;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEventoRetorno;
import com.fincatto.documentofiscal.utils.DFAssinaturaDigital;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.apache.axiom.om.OMElement;

/**
 * Cliente generico do WS DCeRecepcaoEvento.
 * Assina o evento via DFAssinaturaDigital(infEvento) e envia para o ambiente nacional.
 */
class WSRecepcaoEvento implements DFLog {

    private final DCeConfig config;
    private final DCeWSClient client;

    WSRecepcaoEvento(final DCeConfig config) {
        this.config = config;
        this.client = new DCeWSClient(config);
    }

    DCeEventoRetorno envia(final DCeEvento evento) throws Exception {
        final String xmlAssinado = new DFAssinaturaDigital(this.config).assinarDocumento(evento.toString(), "infEvento");
        return this.enviar(xmlAssinado);
    }

    DCeEventoRetorno enviaAssinado(final String xmlEventoAssinado) throws Exception {
        return this.enviar(xmlEventoAssinado);
    }

    String getXmlAssinado(final DCeEvento evento) throws Exception {
        return new DFAssinaturaDigital(this.config).assinarDocumento(evento.toString(), "infEvento");
    }

    private DCeEventoRetorno enviar(final String xmlAssinado) throws Exception {
        DCeXMLValidador.validaEventoDCe(xmlAssinado);
        this.getLogger().debug(xmlAssinado);

        final OMElement resposta = this.client.sendReceive(DCeEndpoint.RECEPCAO_EVENTO, xmlAssinado);
        this.getLogger().debug(resposta.toString());
        return this.config.getPersister().read(DCeEventoRetorno.class, resposta.toString());
    }
}
