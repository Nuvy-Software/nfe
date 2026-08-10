package com.fincatto.documentofiscal.dce.classes.endereco;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Endereco do destinatario / assinante da DC-e. Tipo TEndeDest.
 * Diferentemente do emitente, aceita qualquer pais e email opcional.
 */
@Root(name = "enderDest")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeEnderecoDestinatario extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "xLgr")
    private String logradouro;

    @Element(name = "nro")
    private String numero;

    @Element(name = "xCpl", required = false)
    private String complemento;

    @Element(name = "xBairro")
    private String bairro;

    @Element(name = "cMun")
    private String codigoMunicipio;

    @Element(name = "xMun")
    private String descricaoMunicipio;

    @Element(name = "UF")
    private String uf;

    @Element(name = "CEP")
    private String cep;

    @Element(name = "cPais")
    private String codigoPais;

    @Element(name = "xPais")
    private String descricaoPais;

    @Element(name = "fone", required = false)
    private String telefone;

    @Element(name = "email", required = false)
    private String email;

    public void setLogradouro(final String logradouro) {
        DFStringValidador.tamanho2ate60(logradouro, "Logradouro do Destinatario");
        this.logradouro = logradouro;
    }

    public void setNumero(final String numero) {
        DFStringValidador.tamanho60(numero, "Numero do Destinatario");
        this.numero = numero;
    }

    public void setComplemento(final String complemento) {
        DFStringValidador.tamanho60(complemento, "Complemento do Destinatario");
        this.complemento = complemento;
    }

    public void setBairro(final String bairro) {
        DFStringValidador.tamanho2ate60(bairro, "Bairro do Destinatario");
        this.bairro = bairro;
    }

    public void setCodigoMunicipio(final String codigoMunicipio) {
        DFStringValidador.exatamente7N(codigoMunicipio, "Codigo do Municipio do Destinatario");
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setDescricaoMunicipio(final String descricaoMunicipio) {
        DFStringValidador.tamanho2ate60(descricaoMunicipio, "Descricao do Municipio do Destinatario");
        this.descricaoMunicipio = descricaoMunicipio;
    }

    public void setUf(final String uf) {
        DFStringValidador.exatamente2(uf, "Sigla da UF do Destinatario");
        this.uf = uf;
    }

    public void setCep(final String cep) {
        DFStringValidador.exatamente8N(cep, "CEP do Destinatario");
        this.cep = cep;
    }

    public void setCodigoPais(final String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public void setDescricaoPais(final String descricaoPais) {
        DFStringValidador.tamanho2ate60(descricaoPais, "Descricao do Pais do Destinatario");
        this.descricaoPais = descricaoPais;
    }

    public void setTelefone(final String telefone) {
        DFStringValidador.telefone(telefone);
        this.telefone = telefone;
    }

    public void setEmail(final String email) {
        DFStringValidador.email(email);
        this.email = email;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getCodigoMunicipio() {
        return this.codigoMunicipio;
    }

    public String getDescricaoMunicipio() {
        return this.descricaoMunicipio;
    }

    public String getUf() {
        return this.uf;
    }

    public String getCep() {
        return this.cep;
    }

    public String getCodigoPais() {
        return this.codigoPais;
    }

    public String getDescricaoPais() {
        return this.descricaoPais;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }
}
