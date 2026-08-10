package com.fincatto.documentofiscal.dce.classes.infadic;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Informacoes Adicionais (Grupo Z - infAdic).
 */
@Root(name = "infAdic")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInformacoesAdicionais extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "infAdFisco", required = false)
    private String informacoesAdicionaisFisco;

    @Element(name = "infCpl", required = false)
    private String informacoesComplementares;

    @Element(name = "infAdMarketplace", required = false)
    private String informacoesAdicionaisMarketplace;

    @Element(name = "infAdECT", required = false)
    private String informacoesAdicionaisECT;

    @ElementList(entry = "obsEmit", inline = true, required = false)
    private List<DCeObservacaoEmitente> observacoesEmitente;

    @ElementList(entry = "obsFisco", inline = true, required = false)
    private List<DCeObservacaoFisco> observacoesFisco;

    @ElementList(entry = "obsMarketplace", inline = true, required = false)
    private List<DCeObservacaoMarketplace> observacoesMarketplace;

    @ElementList(entry = "obsECT", inline = true, required = false)
    private List<DCeObservacaoECT> observacoesECT;

    public void setInformacoesAdicionaisFisco(final String informacoesAdicionaisFisco) {
        if (informacoesAdicionaisFisco != null
                && (informacoesAdicionaisFisco.length() < 1 || informacoesAdicionaisFisco.length() > 2000)) {
            throw new IllegalStateException("infAdFisco deve ter 1 a 2000 caracteres");
        }
        this.informacoesAdicionaisFisco = informacoesAdicionaisFisco;
    }

    public void setInformacoesComplementares(final String informacoesComplementares) {
        if (informacoesComplementares != null
                && (informacoesComplementares.length() < 1 || informacoesComplementares.length() > 5000)) {
            throw new IllegalStateException("infCpl deve ter 1 a 5000 caracteres");
        }
        this.informacoesComplementares = informacoesComplementares;
    }

    public void setInformacoesAdicionaisMarketplace(final String informacoesAdicionaisMarketplace) {
        if (informacoesAdicionaisMarketplace != null
                && (informacoesAdicionaisMarketplace.length() < 1 || informacoesAdicionaisMarketplace.length() > 5000)) {
            throw new IllegalStateException("infAdMarketplace deve ter 1 a 5000 caracteres");
        }
        this.informacoesAdicionaisMarketplace = informacoesAdicionaisMarketplace;
    }

    public void setInformacoesAdicionaisECT(final String informacoesAdicionaisECT) {
        if (informacoesAdicionaisECT != null
                && (informacoesAdicionaisECT.length() < 1 || informacoesAdicionaisECT.length() > 5000)) {
            throw new IllegalStateException("infAdECT deve ter 1 a 5000 caracteres");
        }
        this.informacoesAdicionaisECT = informacoesAdicionaisECT;
    }

    public void setObservacoesEmitente(final List<DCeObservacaoEmitente> observacoesEmitente) {
        if (observacoesEmitente != null && observacoesEmitente.size() > 10) {
            throw new IllegalStateException("obsEmit aceita no maximo 10 ocorrencias");
        }
        this.observacoesEmitente = observacoesEmitente;
    }

    public void setObservacoesFisco(final List<DCeObservacaoFisco> observacoesFisco) {
        if (observacoesFisco != null && observacoesFisco.size() > 10) {
            throw new IllegalStateException("obsFisco aceita no maximo 10 ocorrencias");
        }
        this.observacoesFisco = observacoesFisco;
    }

    public void setObservacoesMarketplace(final List<DCeObservacaoMarketplace> observacoesMarketplace) {
        if (observacoesMarketplace != null && observacoesMarketplace.size() > 10) {
            throw new IllegalStateException("obsMarketplace aceita no maximo 10 ocorrencias");
        }
        this.observacoesMarketplace = observacoesMarketplace;
    }

    public void setObservacoesECT(final List<DCeObservacaoECT> observacoesECT) {
        if (observacoesECT != null && observacoesECT.size() > 10) {
            throw new IllegalStateException("obsECT aceita no maximo 10 ocorrencias");
        }
        this.observacoesECT = observacoesECT;
    }

    public String getInformacoesAdicionaisFisco() {
        return this.informacoesAdicionaisFisco;
    }

    public String getInformacoesComplementares() {
        return this.informacoesComplementares;
    }

    public String getInformacoesAdicionaisMarketplace() {
        return this.informacoesAdicionaisMarketplace;
    }

    public String getInformacoesAdicionaisECT() {
        return this.informacoesAdicionaisECT;
    }

    public List<DCeObservacaoEmitente> getObservacoesEmitente() {
        return this.observacoesEmitente;
    }

    public List<DCeObservacaoFisco> getObservacoesFisco() {
        return this.observacoesFisco;
    }

    public List<DCeObservacaoMarketplace> getObservacoesMarketplace() {
        return this.observacoesMarketplace;
    }

    public List<DCeObservacaoECT> getObservacoesECT() {
        return this.observacoesECT;
    }
}
