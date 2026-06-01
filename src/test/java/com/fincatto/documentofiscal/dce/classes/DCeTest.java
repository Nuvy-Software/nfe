package com.fincatto.documentofiscal.dce.classes;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.junit.Assert;
import org.junit.Test;

public class DCeTest {

    @Test
    public void deveSerializarDCeCompletaComEstruturaEsperada() {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        final String xml = dce.toString();

        Assert.assertTrue(xml.contains("<DCe xmlns=\"http://www.portalfiscal.inf.br/dce\">"));
        Assert.assertTrue(xml.contains("Id=\"DCe41250112345678000195990010000000011000123456\""));
        Assert.assertTrue(xml.contains("versao=\"1.00\""));
        Assert.assertTrue(xml.contains("<mod>99</mod>"));
        Assert.assertTrue(xml.contains("<CNPJ>12345678000195</CNPJ>"));
        Assert.assertTrue(xml.contains("<xObs1>"));
        Assert.assertTrue(xml.contains("<xObs2>"));
    }

    @Test
    public void deveValidarContraXSDQuandoCompletadaComSolicSuplESignature() throws Exception {
        // O XSD oficial exige infSolicDCe, infDCeSupl e Signature como elementos obrigatorios
        // no elemento raiz DCe. Em runtime, infDCeSupl e protDCe sao retornados pela SEFAZ
        // e a Signature e injetada por DFAssinaturaDigital antes da transmissao.
        // Aqui injetamos stubs minimos para testar a validacao estrutural do leiaute.
        final String xml = FabricaDeObjetosFakeDCe.getDCeCompleta().toString().replace(
                "</DCe>",
                "<infSolicDCe><xSolic></xSolic></infSolicDCe>"
                        + "<infDCeSupl>"
                        + "<qrCodDCe>https://www.fazenda.pr.gov.br/dce/qrcode?chDCe=41250112345678000195990010000000011000123456&amp;tpAmb=2</qrCodDCe>"
                        + "<urlChave>https://www.fazenda.pr.gov.br/dce/qrcode</urlChave>"
                        + "</infDCeSupl>"
                        + signatureStub()
                        + "</DCe>");
        Assert.assertTrue(DCeXMLValidador.validaDCe(xml));
    }

    private static String signatureStub() {
        return "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">"
                + "<SignedInfo>"
                + "<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>"
                + "<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>"
                + "<Reference URI=\"#DCe41250112345678000195990010000000011000123456\">"
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
                + "</Signature>";
    }
}
