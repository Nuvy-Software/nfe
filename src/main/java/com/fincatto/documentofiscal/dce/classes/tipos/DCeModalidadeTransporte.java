package com.fincatto.documentofiscal.dce.classes.tipos;

/**
 * Modalidade de transporte da DC-e (campo modTrans).
 */
public enum DCeModalidadeTransporte {

    CORREIOS("0", "Transporte pelos correios"),
    CONTA_PROPRIA("1", "Transporte por conta propria"),
    EMPRESA_TRANSPORTADORA("2", "Transporte por empresa transportadora");

    private final String codigo;
    private final String descricao;

    DCeModalidadeTransporte(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static DCeModalidadeTransporte valueOfCodigo(final String codigo) {
        for (final DCeModalidadeTransporte tipo : DCeModalidadeTransporte.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }
}
