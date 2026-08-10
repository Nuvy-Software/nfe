package com.fincatto.documentofiscal.dce.classes.evento.cancelamento;

import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.junit.Assert;
import org.junit.Test;

public class DCeEventoCancelamentoTest {

    @Test
    public void deveSerializarEventoCancelamento() {
        final String xml = FabricaDeObjetosFakeDCe.getEventoCancelamento().toString();
        Assert.assertTrue(xml.contains("<descEvento>Cancelamento</descEvento>"));
        Assert.assertTrue(xml.contains("<nProt>1412500000000010</nProt>"));
        Assert.assertTrue(xml.contains("<xJust>Cancelamento por erro de digitacao no destinatario do envio</xJust>"));
    }

    @Test
    public void deveValidarContraXSDOficial() throws Exception {
        // O XSD evCancDCe nao define atributo versao (ele esta em detEvento/versaoEvento).
        final String xml = "<evCancDCe xmlns=\"http://www.portalfiscal.inf.br/dce\">"
                + "<descEvento>Cancelamento</descEvento>"
                + "<nProt>1412500000000010</nProt>"
                + "<xJust>Cancelamento por erro de digitacao no destinatario do envio</xJust>"
                + "</evCancDCe>";
        Assert.assertTrue(DCeXMLValidador.validaEventoCancelamentoDCe(xml));
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarJustificativaCurta() {
        new DCeEventoCancelamento().setJustificativa("curta");
    }

    @Test(expected = IllegalStateException.class)
    public void naoDeveAceitarProtocoloInvalido() {
        new DCeEventoCancelamento().setNumeroProtocolo("123");
    }
}
