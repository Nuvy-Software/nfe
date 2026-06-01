package com.fincatto.documentofiscal.dce.classes.evento.cancelamento;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Evento de Cancelamento da DC-e (evCancDCe). Codigo do evento 110111.
 * Prazo: 24 horas apos a autorizacao (Clausula Decima Primeira do Ajuste SINIEF 05/2021).
 */
@Root(name = "evCancDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeEventoCancelamento extends DFBase {

    private static final long serialVersionUID = 1L;
    public static final String DESCRICAO = "Cancelamento";

    @Element(name = "descEvento")
    private String descricaoEvento = DESCRICAO;

    @Element(name = "nProt")
    private String numeroProtocolo;

    @Element(name = "xJust")
    private String justificativa;

    public void setNumeroProtocolo(final String numeroProtocolo) {
        if (numeroProtocolo == null || !numeroProtocolo.matches("[0-9]{16}")) {
            throw new IllegalStateException("Numero do Protocolo de Autorizacao deve ter 16 digitos numericos");
        }
        this.numeroProtocolo = numeroProtocolo;
    }

    public void setJustificativa(final String justificativa) {
        DFStringValidador.tamanho15a255(justificativa, "Justificativa do Cancelamento");
        this.justificativa = justificativa;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }

    public String getNumeroProtocolo() {
        return this.numeroProtocolo;
    }

    public String getJustificativa() {
        return this.justificativa;
    }
}
