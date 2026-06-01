package com.fincatto.documentofiscal.dce.classes.emit;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Identificacao do Marketplace (Grupo D - uso exclusivo quando tpEmit=1).
 */
@Root(name = "Marketplace")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeMarketplace extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "CNPJ")
    private String cnpj;

    @Element(name = "xNome")
    private String razaoSocial;

    @Element(name = "Site")
    private String site;

    public void setCnpj(final String cnpj) {
        DFStringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public void setRazaoSocial(final String razaoSocial) {
        DFStringValidador.tamanho2ate60(razaoSocial, "Razao Social do Marketplace");
        this.razaoSocial = razaoSocial;
    }

    public void setSite(final String site) {
        if (site == null || site.length() < 2 || site.length() > 120) {
            throw new IllegalStateException("Site do Marketplace deve ter 2 a 120 caracteres");
        }
        this.site = site;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public String getSite() {
        return this.site;
    }
}
