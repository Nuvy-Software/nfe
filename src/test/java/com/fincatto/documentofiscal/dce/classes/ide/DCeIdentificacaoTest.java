package com.fincatto.documentofiscal.dce.classes.ide;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import org.junit.Assert;
import org.junit.Test;

public class DCeIdentificacaoTest {

    @Test
    public void deveSerializarIdentificacao() {
        final String xml = FabricaDeObjetosFakeDCe.getIdentificacao().toString();
        Assert.assertTrue(xml.contains("<ide xmlns=\"http://www.portalfiscal.inf.br/dce\">"));
        Assert.assertTrue(xml.contains("<cUF>41</cUF>"));
        Assert.assertTrue(xml.contains("<cDC>123456</cDC>"));
        Assert.assertTrue(xml.contains("<mod>99</mod>"));
        Assert.assertTrue(xml.contains("<serie>1</serie>"));
        Assert.assertTrue(xml.contains("<nDC>1</nDC>"));
        Assert.assertTrue(xml.contains("<tpEmis>1</tpEmis>"));
        Assert.assertTrue(xml.contains("<tpEmit>2</tpEmit>"));
        Assert.assertTrue(xml.contains("<nSiteAutoriz>0</nSiteAutoriz>"));
        Assert.assertTrue(xml.contains("<cDV>5</cDV>"));
        Assert.assertTrue(xml.contains("<tpAmb>2</tpAmb>"));
        Assert.assertTrue(xml.contains("<verProc>nuvy-1.0</verProc>"));
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarSerieInvalida() {
        final DCeIdentificacao ide = new DCeIdentificacao();
        ide.setSerie("9999"); // > 3 digitos
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarNumeroInvalido() {
        final DCeIdentificacao ide = new DCeIdentificacao();
        ide.setNumero("0"); // pattern [1-9][0-9]{0,8}
    }
}
