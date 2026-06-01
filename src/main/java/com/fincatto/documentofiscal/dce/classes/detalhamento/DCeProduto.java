package com.fincatto.documentofiscal.dce.classes.detalhamento;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * Dados do Produto, bem ou mercadoria (Grupo H - prod).
 */
@Root(name = "prod")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeProduto extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "xProd")
    private String descricao;

    @Element(name = "NCM")
    private String ncm;

    @Element(name = "qCom")
    private String quantidade;

    @Element(name = "vUnCom")
    private String valorUnitario;

    @Element(name = "vProd")
    private String valorTotalBruto;

    public void setDescricao(final String descricao) {
        if (descricao == null || descricao.length() < 1 || descricao.length() > 120) {
            throw new IllegalStateException("Descricao do Produto deve ter 1 a 120 caracteres");
        }
        this.descricao = descricao;
    }

    public void setNcm(final String ncm) {
        if (ncm == null || !ncm.matches("[0-9]{2}|[0-9]{8}")) {
            throw new IllegalStateException("NCM deve ter 2 ou 8 digitos numericos");
        }
        this.ncm = ncm;
    }

    public void setQuantidade(final BigDecimal quantidade) {
        this.quantidade = DFBigDecimalValidador.tamanho11Com4CasasDecimais(quantidade, "Quantidade Produto DC-e");
    }

    public void setValorUnitario(final BigDecimal valorUnitario) {
        this.valorUnitario = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorUnitario, "Valor Unitario Produto DC-e");
    }

    public void setValorTotalBruto(final BigDecimal valorTotalBruto) {
        this.valorTotalBruto = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorTotalBruto, "Valor Total Bruto Produto DC-e");
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getNcm() {
        return this.ncm;
    }

    public BigDecimal getQuantidade() {
        return this.quantidade == null ? null : new BigDecimal(this.quantidade);
    }

    public BigDecimal getValorUnitario() {
        return this.valorUnitario == null ? null : new BigDecimal(this.valorUnitario);
    }

    public BigDecimal getValorTotalBruto() {
        return this.valorTotalBruto == null ? null : new BigDecimal(this.valorTotalBruto);
    }
}
