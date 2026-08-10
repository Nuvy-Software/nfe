package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.junit.Assert;
import org.junit.Test;

public class DCeEventoTest {

    @Test
    public void deveSerializarEventoCompleto() {
        final String xml = FabricaDeObjetosFakeDCe.getEvento().toString();
        Assert.assertTrue(xml.contains("<eventoDCe versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/dce\"")
                || xml.contains("<eventoDCe xmlns=\"http://www.portalfiscal.inf.br/dce\" versao=\"1.00\""));
        Assert.assertTrue(xml.contains("versao=\"1.00\""));
        Assert.assertTrue(xml.contains("<infEvento Id=\"ID110111"));
        Assert.assertTrue(xml.contains("<cOrgao>41</cOrgao>"));
        Assert.assertTrue(xml.contains("<tpAmb>2</tpAmb>"));
        Assert.assertTrue(xml.contains("<tpEmit>2</tpEmit>"));
        Assert.assertTrue(xml.contains("<CNPJAutor>12345678000195</CNPJAutor>"));
        Assert.assertTrue(xml.contains("<tpEvento>110111</tpEvento>"));
        Assert.assertTrue(xml.contains("<nSeqEvento>1</nSeqEvento>"));
        Assert.assertTrue(xml.contains("<detEvento versaoEvento=\"1.00\">"));
        Assert.assertTrue(xml.contains("<evCancDCe>"));
    }

    @Test
    public void deveValidarContraXSDOficialAposSimularAssinatura() throws Exception {
        // O XSD eventoDCe exige ds:Signature obrigatorio. Injetamos um stub minimo
        // para validacao estrutural (assinatura real e gerada em runtime pelo WSRecepcaoEvento).
        final String xmlSemSignature = FabricaDeObjetosFakeDCe.getEvento().toString();
        final String xml = xmlSemSignature.replace("</eventoDCe>",
                "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">"
                        + "<SignedInfo>"
                        + "<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>"
                        + "<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>"
                        + "<Reference URI=\"#ID110111412501123456780001959900100000000112001234560011\">"
                        + "<Transforms>"
                        + "<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>"
                        + "<Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>"
                        + "</Transforms>"
                        + "<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>"
                        + "<DigestValue>aGFzaA==</DigestValue>"
                        + "</Reference>"
                        + "</SignedInfo>"
                        + "<SignatureValue>c2lnbmF0dXJl</SignatureValue>"
                        + "<KeyInfo><X509Data><X509Certificate>Y2VydA==</X509Certificate></X509Data></KeyInfo>"
                        + "</Signature></eventoDCe>");
        Assert.assertTrue(DCeXMLValidador.validaEventoDCe(xml));
    }

    /**
     * Com o CNPJ alfanumerico (NT 2026.004) a chave de acesso referenciada no evento
     * pode conter letras nas posicoes 7-20. O setter deve aceita-la.
     *
     * Nao valida contra o XSD de proposito: o pacote empacotado
     * (PL_DCe_v1.00a_NT2024.001) ainda restringe a chave a [0-9]{44}, e o pacote
     * adequado ao CNPJ alfanumerico ainda nao foi publicado para a DC-e.
     */
    @Test
    public void deveAceitarChaveDeAcessoAlfanumericaNoEvento() {
        final DCeInfoEvento info = new DCeInfoEvento();
        info.setChaveAcesso("412501AB12CD34EF5602990010000000011201234564");
        Assert.assertEquals("412501AB12CD34EF5602990010000000011201234564", info.getChaveAcesso());
    }
}
