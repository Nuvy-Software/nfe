package com.fincatto.documentofiscal.dce.classes.total;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * Dados dos totais da DC-e (Grupo W - total).
 */
@Root(name = "total")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeTotal extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "vDC")
    private String valorTotalDeclaracao;

    public void setValorTotalDeclaracao(final BigDecimal valorTotalDeclaracao) {
        this.valorTotalDeclaracao = DFBigDecimalValidador.tamanho13Com2CasasDecimais(valorTotalDeclaracao, "Valor Total Declaracao DC-e");
    }

    public BigDecimal getValorTotalDeclaracao() {
        return this.valorTotalDeclaracao == null ? null : new BigDecimal(this.valorTotalDeclaracao);
    }
}
