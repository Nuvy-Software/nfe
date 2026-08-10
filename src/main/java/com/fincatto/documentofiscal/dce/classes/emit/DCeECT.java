package com.fincatto.documentofiscal.dce.classes.emit;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Identificacao dos Correios - ECT (Grupo D - uso exclusivo quando tpEmit=4).
 */
@Root(name = "ECT")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeECT extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "CNPJ")
    private String cnpj;

    @Element(name = "xNome")
    private String razaoSocial;

    public void setCnpj(final String cnpj) {
        DFStringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public void setRazaoSocial(final String razaoSocial) {
        DFStringValidador.tamanho2ate60(razaoSocial, "Razao Social da ECT");
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }
}
