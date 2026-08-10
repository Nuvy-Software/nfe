package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Retorno do WS DCeRecepcaoEvento (retEventoDCe = TRetEvento).
 */
@Root(name = "retEventoDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeEventoRetorno extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Element(name = "infEvento")
    private DCeInfoEventoRetorno info;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public String getVersao() {
        return this.versao;
    }

    public DCeInfoEventoRetorno getInfo() {
        return this.info;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }

    public void setInfo(final DCeInfoEventoRetorno info) {
        this.info = info;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }
}
