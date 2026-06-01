package com.fincatto.documentofiscal.dce.utils;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import com.fincatto.documentofiscal.dce.classes.DCe;
import org.junit.Assert;
import org.junit.Test;

public class DCeGeraChaveTest {

    /**
     * Reproduz o exemplo do Manual de Visao Geral (secao 2.2.4.1):
     * sequencia 5206043300991100250655012000000780026730161 deve produzir DV = 5.
     */
    @Test
    public void deveCalcularDVDoExemploDoManual() {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        final Integer dv = new DCeGeraChave(dce).calcularDigitoVerificador(
                "5206043300991100250655012000000780026730161");
        Assert.assertEquals(Integer.valueOf(5), dv);
    }

    @Test
    public void deveGerarChaveCom44DigitosTerminadaEmDVValido() {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        final String chave = new DCeGeraChave(dce).getChaveAcesso();
        Assert.assertEquals(44, chave.length());
        // DV recalculado deve bater com o ultimo digito
        final String semDV = chave.substring(0, 43);
        final int dvAtual = Integer.parseInt(chave.substring(43, 44));
        Assert.assertEquals(Integer.valueOf(dvAtual),
                new DCeGeraChave(dce).calcularDigitoVerificador(semDV));
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveCalcularDVComTamanhoIncorreto() {
        new DCeGeraChave(FabricaDeObjetosFakeDCe.getDCeCompleta())
                .calcularDigitoVerificador("123");
    }
}
