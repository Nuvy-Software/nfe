package com.fincatto.documentofiscal.dce.classes.tipos;

/**
 * Tipo do emitente da DC-e (campo tpEmit).
 * Identifica quem opera o sistema autorizador e assina a DC-e.
 */
public enum DCeTipoEmitente {

    FISCO("0", "App Fisco"),
    MARKETPLACE("1", "Marketplace"),
    EMISSOR_PROPRIO("2", "Emissor proprio"),
    TRANSPORTADORA("3", "Transportadora"),
    ECT("4", "ECT");

    private final String codigo;
    private final String descricao;

    DCeTipoEmitente(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static DCeTipoEmitente valueOfCodigo(final String codigo) {
        for (final DCeTipoEmitente tipo : DCeTipoEmitente.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }
}
