package com.fincatto.documentofiscal.dce.classes;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Declaracao de Conteudo Eletronica (DC-e) - elemento raiz do leiaute v1.00.
 * Modelo fiscal 99, instituido pelo Ajuste SINIEF 05/2021.
 */
@Root(name = "DCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCe extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "infDCe")
    private DCeInfo info;

    @Element(name = "infSolicDCe", required = false)
    private DCeInfoSolicitacao infoSolicitacao;

    @Element(name = "infDCeSupl", required = false)
    private DCeInfoSuplementar infoSuplementar;

    @Element(name = "Signature", required = false)
    private NFSignature assinatura;

    public void setInfo(final DCeInfo info) {
        this.info = info;
    }

    public void setInfoSolicitacao(final DCeInfoSolicitacao infoSolicitacao) {
        this.infoSolicitacao = infoSolicitacao;
    }

    public void setInfoSuplementar(final DCeInfoSuplementar infoSuplementar) {
        this.infoSuplementar = infoSuplementar;
    }

    public void setAssinatura(final NFSignature assinatura) {
        this.assinatura = assinatura;
    }

    public DCeInfo getInfo() {
        return this.info;
    }

    public DCeInfoSolicitacao getInfoSolicitacao() {
        return this.infoSolicitacao;
    }

    public DCeInfoSuplementar getInfoSuplementar() {
        return this.infoSuplementar;
    }

    public NFSignature getAssinatura() {
        return this.assinatura;
    }
}
