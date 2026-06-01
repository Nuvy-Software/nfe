package com.fincatto.documentofiscal.dce.classes.emit;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.classes.endereco.DCeEnderecoEmitente;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Identificacao do usuario emitente da DC-e (Grupo C - emit).
 * Choice entre CNPJ, CPF e idOutros.
 */
@Root(name = "emit")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeEmitente extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "CNPJ", required = false)
    private String cnpj;

    @Element(name = "CPF", required = false)
    private String cpf;

    @Element(name = "idOutros", required = false)
    private String idOutros;

    @Element(name = "xNome")
    private String razaoSocial;

    @Element(name = "enderEmit")
    private DCeEnderecoEmitente endereco;

    public void setCnpj(final String cnpj) {
        if ((this.cpf != null || this.idOutros != null) && cnpj != null) {
            throw new IllegalStateException("CNPJ exclui CPF/idOutros no Emitente");
        }
        DFStringValidador.cnpj(cnpj);
        this.cnpj = cnpj;
    }

    public void setCpf(final String cpf) {
        if ((this.cnpj != null || this.idOutros != null) && cpf != null) {
            throw new IllegalStateException("CPF exclui CNPJ/idOutros no Emitente");
        }
        DFStringValidador.cpf(cpf);
        this.cpf = cpf;
    }

    public void setIdOutros(final String idOutros) {
        if ((this.cnpj != null || this.cpf != null) && idOutros != null) {
            throw new IllegalStateException("idOutros exclui CNPJ/CPF no Emitente");
        }
        if (idOutros != null && (idOutros.length() < 2 || idOutros.length() > 20)) {
            throw new IllegalStateException("idOutros do Emitente deve ter 2 a 20 caracteres");
        }
        this.idOutros = idOutros;
    }

    public void setRazaoSocial(final String razaoSocial) {
        DFStringValidador.tamanho2ate60(razaoSocial, "Razao Social do Emitente");
        this.razaoSocial = razaoSocial;
    }

    public void setEndereco(final DCeEnderecoEmitente endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getIdOutros() {
        return this.idOutros;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public DCeEnderecoEmitente getEndereco() {
        return this.endereco;
    }
}
