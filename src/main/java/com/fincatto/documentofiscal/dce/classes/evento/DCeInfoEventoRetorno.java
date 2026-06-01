package com.fincatto.documentofiscal.dce.classes.evento;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Informacoes do retorno do evento (infEvento dentro de retEventoDCe).
 */
@Root(name = "infEvento")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInfoEventoRetorno extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "Id", required = false)
    private String id;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "verAplic")
    private String versaoAplicacao;

    @Element(name = "cOrgao")
    private Integer codigoOrgao;

    @Element(name = "cStat")
    private String status;

    @Element(name = "xMotivo")
    private String motivo;

    @Element(name = "chDCe", required = false)
    private String chaveAcesso;

    @Element(name = "tpEvento", required = false)
    private String tipoEvento;

    @Element(name = "xEvento", required = false)
    private String descricaoEvento;

    @Element(name = "nSeqEvento", required = false)
    private String numeroSequencialEvento;

    @Element(name = "dhRegEvento", required = false)
    private ZonedDateTime dataHoraRegistroEvento;

    @Element(name = "nProt", required = false)
    private String numeroProtocolo;

    public String getId() {
        return this.id;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public Integer getCodigoOrgao() {
        return this.codigoOrgao;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

    public String getDescricaoEvento() {
        return this.descricaoEvento;
    }

    public String getNumeroSequencialEvento() {
        return this.numeroSequencialEvento;
    }

    public ZonedDateTime getDataHoraRegistroEvento() {
        return this.dataHoraRegistroEvento;
    }

    public String getNumeroProtocolo() {
        return this.numeroProtocolo;
    }
}
