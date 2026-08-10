package com.fincatto.documentofiscal.dce.classes.declaracao;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Declaracao do usuario emitente (Grupo ZA - infDec).
 * Os campos xObs1 e xObs2 sao textos legais fixos exigidos pelo Ajuste SINIEF 05/2021.
 */
@Root(name = "infDec")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeDeclaracaoEmitente extends DFBase {

    private static final long serialVersionUID = 1L;

    /**
     * Texto fixo obrigatorio para xObs1 da DC-e, conforme Anexo I.
     */
    public static final String TEXTO_OBS1 = "E contribuinte de ICMS qualquer pessoa fisica ou juridica, "
            + "que realize, com habitualidade ou em volume que caracterize intuito comercial, "
            + "operacoes de circulacao de mercadoria ou prestacoes de servicos de transportes "
            + "interestadual e intermunicipal e de comunicacao, ainda que as operacoes e prestacoes "
            + "de iniciem no exterior (Lei Complementar n 87/96, Art. 4).";

    /**
     * Texto fixo obrigatorio para xObs2 da DC-e, conforme Anexo I.
     */
    public static final String TEXTO_OBS2 = "Constitui crime contra a ordem tributaria suprimir ou reduzir "
            + "tributo, ou contribuicao social e qualquer acessorio: quando negar ou deixar de fornecer, "
            + "quando obrigatorio, nota fiscal ou documento equivalente, relativa a venda de mercadoria "
            + "ou prestacao de servico, efetivamente realizada ou fornece-la em desacordo com a legislacao. "
            + "Sob pena de reclusao de 2 (dois) e 5 (cinco) anos, e multa (Lei 8.137/90, Art 1, V).";

    @Element(name = "xObs1")
    private String observacao1 = TEXTO_OBS1;

    @Element(name = "xObs2")
    private String observacao2 = TEXTO_OBS2;

    public void setObservacao1(final String observacao1) {
        if (observacao1 == null || observacao1.length() < 1 || observacao1.length() > 2000) {
            throw new IllegalStateException("xObs1 deve ter 1 a 2000 caracteres");
        }
        this.observacao1 = observacao1;
    }

    public void setObservacao2(final String observacao2) {
        if (observacao2 == null || observacao2.length() < 1 || observacao2.length() > 5000) {
            throw new IllegalStateException("xObs2 deve ter 1 a 5000 caracteres");
        }
        this.observacao2 = observacao2;
    }

    public String getObservacao1() {
        return this.observacao1;
    }

    public String getObservacao2() {
        return this.observacao2;
    }
}
