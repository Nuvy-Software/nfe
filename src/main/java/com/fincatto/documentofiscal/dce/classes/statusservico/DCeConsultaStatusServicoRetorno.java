package com.fincatto.documentofiscal.dce.classes.statusservico;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.DCeConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Retorno do WS DCeStatusServico (retConsStatServDCe).
 */
@Root(name = "retConsStatServDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeConsultaStatusServicoRetorno extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "verAplic")
    private String versaoAplicacao;

    @Element(name = "cStat")
    private String status;

    @Element(name = "xMotivo")
    private String motivo;

    @Element(name = "cUF")
    private DFUnidadeFederativa codigoUF;

    @Element(name = "dhRecbto")
    private ZonedDateTime dataHoraRecebimento;

    @Element(name = "tMed", required = false)
    private Integer tempoMedio;

    @Element(name = "dhRetorno", required = false)
    private ZonedDateTime dataHoraRetorno;

    @Element(name = "xObs", required = false)
    private String observacao;

    public String getVersao() {
        return this.versao;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public DFUnidadeFederativa getCodigoUF() {
        return this.codigoUF;
    }

    public ZonedDateTime getDataHoraRecebimento() {
        return this.dataHoraRecebimento;
    }

    public Integer getTempoMedio() {
        return this.tempoMedio;
    }

    public ZonedDateTime getDataHoraRetorno() {
        return this.dataHoraRetorno;
    }

    public String getObservacao() {
        return this.observacao;
    }
}
