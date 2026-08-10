package com.fincatto.documentofiscal.dce.classes.protocolo;

import com.fincatto.documentofiscal.DFBase;
import com.fincatto.documentofiscal.nfe310.classes.nota.assinatura.NFSignature;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Protocolo de status da DC-e (protDCe = TProtDCe).
 */
@Root(name = "protDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeProtocolo extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "infProt")
    private DCeProtocoloInfo info;

    @Element(name = "Signature", required = false)
    private NFSignature signature;

    public DCeProtocoloInfo getInfo() {
        return this.info;
    }

    public NFSignature getSignature() {
        return this.signature;
    }

    public void setInfo(final DCeProtocoloInfo info) {
        this.info = info;
    }

    public void setSignature(final NFSignature signature) {
        this.signature = signature;
    }
}
