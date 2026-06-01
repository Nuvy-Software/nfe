package com.fincatto.documentofiscal.dce;

import com.fincatto.documentofiscal.DFAmbiente;

/**
 * Enderecos dos Web Services do Ambiente Nacional da DC-e.
 * Autorizador unico hospedado pela SEFAZ-PR atendendo todas as UFs participantes.
 */
public enum DCeEndpoint {

    AUTORIZACAO("DCeAutorizacao"),
    CONSULTA("DCeConsulta"),
    STATUS_SERVICO("DCeStatusServico"),
    RECEPCAO_EVENTO("DCeRecepcaoEvento");

    private static final String BASE_PRODUCAO = "https://dce.fazenda.pr.gov.br/dce/";
    private static final String BASE_HOMOLOGACAO = "https://homologacao.dce.fazenda.pr.gov.br/dce/";
    public static final String QRCODE_URL = "https://www.fazenda.pr.gov.br/dce/qrcode";

    private final String servico;

    DCeEndpoint(final String servico) {
        this.servico = servico;
    }

    public String getUrl(final DFAmbiente ambiente) {
        final String base = ambiente == DFAmbiente.PRODUCAO ? BASE_PRODUCAO : BASE_HOMOLOGACAO;
        return base + this.servico;
    }
}
