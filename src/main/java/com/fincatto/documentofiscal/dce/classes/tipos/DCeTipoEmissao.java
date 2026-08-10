package com.fincatto.documentofiscal.dce.classes.tipos;

/**
 * Forma de emissao da DC-e (campo tpEmis).
 */
public enum DCeTipoEmissao {

    NORMAL("1", "Normal"),
    CONTINGENCIA_OFFLINE("9", "Contingencia offline");

    private final String codigo;
    private final String descricao;

    DCeTipoEmissao(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static DCeTipoEmissao valueOfCodigo(final String codigo) {
        for (final DCeTipoEmissao tipo : DCeTipoEmissao.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }
}
