package com.fincatto.documentofiscal.dce.classes.ide;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Identificacao da DC-e (Grupo B - ide). Modelo fixo "99".
 */
@Root(name = "ide")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeIdentificacao extends DFBase {

    private static final long serialVersionUID = 1L;
    public static final DFModelo MOD = DFModelo.DCE;

    @Element(name = "cUF")
    private DFUnidadeFederativa codigoUF;

    @Element(name = "cDC")
    private String codigoNumerico;

    @Element(name = "mod")
    private String modelo = MOD.getCodigo();

    @Element(name = "serie")
    private String serie;

    @Element(name = "nDC")
    private String numero;

    @Element(name = "dhEmi")
    private ZonedDateTime dataHoraEmissao;

    @Element(name = "tpEmis")
    private DCeTipoEmissao tipoEmissao;

    @Element(name = "tpEmit")
    private DCeTipoEmitente tipoEmitente;

    @Element(name = "nSiteAutoriz")
    private String numeroSiteAutorizador;

    @Element(name = "cDV")
    private String digitoVerificador;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "verProc")
    private String versaoProcesso;

    public void setCodigoUF(final DFUnidadeFederativa codigoUF) {
        this.codigoUF = codigoUF;
    }

    public void setCodigoNumerico(final String codigoNumerico) {
        DFStringValidador.exatamente6N(codigoNumerico, "Codigo Numerico da DC-e");
        this.codigoNumerico = codigoNumerico;
    }

    public void setSerie(final String serie) {
        if (serie == null || !serie.matches("0|[1-9][0-9]{0,2}")) {
            throw new IllegalStateException("Serie da DC-e deve ser '0' ou 1-3 digitos (1-999)");
        }
        this.serie = serie;
    }

    public void setNumero(final String numero) {
        if (numero == null || !numero.matches("[1-9][0-9]{0,8}")) {
            throw new IllegalStateException("Numero da DC-e deve ter 1-9 digitos (1 a 999999999)");
        }
        this.numero = numero;
    }

    public void setDataHoraEmissao(final ZonedDateTime dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public void setTipoEmissao(final DCeTipoEmissao tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public void setTipoEmitente(final DCeTipoEmitente tipoEmitente) {
        this.tipoEmitente = tipoEmitente;
    }

    public void setNumeroSiteAutorizador(final String numeroSiteAutorizador) {
        DFStringValidador.exatamente1(numeroSiteAutorizador, "Numero do Site do Autorizador");
        this.numeroSiteAutorizador = numeroSiteAutorizador;
    }

    public void setDigitoVerificador(final String digitoVerificador) {
        DFStringValidador.exatamente1(digitoVerificador, "Digito Verificador");
        this.digitoVerificador = digitoVerificador;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setVersaoProcesso(final String versaoProcesso) {
        DFStringValidador.tamanho20(versaoProcesso, "Versao do Processo de Emissao");
        this.versaoProcesso = versaoProcesso;
    }

    public DFUnidadeFederativa getCodigoUF() {
        return this.codigoUF;
    }

    public String getCodigoNumerico() {
        return this.codigoNumerico;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getSerie() {
        return this.serie;
    }

    public String getNumero() {
        return this.numero;
    }

    public ZonedDateTime getDataHoraEmissao() {
        return this.dataHoraEmissao;
    }

    public DCeTipoEmissao getTipoEmissao() {
        return this.tipoEmissao;
    }

    public DCeTipoEmitente getTipoEmitente() {
        return this.tipoEmitente;
    }

    public String getNumeroSiteAutorizador() {
        return this.numeroSiteAutorizador;
    }

    public String getDigitoVerificador() {
        return this.digitoVerificador;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getVersaoProcesso() {
        return this.versaoProcesso;
    }
}
