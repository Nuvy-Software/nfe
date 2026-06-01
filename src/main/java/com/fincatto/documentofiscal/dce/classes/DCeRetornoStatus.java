package com.fincatto.documentofiscal.dce.classes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum DCeRetornoStatus {

    CODIGO_100(100, "Autorizado o uso da DC-e"),
    CODIGO_103(103, "Lote recebido com sucesso"),
    CODIGO_104(104, "Lote processado"),
    CODIGO_105(105, "Lote em processamento"),
    CODIGO_107(107, "Serviço em Operação"),
    CODIGO_108(108, "Serviço Paralisado Momentaneamente"),
    CODIGO_109(109, "Serviço Paralisado sem Previsão"),
    CODIGO_110(110, "Uso Denegado"),
    CODIGO_135(135, "Evento registrado e vinculado à DC-e"),
    CODIGO_150(150, "Autorizado o uso da DC-e, autorização fora de prazo"),
    CODIGO_155(155, "Evento registrado e vinculado à DC-e, fora de prazo"),
    CODIGO_204(204, "Rejeição: Duplicidade de DC-e"),
    CODIGO_215(215, "Rejeição: Falha no schema XML"),
    CODIGO_220(220, "Rejeição: DC-e autorizada há mais de 24 horas"),
    CODIGO_539(539, "Rejeição: Duplicidade de DC-e, com diferença na Chave de Acesso"),
    CODIGO_573(573, "Rejeição: Duplicidade de Evento"),
    CODIGO_999(999, "Rejeição: Erro não catalogado");

    private static final List<DCeRetornoStatus> AUTORIZADOS = Arrays.asList(CODIGO_100, CODIGO_150);
    private static final List<DCeRetornoStatus> DENEGADOS = List.of(CODIGO_110);
    private static final List<DCeRetornoStatus> DUPLICADOS = Arrays.asList(CODIGO_204, CODIGO_539);
    private static final List<DCeRetornoStatus> EVENTOS_REGISTRADOS = Arrays.asList(CODIGO_135, CODIGO_155);

    private final int codigo;
    private final String motivo;

    DCeRetornoStatus(final int codigo, final String motivo) {
        this.codigo = codigo;
        this.motivo = motivo;
    }

    public static Optional<DCeRetornoStatus> valueOfCodigo(final String codigo) {
        if (codigo == null) {
            return Optional.empty();
        }
        try {
            final int c = Integer.parseInt(codigo);
            return Arrays.stream(values()).filter(s -> s.codigo == c).findFirst();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static boolean isCodigoAutorizado(final String codigo) {
        return valueOfCodigo(codigo).map(DCeRetornoStatus::isAutorizado).orElse(false);
    }

    public static boolean isCodigoEventoRegistrado(final String codigo) {
        return valueOfCodigo(codigo).map(DCeRetornoStatus::isEventoRegistrado).orElse(false);
    }

    public boolean isAutorizado() {
        return AUTORIZADOS.contains(this);
    }

    public boolean isEventoRegistrado() {
        return EVENTOS_REGISTRADOS.contains(this);
    }

    public boolean isDenegado() {
        return DENEGADOS.contains(this);
    }

    public boolean isDuplicado() {
        return DUPLICADOS.contains(this);
    }

    public boolean isRejeitado() {
        return this.codigo > 200 && !this.isAutorizado() && !this.isDuplicado() && !this.isDenegado();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getMotivo() {
        return this.motivo;
    }
}
