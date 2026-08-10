package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.evento.cancelamento.DCeEventoCancelamento;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Detalhamento de um evento da DC-e (detEvento dentro de infEvento).
 *
 * <p>O leiaute base usa {@code xs:any} para o conteudo do detEvento, permitindo
 * que tipos especificos de evento sejam inseridos. Atualmente a DC-e v1.00
 * suporta apenas o evento de Cancelamento (evCancDCe).</p>
 */
@Root(name = "detEvento")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeDetalhamentoEvento extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "versaoEvento")
    private String versaoEvento = DCeConfig.VERSAO;

    @Element(name = "evCancDCe", required = false)
    private DCeEventoCancelamento eventoCancelamento;

    public void setEventoCancelamento(final DCeEventoCancelamento eventoCancelamento) {
        this.eventoCancelamento = eventoCancelamento;
    }

    public void setVersaoEvento(final String versaoEvento) {
        this.versaoEvento = versaoEvento;
    }

    public String getVersaoEvento() {
        return this.versaoEvento;
    }

    public DCeEventoCancelamento getEventoCancelamento() {
        return this.eventoCancelamento;
    }
}
