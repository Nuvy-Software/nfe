package com.fincatto.documentofiscal.dce.utils;

import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.DCeEndpoint;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import com.fincatto.documentofiscal.utils.DFAssinaturaDigital;
import org.apache.commons.lang3.StringUtils;

/**
 * Monta a URL do QR-Code a ser impresso na DACE.
 *
 * Para emissao normal (tpEmis=1):
 *   {baseUrl}?chDCe=<chave>&tpAmb=<1|2>
 *
 * Para contingencia offline (tpEmis=9), acrescenta o identificador do emitente
 * (CNPJ, CPF ou idOutros) e a assinatura RSA-SHA1 (Base64) da chave de acesso:
 *   {baseUrl}?chDCe=<chave>&tpAmb=<1|2>&[CNPJ|CPF|idOutros]=<id>&sign=<base64>
 */
public class DCeGeraQRCode {

    private final DCe dce;
    private final DCeConfig config;

    public DCeGeraQRCode(final DCe dce, final DCeConfig config) {
        this.dce = dce;
        this.config = config;
    }

    public String getQRCode() throws Exception {
        final String chave = this.dce.getInfo().getChaveAcesso();
        if (StringUtils.isBlank(chave)) {
            throw new IllegalStateException("DC-e sem chave de acesso preenchida no Identificador de infDCe");
        }

        final StringBuilder url = new StringBuilder(DCeEndpoint.QRCODE_URL);
        url.append("?chDCe=").append(chave);
        url.append("&tpAmb=").append(this.config.getAmbiente().getCodigo());

        if (this.dce.getInfo().getIdentificacao().getTipoEmissao() == DCeTipoEmissao.CONTINGENCIA_OFFLINE) {
            url.append("&").append(this.identificadorEmitenteParaQRCode());
            url.append("&sign=").append(new DFAssinaturaDigital(this.config).assinarString(chave));
        }
        return url.toString();
    }

    public String getUrlConsultaChaveAcesso() {
        return DCeEndpoint.QRCODE_URL;
    }

    private String identificadorEmitenteParaQRCode() {
        final String cnpj = this.dce.getInfo().getEmitente().getCnpj();
        if (StringUtils.isNotBlank(cnpj)) {
            return "CNPJ=" + cnpj;
        }
        final String cpf = this.dce.getInfo().getEmitente().getCpf();
        if (StringUtils.isNotBlank(cpf)) {
            return "CPF=" + cpf;
        }
        final String idOutros = this.dce.getInfo().getEmitente().getIdOutros();
        if (StringUtils.isNotBlank(idOutros)) {
            return "idOutros=" + idOutros;
        }
        throw new IllegalStateException(
                "Para QR-Code em contingencia e necessario CNPJ, CPF ou idOutros no Emitente");
    }
}
