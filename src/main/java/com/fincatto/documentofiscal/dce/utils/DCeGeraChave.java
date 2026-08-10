package com.fincatto.documentofiscal.dce.utils;

import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.ide.DCeIdentificacao;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;

/**
 * Gera a chave de acesso de 44 caracteres da DC-e (modelo 99).
 *
 * Estrutura conforme Tabela 2-1 do Manual da Visao Geral:
 * cUF(2) + AAMM(4) + CNPJ/CPF(14) + mod=99(2) + serie(3) + nDC(9)
 *   + tpEmis(1) + tpEmit(1) + nSiteAutoriz(1) + cDC(6) + cDV(1) = 44.
 *
 * DV: modulo 11 com pesos ciclicos 2..9 (direita para esquerda), usando a conversao
 * por valor de char (ASCII - 48), que funciona tanto para digitos quanto para letras
 * A-Z e portanto suporta o CNPJ alfanumerico (NT 2026.004).
 */
public class DCeGeraChave {

    private static final SecureRandom RANDOM = new SecureRandom();

    private final DCe dce;

    public DCeGeraChave(final DCe dce) {
        this.dce = dce;
    }

    public String geraCodigoNumerico() {
        return StringUtils.leftPad(String.valueOf(RANDOM.nextInt(1000000)), 6, "0");
    }

    public String preencher() {
        final DCeIdentificacao ide = this.dce.getInfo().getIdentificacao();
        ide.setCodigoNumerico(this.geraCodigoNumerico());
        final String chave = this.getChaveAcesso();
        ide.setDigitoVerificador(chave.substring(chave.length() - 1));
        this.dce.getInfo().setIdentificador(chave);
        return chave;
    }

    public String getChaveAcesso() {
        final String chaveSemDv = this.geraChaveAcessoSemDV();
        return chaveSemDv + this.calcularDigitoVerificador(chaveSemDv);
    }

    public Integer getDV() {
        return this.calcularDigitoVerificador(this.geraChaveAcessoSemDV());
    }

    public Integer calcularDigitoVerificador(final String chaveSemDV) {
        if (chaveSemDV == null || chaveSemDV.length() != 43) {
            throw new IllegalStateException("Chave sem DV deve ter 43 digitos para calculo do DV");
        }
        final char[] valores = chaveSemDV.toCharArray();
        final int[] pesos = {2, 3, 4, 5, 6, 7, 8, 9};
        int indice = 0;
        int soma = 0;
        for (int i = valores.length; i > 0; i--) {
            if (indice >= pesos.length) {
                indice = 0;
            }
            final int valor = valores[i - 1] - '0';
            soma += valor * pesos[indice++];
        }
        final int dv = 11 - (soma % 11);
        return ((dv == 11) || (dv == 10)) ? 0 : dv;
    }

    private String geraChaveAcessoSemDV() {
        final DCeIdentificacao ide = this.dce.getInfo().getIdentificacao();
        if (StringUtils.isBlank(ide.getCodigoNumerico())) {
            throw new IllegalStateException("Codigo numerico (cDC) deve estar presente para gerar a chave de acesso");
        }
        final String cpfCnpj = obterCpfCnpjEmitente();
        return StringUtils.leftPad(ide.getCodigoUF().getCodigoIbge(), 2, "0")
                + StringUtils.leftPad(DateTimeFormatter.ofPattern("yyMM").format(ide.getDataHoraEmissao()), 4, "0")
                + StringUtils.leftPad(cpfCnpj, 14, "0")
                + DCeIdentificacao.MOD.getCodigo()
                + StringUtils.leftPad(ide.getSerie(), 3, "0")
                + StringUtils.leftPad(ide.getNumero(), 9, "0")
                + ide.getTipoEmissao().getCodigo()
                + ide.getTipoEmitente().getCodigo()
                + ide.getNumeroSiteAutorizador()
                + StringUtils.leftPad(ide.getCodigoNumerico(), 6, "0");
    }

    private String obterCpfCnpjEmitente() {
        final String cnpj = this.dce.getInfo().getEmitente().getCnpj();
        if (StringUtils.isNotBlank(cnpj)) {
            return cnpj;
        }
        final String cpf = this.dce.getInfo().getEmitente().getCpf();
        if (StringUtils.isNotBlank(cpf)) {
            return cpf;
        }
        throw new IllegalStateException(
                "Para gerar a chave de acesso, o Emitente deve possuir CNPJ ou CPF (idOutros nao e suportado para chave)");
    }
}
