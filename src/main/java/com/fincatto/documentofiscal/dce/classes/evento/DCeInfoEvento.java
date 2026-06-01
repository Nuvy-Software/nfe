package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.validadores.DFStringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Informacoes do evento da DC-e (infEvento). Possui o atributo Id que sera assinado.
 *
 * <p>Formato do Id: "ID" + tpEvento(6) + chDCe(44) + nSeqEvento(3) = 53 digitos, prefixo ID.</p>
 */
@Root(name = "infEvento")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInfoEvento extends DFBase {

    private static final long serialVersionUID = 1L;
    public static final String IDENT = "ID";

    @Attribute(name = "Id")
    private String identificador;

    @Element(name = "cOrgao")
    private Integer codigoOrgao;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "tpEmit")
    private String tipoEmitente;

    @Element(name = "CNPJAutor")
    private String cnpjAutor;

    @Element(name = "CNPJUsEmit", required = false)
    private String cnpjUsuarioEmitente;

    @Element(name = "CPFUsEmit", required = false)
    private String cpfUsuarioEmitente;

    @Element(name = "IdOutrosUsEmit", required = false)
    private String idOutrosUsuarioEmitente;

    @Element(name = "chDCe")
    private String chaveAcesso;

    @Element(name = "dhEvento")
    private ZonedDateTime dataHoraEvento;

    @Element(name = "tpEvento")
    private String tipoEvento;

    @Element(name = "nSeqEvento")
    private String numeroSequencialEvento;

    @Element(name = "detEvento")
    private DCeDetalhamentoEvento detalhamentoEvento;

    public void setIdentificador(final String identificador) {
        if (identificador == null || identificador.length() != 53 || !identificador.matches("[0-9]{53}")) {
            throw new IllegalStateException(
                    "Identificador do evento deve ser tpEvento(6) + chDCe(44) + nSeqEvento(3) = 53 digitos");
        }
        this.identificador = IDENT + identificador;
    }

    public void setCodigoOrgao(final Integer codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public void setTipoEmitente(final String tipoEmitente) {
        if (tipoEmitente == null || !tipoEmitente.matches("[0-3]")) {
            throw new IllegalStateException("tpEmit do evento deve ser 0, 1, 2 ou 3");
        }
        this.tipoEmitente = tipoEmitente;
    }

    public void setCnpjAutor(final String cnpjAutor) {
        DFStringValidador.cnpj(cnpjAutor);
        this.cnpjAutor = cnpjAutor;
    }

    public void setCnpjUsuarioEmitente(final String cnpjUsuarioEmitente) {
        DFStringValidador.cnpj(cnpjUsuarioEmitente);
        this.cnpjUsuarioEmitente = cnpjUsuarioEmitente;
    }

    public void setCpfUsuarioEmitente(final String cpfUsuarioEmitente) {
        DFStringValidador.cpf(cpfUsuarioEmitente);
        this.cpfUsuarioEmitente = cpfUsuarioEmitente;
    }

    public void setIdOutrosUsuarioEmitente(final String idOutrosUsuarioEmitente) {
        this.idOutrosUsuarioEmitente = idOutrosUsuarioEmitente;
    }

    public void setChaveAcesso(final String chaveAcesso) {
        DFStringValidador.exatamente44N(chaveAcesso, "Chave de Acesso da DC-e no Evento");
        this.chaveAcesso = chaveAcesso;
    }

    public void setDataHoraEvento(final ZonedDateTime dataHoraEvento) {
        this.dataHoraEvento = dataHoraEvento;
    }

    public void setTipoEvento(final DCeTipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento.getCodigo();
    }

    public void setTipoEvento(final String tipoEvento) {
        if (tipoEvento == null || !tipoEvento.matches("[0-9]{6}")) {
            throw new IllegalStateException("tpEvento deve ter 6 digitos numericos");
        }
        this.tipoEvento = tipoEvento;
    }

    public void setNumeroSequencialEvento(final String numeroSequencialEvento) {
        if (numeroSequencialEvento == null || !numeroSequencialEvento.matches("[0-9]{1,3}")) {
            throw new IllegalStateException("nSeqEvento deve ter 1 a 3 digitos numericos");
        }
        this.numeroSequencialEvento = numeroSequencialEvento;
    }

    public void setNumeroSequencialEvento(final int numeroSequencialEvento) {
        this.setNumeroSequencialEvento(String.valueOf(numeroSequencialEvento));
    }

    public void setDetalhamentoEvento(final DCeDetalhamentoEvento detalhamentoEvento) {
        this.detalhamentoEvento = detalhamentoEvento;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public Integer getCodigoOrgao() {
        return this.codigoOrgao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getTipoEmitente() {
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

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public ZonedDateTime getDataHoraEvento() {
        return this.dataHoraEvento;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

    public String getNumeroSequencialEvento() {
        return this.numeroSequencialEvento;
    }

    public DCeDetalhamentoEvento getDetalhamentoEvento() {
        return this.detalhamentoEvento;
    }
}
