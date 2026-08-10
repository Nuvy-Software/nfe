package com.fincatto.documentofiscal.dce.classes;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.autxml.DCeAutorizadoXML;
import com.fincatto.documentofiscal.dce.classes.declaracao.DCeDeclaracaoEmitente;
import com.fincatto.documentofiscal.dce.classes.dest.DCeDestinatario;
import com.fincatto.documentofiscal.dce.classes.detalhamento.DCeDetalhamento;
import com.fincatto.documentofiscal.dce.classes.emit.DCeECT;
import com.fincatto.documentofiscal.dce.classes.emit.DCeEmitente;
import com.fincatto.documentofiscal.dce.classes.emit.DCeFisco;
import com.fincatto.documentofiscal.dce.classes.emit.DCeMarketplace;
import com.fincatto.documentofiscal.dce.classes.emit.DCeTransportadora;
import com.fincatto.documentofiscal.dce.classes.ide.DCeIdentificacao;
import com.fincatto.documentofiscal.dce.classes.infadic.DCeInformacoesAdicionais;
import com.fincatto.documentofiscal.dce.classes.total.DCeTotal;
import com.fincatto.documentofiscal.dce.classes.transporte.DCeTransporte;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Informacoes da DC-e (infDCe). Elemento principal do leiaute, contendo o
 * atributo Id que sera assinado.
 */
@Root(name = "infDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInfo extends DFBase {

    private static final long serialVersionUID = 1L;
    public static final String IDENT = "DCe";

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Attribute(name = "Id")
    private String identificador;

    @Element(name = "ide")
    private DCeIdentificacao identificacao;

    @Element(name = "emit")
    private DCeEmitente emitente;

    @Element(name = "Fisco", required = false)
    private DCeFisco fisco;

    @Element(name = "Marketplace", required = false)
    private DCeMarketplace marketplace;

    @Element(name = "Transportadora", required = false)
    private DCeTransportadora transportadora;

    @Element(name = "ECT", required = false)
    private DCeECT ect;

    @Element(name = "dest")
    private DCeDestinatario destinatario;

    @ElementList(entry = "autXML", inline = true, required = false)
    private List<DCeAutorizadoXML> autorizadosXML;

    @ElementList(entry = "det", inline = true)
    private List<DCeDetalhamento> detalhamentos;

    @Element(name = "total")
    private DCeTotal total;

    @Element(name = "transp")
    private DCeTransporte transporte;

    @Element(name = "infAdic", required = false)
    private DCeInformacoesAdicionais informacoesAdicionais;

    @Element(name = "infDec")
    private DCeDeclaracaoEmitente declaracao;

    public void setIdentificador(final String identificador) {
        DFStringValidador.exatamente44N(identificador, "Identificador da DC-e");
        this.identificador = DCeInfo.IDENT + identificador;
    }

    public String getChaveAcesso() {
        return this.identificador == null ? null : this.identificador.replace(DCeInfo.IDENT, "");
    }

    public void setVersao(final String versao) {
        DFStringValidador.equals(DCeConfig.VERSAO, versao);
        this.versao = versao;
    }

    public void setIdentificacao(final DCeIdentificacao identificacao) {
        this.identificacao = identificacao;
    }

    public void setEmitente(final DCeEmitente emitente) {
        this.emitente = emitente;
    }

    public void setFisco(final DCeFisco fisco) {
        validarChoiceAutor();
        this.fisco = fisco;
    }

    public void setMarketplace(final DCeMarketplace marketplace) {
        validarChoiceAutor();
        this.marketplace = marketplace;
    }

    public void setTransportadora(final DCeTransportadora transportadora) {
        validarChoiceAutor();
        this.transportadora = transportadora;
    }

    public void setEct(final DCeECT ect) {
        validarChoiceAutor();
        this.ect = ect;
    }

    private void validarChoiceAutor() {
        int presentes = 0;
        if (this.fisco != null) presentes++;
        if (this.marketplace != null) presentes++;
        if (this.transportadora != null) presentes++;
        if (this.ect != null) presentes++;
        if (presentes > 0) {
            throw new IllegalStateException(
                    "Apenas um entre Fisco, Marketplace, Transportadora ou ECT pode estar presente");
        }
    }

    public void setDestinatario(final DCeDestinatario destinatario) {
        this.destinatario = destinatario;
    }

    public void setAutorizadosXML(final List<DCeAutorizadoXML> autorizadosXML) {
        if (autorizadosXML != null && autorizadosXML.size() > 10) {
            throw new IllegalStateException("autXML aceita no maximo 10 ocorrencias");
        }
        this.autorizadosXML = autorizadosXML;
    }

    public void setDetalhamentos(final List<DCeDetalhamento> detalhamentos) {
        if (detalhamentos == null || detalhamentos.isEmpty() || detalhamentos.size() > 999) {
            throw new IllegalStateException("det deve ter de 1 a 999 ocorrencias");
        }
        this.detalhamentos = detalhamentos;
    }

    public void setTotal(final DCeTotal total) {
        this.total = total;
    }

    public void setTransporte(final DCeTransporte transporte) {
        this.transporte = transporte;
    }

    public void setInformacoesAdicionais(final DCeInformacoesAdicionais informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public void setDeclaracao(final DCeDeclaracaoEmitente declaracao) {
        this.declaracao = declaracao;
    }

    public String getVersao() {
        return this.versao;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public DCeIdentificacao getIdentificacao() {
        return this.identificacao;
    }

    public DCeEmitente getEmitente() {
        return this.emitente;
    }

    public DCeFisco getFisco() {
        return this.fisco;
    }

    public DCeMarketplace getMarketplace() {
        return this.marketplace;
    }

    public DCeTransportadora getTransportadora() {
        return this.transportadora;
    }

    public DCeECT getEct() {
        return this.ect;
    }

    public DCeDestinatario getDestinatario() {
        return this.destinatario;
    }

    public List<DCeAutorizadoXML> getAutorizadosXML() {
        return this.autorizadosXML;
    }

    public List<DCeDetalhamento> getDetalhamentos() {
        return this.detalhamentos;
    }

    public DCeTotal getTotal() {
        return this.total;
    }

    public DCeTransporte getTransporte() {
        return this.transporte;
    }

    public DCeInformacoesAdicionais getInformacoesAdicionais() {
        return this.informacoesAdicionais;
    }

    public DCeDeclaracaoEmitente getDeclaracao() {
        return this.declaracao;
    }
}
