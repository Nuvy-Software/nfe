package com.fincatto.documentofiscal.dce.classes.emit;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Identificacao do Fisco (Grupo D - uso exclusivo do Fisco quando tpEmit=0).
 */
@Root(name = "Fisco")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeFisco extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "CNPJ")
    private String cnpj;

    @Element(name = "xOrgao")
    private String nomeOrgao;

    @Element(name = "UF")
    private String uf;

    public void setCnpj(final String cnpj) {
        DFStringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public void setNomeOrgao(final String nomeOrgao) {
        DFStringValidador.tamanho2ate60(nomeOrgao, "Nome do Orgao do Fisco");
        this.nomeOrgao = nomeOrgao;
    }

    public void setUf(final String uf) {
        DFStringValidador.exatamente2(uf, "Sigla da UF do Fisco");
        this.uf = uf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNomeOrgao() {
        return this.nomeOrgao;
    }

    public String getUf() {
        return this.uf;
    }
}
