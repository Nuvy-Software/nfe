package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Evento da DC-e processado (procEventoDCe = TProcEvento).
 * Combina o evento enviado e seu retorno autorizado.
 */
@Root(name = "procEventoDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeProcEvento extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Attribute(name = "ipTransmissor", required = false)
    private String ipTransmissor;

    @Attribute(name = "nPortaCon", required = false)
    private String portaConexao;

    @Attribute(name = "dhConexao", required = false)
    private String dataHoraConexao;

    @Element(name = "eventoDCe")
    private DCeEvento evento;

    @Element(name = "retEventoDCe")
    private DCeEventoRetorno retornoEvento;

    public void setEvento(final DCeEvento evento) {
        this.evento = evento;
    }

    public void setRetornoEvento(final DCeEventoRetorno retornoEvento) {
        this.retornoEvento = retornoEvento;
    }

    public String getVersao() {
        return this.versao;
    }

    public DCeEvento getEvento() {
        return this.evento;
    }

    public DCeEventoRetorno getRetornoEvento() {
        return this.retornoEvento;
    }
}
