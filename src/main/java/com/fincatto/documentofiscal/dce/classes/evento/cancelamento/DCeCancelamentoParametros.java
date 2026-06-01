package com.fincatto.documentofiscal.dce.classes.evento.cancelamento;

import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Parametros para o evento de cancelamento da DC-e (tpEvento=110111).
 *
 * <p>A SEFAZ exige, no infEvento, exatamente um dos campos CNPJUsEmit,
 * CPFUsEmit ou IdOutrosUsEmit (xs:choice obrigatoria do XSD
 * eventoDCeTiposBasico_v1.00). Esta classe valida essa restricao na
 * construcao via {@link Builder#build()}, antes de qualquer chamada a SEFAZ.</p>
 */
public class DCeCancelamentoParametros {

    private final String chaveAcesso;
    private final String numeroProtocolo;
    private final String justificativa;
    private final DCeTipoEmitente tipoEmitente;
    private final String cnpjAutor;
    private final String cnpjUsuarioEmitente;
    private final String cpfUsuarioEmitente;
    private final String idOutrosUsuarioEmitente;

    private DCeCancelamentoParametros(final Builder b) {
        if (b.chaveAcesso == null || b.numeroProtocolo == null || b.justificativa == null
                || b.tipoEmitente == null || b.cnpjAutor == null) {
            throw new IllegalStateException(
                    "Campos obrigatorios: chaveAcesso, numeroProtocolo, justificativa, tipoEmitente, cnpjAutor");
        }
        final long preenchidos = Stream
                .of(b.cnpjUsuarioEmitente, b.cpfUsuarioEmitente, b.idOutrosUsuarioEmitente)
                .filter(Objects::nonNull)
                .count();
        if (preenchidos != 1) {
            throw new IllegalStateException(
                    "Informe exatamente um entre cnpjUsuarioEmitente, cpfUsuarioEmitente "
                            + "ou idOutrosUsuarioEmitente (encontrado: " + preenchidos + ")");
        }
        this.chaveAcesso = b.chaveAcesso;
        this.numeroProtocolo = b.numeroProtocolo;
        this.justificativa = b.justificativa;
        this.tipoEmitente = b.tipoEmitente;
        this.cnpjAutor = b.cnpjAutor;
        this.cnpjUsuarioEmitente = b.cnpjUsuarioEmitente;
        this.cpfUsuarioEmitente = b.cpfUsuarioEmitente;
        this.idOutrosUsuarioEmitente = b.idOutrosUsuarioEmitente;
    }

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public String getNumeroProtocolo() {
        return this.numeroProtocolo;
    }

    public String getJustificativa() {
        return this.justificativa;
    }

    public DCeTipoEmitente getTipoEmitente() {
        return this.tipoEmitente;
    }

    public String getCnpjAutor() {
        return this.cnpjAutor;
    }

    public String getCnpjUsuarioEmitente() {
        return this.cnpjUsuarioEmitente;
    }

    public String getCpfUsuarioEmitente() {
        return this.cpfUsuarioEmitente;
    }

    public String getIdOutrosUsuarioEmitente() {
        return this.idOutrosUsuarioEmitente;
    }

    public static class Builder {

        private String chaveAcesso;
        private String numeroProtocolo;
        private String justificativa;
        private DCeTipoEmitente tipoEmitente;
        private String cnpjAutor;
        private String cnpjUsuarioEmitente;
        private String cpfUsuarioEmitente;
        private String idOutrosUsuarioEmitente;

        public Builder chaveAcesso(final String chaveAcesso) {
            this.chaveAcesso = chaveAcesso;
            return this;
        }

        public Builder numeroProtocolo(final String numeroProtocolo) {
            this.numeroProtocolo = numeroProtocolo;
            return this;
        }

        public Builder justificativa(final String justificativa) {
            this.justificativa = justificativa;
            return this;
        }

        public Builder tipoEmitente(final DCeTipoEmitente tipoEmitente) {
            this.tipoEmitente = tipoEmitente;
            return this;
        }

        public Builder cnpjAutor(final String cnpjAutor) {
            this.cnpjAutor = cnpjAutor;
            return this;
        }

        public Builder cnpjUsuarioEmitente(final String cnpjUsuarioEmitente) {
            this.cnpjUsuarioEmitente = cnpjUsuarioEmitente;
            return this;
        }

        public Builder cpfUsuarioEmitente(final String cpfUsuarioEmitente) {
            this.cpfUsuarioEmitente = cpfUsuarioEmitente;
            return this;
        }

        public Builder idOutrosUsuarioEmitente(final String idOutrosUsuarioEmitente) {
            this.idOutrosUsuarioEmitente = idOutrosUsuarioEmitente;
            return this;
        }

        public DCeCancelamentoParametros build() {
            return new DCeCancelamentoParametros(this);
        }
    }
}
