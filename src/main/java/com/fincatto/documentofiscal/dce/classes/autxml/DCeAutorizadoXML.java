package com.fincatto.documentofiscal.dce.classes.autxml;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Autorizado a acessar o XML da DC-e (campo autXML, 0 a 10 ocorrencias).
 * Choice entre CNPJ e CPF.
 */
@Root(name = "autXML")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeAutorizadoXML extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    public void setCnpj(final String cnpj) {
        if (this.cpf != null && cnpj != null) {
            throw new IllegalStateException("Nao pode setar CNPJ caso CPF esteja setado");
        }
        DFStringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public void setCpf(final String cpf) {
        if (this.cnpj != null && cpf != null) {
            throw new IllegalStateException("Nao pode setar CPF caso CNPJ esteja setado");
        }
        DFStringValidador.cpf(cpf);
        this.cpf = cpf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }
}
