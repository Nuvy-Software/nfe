package com.fincatto.documentofiscal.dce.webservices;

import com.fincatto.documentofiscal.DFLog;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.evento.DCeDetalhamentoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEventoRetorno;
import com.fincatto.documentofiscal.dce.classes.evento.DCeInfoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeTipoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.cancelamento.DCeCancelamentoParametros;
import com.fincatto.documentofiscal.dce.classes.evento.cancelamento.DCeEventoCancelamento;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;
import com.fincatto.documentofiscal.dce.parsers.DCeChaveParser;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;

/**
 * Wrapper que monta o evento de cancelamento (tpEvento=110111) e delega a
 * transmissao para {@link WSRecepcaoEvento}.
 */
class WSCancelamento implements DFLog {

    private final DCeConfig config;
    private final WSRecepcaoEvento recepcaoEvento;

    WSCancelamento(final DCeConfig config) {
        this.config = config;
        this.recepcaoEvento = new WSRecepcaoEvento(config);
    }

    DCeEventoRetorno cancela(final DCeCancelamentoParametros params) throws Exception {
        final DCeEvento evento = montaEvento(params);
        return this.recepcaoEvento.envia(evento);
    }

    DCeEventoRetorno cancelaAssinado(final String xmlEventoAssinado) throws Exception {
        return this.recepcaoEvento.enviaAssinado(xmlEventoAssinado);
    }

    String getXmlAssinadoCancelamento(final DCeCancelamentoParametros params) throws Exception {
        final DCeEvento evento = montaEvento(params);
        return this.recepcaoEvento.getXmlAssinado(evento);
    }

    private DCeEvento montaEvento(final DCeCancelamentoParametros p) {
        final DCeEventoCancelamento canc = new DCeEventoCancelamento();
        canc.setNumeroProtocolo(p.getNumeroProtocolo());
        canc.setJustificativa(p.getJustificativa());

        final DCeDetalhamentoEvento det = new DCeDetalhamentoEvento();
        det.setEventoCancelamento(canc);

        final DCeChaveParser parser = new DCeChaveParser(p.getChaveAcesso());
        final int sequencial = 1;

        final DCeInfoEvento info = new DCeInfoEvento();
        info.setCodigoOrgao(Integer.parseInt(parser.getNFUnidadeFederativa().getCodigoIbge()));
        info.setAmbiente(this.config.getAmbiente());
        info.setTipoEmitente(mapearTpEmitParaEvento(p.getTipoEmitente()));
        info.setCnpjAutor(p.getCnpjAutor());
        if (p.getCnpjUsuarioEmitente() != null) {
            info.setCnpjUsuarioEmitente(p.getCnpjUsuarioEmitente());
        } else if (p.getCpfUsuarioEmitente() != null) {
            info.setCpfUsuarioEmitente(p.getCpfUsuarioEmitente());
        } else {
            info.setIdOutrosUsuarioEmitente(p.getIdOutrosUsuarioEmitente());
        }
        info.setChaveAcesso(p.getChaveAcesso());
        info.setDataHoraEvento(ZonedDateTime.now(this.config.getTimeZone().toZoneId()));
        info.setTipoEvento(DCeTipoEvento.CANCELAMENTO);
        info.setNumeroSequencialEvento(sequencial);
        info.setDetalhamentoEvento(det);
        info.setIdentificador(DCeTipoEvento.CANCELAMENTO.getCodigo() + p.getChaveAcesso()
                + StringUtils.leftPad(String.valueOf(sequencial), 3, "0"));

        final DCeEvento evento = new DCeEvento();
        evento.setInfo(info);
        return evento;
    }

    /**
     * Mapeia o tipo de emitente da DC-e (0..4) para o subset aceito pelo
     * evento (XSD: [0-3], onde Transportadora e ECT compartilham o codigo 3).
     */
    private static String mapearTpEmitParaEvento(final DCeTipoEmitente tipo) {
        switch (tipo) {
            case FISCO:
                return "0";
            case MARKETPLACE:
                return "1";
            case EMISSOR_PROPRIO:
                return "2";
            case TRANSPORTADORA:
            case ECT:
                return "3";
            default:
                throw new IllegalStateException("DCeTipoEmitente inesperado: " + tipo);
        }
    }
}
