package com.fincatto.documentofiscal.dce.classes.endereco;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import org.junit.Assert;
import org.junit.Test;

public class DCeEnderecoEmitenteTest {

    @Test
    public void deveSerializarEnderecoEmitente() {
        final String xml = FabricaDeObjetosFakeDCe.getEnderecoEmitente().toString();
        Assert.assertTrue(xml.contains("<xLgr>Rua Sao Paulo</xLgr>"));
        Assert.assertTrue(xml.contains("<UF>PR</UF>"));
        Assert.assertTrue(xml.contains("<nro>123</nro>"));
        Assert.assertTrue(xml.contains("<xCpl>Sala 1</xCpl>"));
        Assert.assertTrue(xml.contains("<cMun>4106902</cMun>"));
        Assert.assertTrue(xml.contains("<UF>PR</UF>"));
        Assert.assertTrue(xml.contains("<CEP>80010000</CEP>"));
        Assert.assertTrue(xml.contains("<cPais>1058</cPais>"));
        Assert.assertTrue(xml.contains("<xPais>Brasil</xPais>"));
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarLogradouroCurto() {
        new DCeEnderecoEmitente().setLogradouro("X");
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarCepInvalido() {
        new DCeEnderecoEmitente().setCep("123");
    }
}
