package com.fincatto.documentofiscal.dce.classes.statusservico;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.validadores.DCeXMLValidador;
import org.junit.Assert;
import org.junit.Test;

public class DCeConsultaStatusServicoTest {

    @Test
    public void deveSerializarStatusServico() throws Exception {
        final DCeConsultaStatusServico consulta = new DCeConsultaStatusServico();
        consulta.setAmbiente(DFAmbiente.HOMOLOGACAO);
        consulta.setCodigoUF(DFUnidadeFederativa.PR);
        final String xml = consulta.toString();

        Assert.assertTrue(xml.contains("<consStatServDCe versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/dce\"")
                || xml.contains("<consStatServDCe xmlns=\"http://www.portalfiscal.inf.br/dce\" versao=\"1.00\""));
        Assert.assertTrue(xml.contains("versao=\"1.00\""));
        Assert.assertTrue(xml.contains("<tpAmb>2</tpAmb>"));
        Assert.assertTrue(xml.contains("<cUF>41</cUF>"));
        Assert.assertTrue(xml.contains("<xServ>STATUS</xServ>"));

        Assert.assertTrue(DCeXMLValidador.validaStatusServicoDCe(xml));
    }
}
