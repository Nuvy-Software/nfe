package com.fincatto.documentofiscal.dce.parsers;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Decompoe a chave de acesso de 44 digitos da DC-e em seus componentes.
 *
 * cUF(2) + AAMM(4) + CNPJ/CPF(14) + mod(2) + serie(3) + nDC(9)
 *   + tpEmis(1) + tpEmit(1) + nSiteAutoriz(1) + cDC(6) + cDV(1) = 44.
 */
public class DCeChaveParser {

    private final String chave;

    public DCeChaveParser(final String chave) {
        if (chave == null || chave.length() != 44) {
            throw new IllegalStateException("Chave de acesso da DC-e deve ter exatamente 44 digitos");
        }
        this.chave = chave;
    }

    public DFUnidadeFederativa getNFUnidadeFederativa() {
        return DFUnidadeFederativa.valueOfCodigo(this.chave.substring(0, 2));
    }

    /**
     * Retorna o ano/mes de emissao no formato yyMM como YearMonth (ano com seculo 20xx).
     */
    public YearMonth getAnoMesEmissao() {
        return YearMonth.parse("20" + this.chave.substring(2, 4) + "-" + this.chave.substring(4, 6),
                DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public String getCnpjOuCpfEmitente() {
        return this.chave.substring(6, 20);
    }

    public DFModelo getModelo() {
        return DFModelo.valueOfCodigo(this.chave.substring(20, 22));
    }

    public String getSerie() {
        return this.chave.substring(22, 25);
    }

    public String getNumero() {
        return this.chave.substring(25, 34);
    }

    public DCeTipoEmissao getTipoEmissao() {
        return DCeTipoEmissao.valueOfCodigo(this.chave.substring(34, 35));
    }

    public DCeTipoEmitente getTipoEmitente() {
        return DCeTipoEmitente.valueOfCodigo(this.chave.substring(35, 36));
    }

    public String getNumeroSiteAutorizador() {
        return this.chave.substring(36, 37);
    }

    public String getCodigoNumerico() {
        return this.chave.substring(37, 43);
    }

    public String getDigitoVerificador() {
        return this.chave.substring(43, 44);
    }

    public String getChave() {
        return this.chave;
    }

    /**
     * O ambiente nao esta codificado na chave de acesso; este metodo retorna {@code null}.
     * O ambiente eh transmitido em tpAmb (campo separado).
     */
    public DFAmbiente getAmbiente() {
        return null;
    }
}
