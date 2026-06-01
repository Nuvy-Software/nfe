package com.fincatto.documentofiscal.dce.classes.statusservico;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.DCeConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Mensagem de entrada do WS DCeStatusServico (consStatServDCe).
 */
@Root(name = "consStatServDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeConsultaStatusServico extends DFBase {

    private static final long serialVersionUID = 1L;
    private static final String SERVICO = "STATUS";

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "cUF")
    private DFUnidadeFederativa codigoUF;

    @Element(name = "xServ")
    private String servico = SERVICO;

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setCodigoUF(final DFUnidadeFederativa codigoUF) {
        this.codigoUF = codigoUF;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public DFUnidadeFederativa getCodigoUF() {
        return this.codigoUF;
    }

    public String getServico() {
        return this.servico;
    }

    public String getVersao() {
        return this.versao;
    }
}
