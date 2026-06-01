package com.fincatto.documentofiscal.dce.classes.envio;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.protocolo.DCeProtocolo;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Retorno do WS DCeAutorizacao (retDCe).
 */
@Root(name = "retDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeEnvioRetorno extends DFBase {

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

    @Element(name = "dhRecbto", required = false)
    private ZonedDateTime dataHoraRecebimento;

    @Element(name = "protDCe", required = false)
    private DCeProtocolo protocolo;

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

    public DCeProtocolo getProtocolo() {
        return this.protocolo;
    }
}
