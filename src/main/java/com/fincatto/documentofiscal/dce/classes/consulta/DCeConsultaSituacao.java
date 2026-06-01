package com.fincatto.documentofiscal.dce.classes.consulta;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Mensagem de entrada do WS DCeConsulta (consSitDCe).
 */
@Root(name = "consSitDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeConsultaSituacao extends DFBase {

    private static final long serialVersionUID = 1L;
    private static final String SERVICO = "CONSULTAR";

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "xServ")
    private String servico = SERVICO;

    @Element(name = "chDCe")
    private String chaveAcesso;

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setChaveAcesso(final String chaveAcesso) {
        DFStringValidador.exatamente44N(chaveAcesso, "Chave de Acesso da DC-e");
        this.chaveAcesso = chaveAcesso;
    }

    public String getVersao() {
        return this.versao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getServico() {
        return this.servico;
    }

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }
}
