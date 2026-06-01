package com.fincatto.documentofiscal.dce.classes.detalhamento;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Detalhamento de um item da DC-e (Grupo I - det).
 * O atributo nItem identifica o numero sequencial do item (1 a 999).
 */
@Root(name = "det")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeDetalhamento extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "nItem")
    private String numeroItem;

    @Element(name = "prod")
    private DCeProduto produto;

    @Element(name = "infAdProd", required = false)
    private String informacoesAdicionaisProduto;

    public void setNumeroItem(final String numeroItem) {
        if (numeroItem == null || !numeroItem.matches("[1-9][0-9]{0,3}")) {
            throw new IllegalStateException("nItem deve ser numero entre 1 e 9999");
        }
        this.numeroItem = numeroItem;
    }

    public void setNumeroItem(final int numeroItem) {
        this.setNumeroItem(String.valueOf(numeroItem));
    }

    public void setProduto(final DCeProduto produto) {
        this.produto = produto;
    }

    public void setInformacoesAdicionaisProduto(final String informacoesAdicionaisProduto) {
        if (informacoesAdicionaisProduto != null
                && (informacoesAdicionaisProduto.length() < 1 || informacoesAdicionaisProduto.length() > 500)) {
            throw new IllegalStateException("infAdProd deve ter 1 a 500 caracteres");
        }
        this.informacoesAdicionaisProduto = informacoesAdicionaisProduto;
    }

    public String getNumeroItem() {
        return this.numeroItem;
    }

    public DCeProduto getProduto() {
        return this.produto;
    }

    public String getInformacoesAdicionaisProduto() {
        return this.informacoesAdicionaisProduto;
    }
}
