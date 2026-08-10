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

    /**
     * A partir do CNPJ alfanumerico (NT 2026.004) as posicoes 7-20 da chave de acesso
     * podem conter letras. O DV usa a conversao por valor de char (ASCII - 48), que
     * atribui 17-42 para as letras A-Z, assim como no MDF-e.
     */
    @Test
    public void deveGerarChaveComCnpjAlfanumericoDoEmitente() {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        dce.getInfo().getEmitente().setCnpj("AB12CD34EF5602");

        final DCeGeraChave geraChave = new DCeGeraChave(dce);
        final String chave = geraChave.getChaveAcesso();

        Assert.assertEquals(44, chave.length());
        Assert.assertTrue("chave deve conter o CNPJ alfanumerico do emitente",
                chave.contains("AB12CD34EF5602"));
        // o DV calculado pela geracao deve bater com o ultimo digito da propria chave
        Assert.assertEquals(chave.substring(43, 44), String.valueOf(geraChave.getDV()));
    }

    @Test
    public void deveCalcularDVComChaveContendoLetras() {
        // cUF(2)=41 | AAMM(4)=2501 | CNPJ(14)=AB12CD34EF5602 | mod(2)=99 | serie(3)=001
        // nDC(9)=000000001 | tpEmis(1)=1 | tpEmit(1)=2 | nSiteAutoriz(1)=0 | cDC(6)=123456
        final String semDV = "41" + "2501" + "AB12CD34EF5602" + "99" + "001" + "000000001"
                + "1" + "2" + "0" + "123456";
        Assert.assertEquals(43, semDV.length());

        final Integer dv = new DCeGeraChave(FabricaDeObjetosFakeDCe.getDCeCompleta())
                .calcularDigitoVerificador(semDV);

        Assert.assertNotNull(dv);
        Assert.assertTrue("DV deve estar entre 0 e 9", dv >= 0 && dv <= 9);
    }

    @Test
    public void devePreencherIdentificadorComCnpjAlfanumerico() {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        dce.getInfo().getEmitente().setCnpj("AB12CD34EF5602");

        final String chave = new DCeGeraChave(dce).preencher();

        // getIdentificador() devolve a chave prefixada por "DCe"; getChaveAcesso() remove o prefixo
        Assert.assertEquals(chave, dce.getInfo().getChaveAcesso());
        Assert.assertEquals("DCe" + chave, dce.getInfo().getIdentificador());
        Assert.assertEquals(chave.substring(43, 44),
                dce.getInfo().getIdentificacao().getDigitoVerificador());
    }
}
