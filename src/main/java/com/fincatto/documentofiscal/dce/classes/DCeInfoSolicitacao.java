package com.fincatto.documentofiscal.dce.classes;

import com.fincatto.documentofiscal.DFBase;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Informacoes do Pedido de Emissao da DC-e pelo Fisco (Grupo ZB - infSolicDCe).
 * Usado quando a DC-e e solicitada pelo Fisco.
 */
@Root(name = "infSolicDCe")
@Namespace(reference = "http://www.portalfiscal.inf.br/dce")
public class DCeInfoSolicitacao extends DFBase {

    private static final long serialVersionUID = 1L;

    @Element(name = "xSolic")
    private String solicitacao;

    public void setSolicitacao(final String solicitacao) {
        if (solicitacao != null && solicitacao.length() > 2000) {
            throw new IllegalStateException("xSolic deve ter no maximo 2000 caracteres");
        }
        this.solicitacao = solicitacao;
    }

    public String getSolicitacao() {
        return this.solicitacao;
    }
}
