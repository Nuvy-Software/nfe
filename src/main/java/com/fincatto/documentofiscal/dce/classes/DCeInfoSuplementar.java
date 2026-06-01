package com.fincatto.documentofiscal.dce.classes;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Informacoes Suplementares da DC-e (Grupo ZX - infDCeSupl).
 * Contem o QR-Code e a URL de consulta da chave.
 */
@Root(name = "infDCeSupl")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInfoSuplementar extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "qrCodDCe")
    private String qrCodeDCe;

    @Element(name = "urlChave")
    private String urlChave;

    public void setQrCodeDCe(final String qrCodeDCe) {
        if (qrCodeDCe == null || qrCodeDCe.length() < 94 || qrCodeDCe.length() > 600) {
            throw new IllegalStateException("qrCodDCe deve ter 94 a 600 caracteres");
        }
        this.qrCodeDCe = qrCodeDCe;
    }

    public void setUrlChave(final String urlChave) {
        if (urlChave == null || urlChave.length() < 21 || urlChave.length() > 85) {
            throw new IllegalStateException("urlChave deve ter 21 a 85 caracteres");
        }
        this.urlChave = urlChave;
    }

    public String getQrCodeDCe() {
        return this.qrCodeDCe;
    }

    public String getUrlChave() {
        return this.urlChave;
    }
}
