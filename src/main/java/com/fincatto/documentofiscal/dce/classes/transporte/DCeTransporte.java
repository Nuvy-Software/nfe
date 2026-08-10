package com.fincatto.documentofiscal.dce.classes.transporte;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeModalidadeTransporte;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Dados de transporte da DC-e (Grupo X - transp).
 */
@Root(name = "transp")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeTransporte extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "modTrans")
    private DCeModalidadeTransporte modalidade;

    @Element(name = "CNPJTransp")
    private String cnpjTransportador;

    public void setModalidade(final DCeModalidadeTransporte modalidade) {
        this.modalidade = modalidade;
    }

    public void setCnpjTransportador(final String cnpjTransportador) {
        DFStringValidador.cnpj(cnpjTransportador);
        this.cnpjTransportador = cnpjTransportador;
    }

    public DCeModalidadeTransporte getModalidade() {
        return this.modalidade;
    }

    public String getCnpjTransportador() {
        return this.cnpjTransportador;
    }
}
