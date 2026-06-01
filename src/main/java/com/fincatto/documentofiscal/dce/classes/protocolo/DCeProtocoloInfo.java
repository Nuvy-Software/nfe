package com.fincatto.documentofiscal.dce.classes.protocolo;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;

/**
 * Informacoes do protocolo de autorizacao da DC-e (infProt dentro de protDCe).
 */
@Root(name = "infProt")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeProtocoloInfo extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "Id", required = false)
    private String id;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "verAplic")
    private String versaoAplicacao;

    @Element(name = "chDCe")
    private String chaveAcesso;

    @Element(name = "dhRecbto")
    private ZonedDateTime dataHoraRecebimento;

    @Element(name = "nProt", required = false)
    private String numeroProtocolo;

    @Element(name = "digVal", required = false)
    private String digestValue;

    @Element(name = "cStat")
    private String status;

    @Element(name = "xMotivo")
    private String motivo;

    public String getId() {
        return this.id;
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public String getVersaoAplicacao() {
        return this.versaoAplicacao;
    }

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public ZonedDateTime getDataHoraRecebimento() {
        return this.dataHoraRecebimento;
    }

    public String getNumeroProtocolo() {
        return this.numeroProtocolo;
    }

    public String getDigestValue() {
        return this.digestValue;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMotivo() {
        return this.motivo;
    }
}
