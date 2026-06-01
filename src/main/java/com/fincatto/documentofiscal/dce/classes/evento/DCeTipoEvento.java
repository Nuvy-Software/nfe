package com.fincatto.documentofiscal.dce.classes.evento;

/**
 * Tipos de evento da DC-e. Conforme Manual de Visao Geral (versao 1.00),
 * a DC-e possui apenas o evento de Cancelamento.
 */
public enum DCeTipoEvento {

    CANCELAMENTO("110111", "Cancelamento");

    private final String codigo;
    private final String descricao;

    DCeTipoEvento(final String codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static DCeTipoEvento valueOfCodigo(final String codigo) {
        for (final DCeTipoEvento tipo : DCeTipoEvento.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }
}
