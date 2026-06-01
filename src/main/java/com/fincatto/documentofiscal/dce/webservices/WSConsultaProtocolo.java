package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.DFLog;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.dce.classes.consulta.DCeConsultaSituacao;
import com.fincatto.documentofiscal.dce.classes.consulta.DCeConsultaSituacaoRetorno;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.apache.axiom.om.OMElement;

/**
 * Cliente do WS DCeConsulta (consulta da situacao atual da DC-e por chave de acesso).
 */
class WSConsultaProtocolo implements DFLog {

    private final DCeConfig config;
    private final DCeWSClient client;

    WSConsultaProtocolo(final DCeConfig config) {
        this.config = config;
        this.client = new DCeWSClient(config);
    }

    DCeConsultaSituacaoRetorno consulta(final String chaveAcesso) throws Exception {
        final DCeConsultaSituacao consulta = new DCeConsultaSituacao();
        consulta.setAmbiente(this.config.getAmbiente());
        consulta.setChaveAcesso(chaveAcesso);

        final String xml = consulta.toString();
        DCeXMLValidador.validaConsultaSituacaoDCe(xml);
        this.getLogger().debug(xml);

        final OMElement resposta = this.client.sendReceive(DCeEndpoint.CONSULTA, xml);
        this.getLogger().debug(resposta.toString());
        return this.config.getPersister().read(DCeConsultaSituacaoRetorno.class, resposta.toString());
    }
}
