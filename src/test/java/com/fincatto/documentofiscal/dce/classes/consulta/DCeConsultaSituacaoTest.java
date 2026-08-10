package com.fincatto.documentofiscal.dce.classes.consulta;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.junit.Assert;
import org.junit.Test;

public class DCeConsultaSituacaoTest {

    @Test
    public void deveSerializarConsultaSituacao() throws Exception {
        final DCeConsultaSituacao consulta = new DCeConsultaSituacao();
        consulta.setAmbiente(DFAmbiente.HOMOLOGACAO);
        consulta.setChaveAcesso("41250112345678000195990010000000011000123456");
        final String xml = consulta.toString();

        Assert.assertTrue(xml.contains("<consSitDCe versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/dce\"")
                || xml.contains("<consSitDCe xmlns=\"http://www.portalfiscal.inf.br/dce\" versao=\"1.00\""));
        Assert.assertTrue(xml.contains("versao=\"1.00\""));
        Assert.assertTrue(xml.contains("<tpAmb>2</tpAmb>"));
        Assert.assertTrue(xml.contains("<xServ>CONSULTAR</xServ>"));
        Assert.assertTrue(xml.contains("<chDCe>41250112345678000195990010000000011000123456</chDCe>"));

        Assert.assertTrue(DCeXMLValidador.validaConsultaSituacaoDCe(xml));
    }

    /**
     * Com o CNPJ alfanumerico (NT 2026.004) a chave consultada pode conter letras
     * nas posicoes 7-20. O setter deve aceita-la.
     *
     * Nao valida contra o XSD de proposito: o pacote empacotado
     * (PL_DCe_v1.00a_NT2024.001) ainda restringe a chave a [0-9]{44}, e o pacote
     * adequado ao CNPJ alfanumerico ainda nao foi publicado para a DC-e.
     */
    @Test
    public void deveAceitarChaveDeAcessoAlfanumericaNaConsulta() {
        final DCeConsultaSituacao consulta = new DCeConsultaSituacao();
        consulta.setAmbiente(DFAmbiente.HOMOLOGACAO);
        consulta.setChaveAcesso("412501AB12CD34EF5602990010000000011201234564");

        Assert.assertEquals("412501AB12CD34EF5602990010000000011201234564", consulta.getChaveAcesso());
        Assert.assertTrue(consulta.toString()
                .contains("<chDCe>412501AB12CD34EF5602990010000000011201234564</chDCe>"));
    }
}
