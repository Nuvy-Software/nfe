package com.fincatto.documentofiscal.dce.classes.procDCe;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.protocolo.DCeProtocolo;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * DC-e processada (dceProc = DCe + protDCe). Elemento gerado apos a
 * autorizacao para arquivamento.
 */
@Root(name = "dceProc")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeProcessada extends DFBase {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "versao")
    private String versao = DCeConfig.VERSAO;

    @Attribute(name = "ipTransmissor", required = false)
    private String ipTransmissor;

    @Attribute(name = "nPortaCon", required = false)
    private String portaConexao;

    @Attribute(name = "dhConexao", required = false)
    private String dataHoraConexao;

    @Element(name = "DCe")
    private DCe dce;

    @Element(name = "protDCe")
    private DCeProtocolo protocolo;

    public void setDce(final DCe dce) {
        this.dce = dce;
    }

    public void setProtocolo(final DCeProtocolo protocolo) {
        this.protocolo = protocolo;
    }

    public String getVersao() {
        return this.versao;
    }

    public DCe getDce() {
        return this.dce;
    }

    public DCeProtocolo getProtocolo() {
        return this.protocolo;
    }

    public String getIpTransmissor() {
        return this.ipTransmissor;
    }

    public String getPortaConexao() {
        return this.portaConexao;
    }

    public String getDataHoraConexao() {
        return this.dataHoraConexao;
    }
}
