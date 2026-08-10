package com.fincatto.documentofiscal.dce;

import com.fincatto.documentofiscal.DFConfig;
import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;

/**
 * Configuracao basica do sistema da Declaracao de Conteudo Eletronica (DC-e).
 * Modelo fiscal 99, instituido pelo Ajuste SINIEF 05/2021.
 * Autorizador unico nacional, hospedado pela SEFAZ-PR.
 */
public abstract class DCeConfig extends DFConfig {

    public static final String NAMESPACE = "http://www.portalfiscal.inf.br/dce";
    public static final String VERSAO = "1.00";

    @Override
    public DFModelo getModelo() {
        return DFModelo.DCE;
    }

    /**
     * Tipo da emissao da DC-e (normal ou contingencia offline).
     *
     * @return Tipo da emissao da DC-e.
     */
    public DCeTipoEmissao getTipoEmissao() {
        return DCeTipoEmissao.NORMAL;
    }

    /**
     * Identificacao do site do Autorizador que recepcionou a DC-e (0 a 9).
     * Se o autorizador possuir apenas um site, deve ser 0 (zero).
     *
     * @return Numero do site do autorizador.
     */
    public String getSiteAutorizador() {
        return "0";
    }
}
