package com.fincatto.documentofiscal.dce;

import com.fincatto.documentofiscal.DFAmbiente;

/**
 * Enderecos dos Web Services do Ambiente Nacional da DC-e.
 * Autorizador unico hospedado pela SEFAZ-PR atendendo todas as UFs participantes.
 */
public enum DCeEndpoint {

    AUTORIZACAO("DCeAutorizacao", "dceAutorizacaoZip"),
    CONSULTA("DCeConsulta", "dceConsulta"),
    STATUS_SERVICO("DCeStatusServico", "dceStatusServico"),
    RECEPCAO_EVENTO("DCeRecepcaoEvento", "dceRecepcaoEvento");

    private static final String BASE_PRODUCAO = "https://dce.fazenda.pr.gov.br/dce/";
    private static final String BASE_HOMOLOGACAO = "https://homologacao.dce.fazenda.pr.gov.br/dce/";
    private static final String WSDL_NAMESPACE_BASE = "http://www.portalfiscal.inf.br/dce/wsdl/";
    public static final String QRCODE_URL = "https://www.fazenda.pr.gov.br/dce/qrcode";

    private final String servico;
    private final String operacao;

    DCeEndpoint(final String servico, final String operacao) {
        this.servico = servico;
        this.operacao = operacao;
    }

    public String getServico() {
        return this.servico;
    }

    public String getUrl(final DFAmbiente ambiente) {
        final String base = ambiente == DFAmbiente.PRODUCAO ? BASE_PRODUCAO : BASE_HOMOLOGACAO;
        return base + this.servico;
    }

    /**
     * Namespace do WSDL do servico, usando o nome real do servico (ex.: {@code DCeAutorizacao}),
     * conforme o targetNamespace publicado pela SEFAZ.
     */
    public String getWsdlNamespace() {
        return WSDL_NAMESPACE_BASE + this.servico;
    }

    /**
     * SOAPAction da operacao do servico no formato {@code {wsdlNamespace}/{operacao}}.
     */
    public String getSoapAction() {
        return this.getWsdlNamespace() + "/" + this.operacao;
    }
}
