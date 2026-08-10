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
}
