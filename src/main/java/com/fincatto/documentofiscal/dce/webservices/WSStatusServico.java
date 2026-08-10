package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.DFLog;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.dce.classes.statusservico.DCeConsultaStatusServico;
import com.fincatto.documentofiscal.dce.classes.statusservico.DCeConsultaStatusServicoRetorno;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.apache.axiom.om.OMElement;

/**
 * Cliente do WS DCeStatusServico.
 */
class WSStatusServico implements DFLog {

    private final DCeConfig config;
    private final DCeWSClient client;

    WSStatusServico(final DCeConfig config) {
        this.config = config;
        this.client = new DCeWSClient(config);
    }

    DCeConsultaStatusServicoRetorno consultaStatus() throws Exception {
        final DCeConsultaStatusServico consulta = new DCeConsultaStatusServico();
        consulta.setAmbiente(this.config.getAmbiente());
        consulta.setCodigoUF(this.config.getCUF());

        final String xml = consulta.toString();
        DCeXMLValidador.validaStatusServicoDCe(xml);
        this.getLogger().debug(xml);

        final OMElement resposta = this.client.sendReceive(DCeEndpoint.STATUS_SERVICO, xml);
        this.getLogger().debug(resposta.toString());
        return this.config.getPersister().read(DCeConsultaStatusServicoRetorno.class, resposta.toString());
    }
}
