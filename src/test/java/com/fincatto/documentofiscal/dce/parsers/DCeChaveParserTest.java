package com.fincatto.documentofiscal.dce.parsers;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;
import org.junit.Assert;
import org.junit.Test;

public class DCeChaveParserTest {

    @Test
    public void deveDecomporChavePadrao() {
        // chave hipotetica decomposta posicao a posicao:
        // cUF(2)=41 PR | AAMM(4)=2501 | CNPJ(14)=12345678000195 | mod(2)=99
        // serie(3)=001 | nDC(9)=000000001 | tpEmis(1)=1 | tpEmit(1)=2
        // nSiteAutoriz(1)=0 | cDC(6)=123456 | DV(1)=4
        final DCeChaveParser parser = new DCeChaveParser("41250112345678000195990010000000011201234564");
        Assert.assertEquals(DFUnidadeFederativa.PR, parser.getNFUnidadeFederativa());
        Assert.assertEquals("2025-01", parser.getAnoMesEmissao().toString());
        Assert.assertEquals("12345678000195", parser.getCnpjOuCpfEmitente());
        Assert.assertEquals(DFModelo.DCE, parser.getModelo());
        Assert.assertEquals("001", parser.getSerie());
        Assert.assertEquals("000000001", parser.getNumero());
        Assert.assertEquals(DCeTipoEmissao.NORMAL, parser.getTipoEmissao());
        Assert.assertEquals(DCeTipoEmitente.EMISSOR_PROPRIO, parser.getTipoEmitente());
        Assert.assertEquals("0", parser.getNumeroSiteAutorizador());
        Assert.assertEquals("123456", parser.getCodigoNumerico());
        Assert.assertEquals("4", parser.getDigitoVerificador());
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarTamanhoIncorreto() {
        new DCeChaveParser("123");
    }

    /**
     * A partir do CNPJ alfanumerico (NT 2026.004) a chave da DC-e pode conter letras
     * nas posicoes 7-20. O parser trabalha por substring e deve permanecer agnostico.
     */
    @Test
    public void deveDecomporChaveComCnpjAlfanumerico() {
        // mesma chave do teste padrao, com o CNPJ substituido por AB12CD34EF5602
        final DCeChaveParser parser = new DCeChaveParser("412501AB12CD34EF5602990010000000011201234564");
        Assert.assertEquals(DFUnidadeFederativa.PR, parser.getNFUnidadeFederativa());
        Assert.assertEquals("2025-01", parser.getAnoMesEmissao().toString());
        Assert.assertEquals("AB12CD34EF5602", parser.getCnpjOuCpfEmitente());
        Assert.assertEquals(DFModelo.DCE, parser.getModelo());
        Assert.assertEquals("001", parser.getSerie());
        Assert.assertEquals("000000001", parser.getNumero());
        Assert.assertEquals(DCeTipoEmissao.NORMAL, parser.getTipoEmissao());
        Assert.assertEquals(DCeTipoEmitente.EMISSOR_PROPRIO, parser.getTipoEmitente());
        Assert.assertEquals("0", parser.getNumeroSiteAutorizador());
        Assert.assertEquals("123456", parser.getCodigoNumerico());
        Assert.assertEquals("4", parser.getDigitoVerificador());
    }
}
