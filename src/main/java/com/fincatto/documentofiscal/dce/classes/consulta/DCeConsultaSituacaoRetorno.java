package com.fincatto.documentofiscal.dce.classes.consulta;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.protocolo.DCeProtocolo;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Retorno do WS DCeConsulta (retConsSitDCe).
 *
 * <p>Os elementos {@code protDCe} e {@code procEventoDCe} sao opcionais e
 * retornam apenas quando o solicitante eh autor (mesmo CNPJ-base) ou
 * transportadora habilitada. Os procEventoDCe sao tratados como XML cru
 * (lista de String) devido ao uso de xs:any no schema.
 */
@Root(name = "retConsSitDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeConsultaSituacaoRetorno extends DFBase {

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
    private Integer codigoUF;

    @Element(name = "dhRecbto")
    private ZonedDateTime dataHoraRecebimento;

    @Element(name = "chDCe")
    private String chaveAcesso;

    @Element(name = "protDCe", required = false)
    private DCeProtocolo protocolo;

    @ElementList(entry = "procEventoDCe", inline = true, required = false)
    private List<String> procEventoDCe;

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

    public Integer getCodigoUF() {
        return this.codigoUF;
    }

    public ZonedDateTime getDataHoraRecebimento() {
        return this.dataHoraRecebimento;
    }

    public String getChaveAcesso() {
        return this.chaveAcesso;
    }

    public DCeProtocolo getProtocolo() {
        return this.protocolo;
    }

    public List<String> getProcEventoDCe() {
        return this.procEventoDCe;
    }
}
