package com.fincatto.documentofiscal.dce.validadores;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public final class DCeXMLValidador {

    private static boolean validaDCe(final String xml, final String xsd, final String subPasta) throws IOException, SAXException, URISyntaxException {
        final URL xsdPath = DCeXMLValidador.class.getClassLoader().getResource(String.format("schemas/PL_DCe_v1.00a_NT2024.001/%s/%s", subPasta, xsd));
        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new StreamSource(xsdPath.toURI().toString()));
        schema.newValidator().validate(new StreamSource(new StringReader(xml)));
        return true;
    }

    public static boolean validaDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "dce_v1.00.xsd", "dce");
    }

    public static boolean validaRetornoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "retDCe_v1.00.xsd", "dce");
    }

    public static boolean validaProcDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "procDCe_v1.00.xsd", "dce");
    }

    public static boolean validaConsultaSituacaoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "consSitDCe_v1.00.xsd", "consulta");
    }

    public static boolean validaRetornoConsultaSituacaoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "retConsSitDCe_v1.00.xsd", "consulta");
    }

    public static boolean validaStatusServicoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "consStatServDCe_v1.00.xsd", "consulta");
    }

    public static boolean validaRetornoStatusServicoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "retConsStatServDCe_v1.00.xsd", "consulta");
    }

    public static boolean validaEventoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "eventoDCe_v1.00.xsd", "evento");
    }

    public static boolean validaRetornoEventoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "retEventoDCe_v1.00.xsd", "evento");
    }

    public static boolean validaEventoCancelamentoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "evCancDCe_v1.00.xsd", "evento");
    }

    public static boolean validaProcEventoDCe(final String arquivoXML) throws Exception {
        return DCeXMLValidador.validaDCe(arquivoXML, "procEventoDCe_v1.00.xsd", "evento");
    }
}
