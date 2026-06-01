package com.fincatto.documentofiscal.dce.classes.infadic;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Campo de uso livre do Fisco (obsFisco, 0..10).
 */
@Root(name = "obsFisco")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeObservacaoFisco extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "xCampo", required = false)
    private String nomeCampo;

    @Element(name = "xTexto")
    private String texto;

    public void setNomeCampo(final String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public void setTexto(final String texto) {
        if (texto == null || texto.length() < 1 || texto.length() > 60) {
            throw new IllegalStateException("xTexto da observacao deve ter 1 a 60 caracteres");
        }
        this.texto = texto;
    }

    public String getNomeCampo() {
        return this.nomeCampo;
    }

    public String getTexto() {
        return this.texto;
    }
}
