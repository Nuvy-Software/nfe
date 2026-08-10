package com.fincatto.documentofiscal.dce.classes.envio;

import com.fincatto.documentofiscal.dce.classes.DCe;

/**
 * Wrapper de retorno do envio da DC-e: combina o retorno SEFAZ
 * com o XML da DC-e ja assinada que foi transmitida.
 */
public class DCeEnvioRetornoDados {

    private final DCeEnvioRetorno retorno;
    private final DCe dceEnviada;

    public DCeEnvioRetornoDados(final DCeEnvioRetorno retorno, final DCe dceEnviada) {
        this.retorno = retorno;
        this.dceEnviada = dceEnviada;
    }

    public DCeEnvioRetorno getRetorno() {
        return this.retorno;
    }

    public DCe getDceEnviada() {
        return this.dceEnviada;
    }
}
