package com.fincatto.documentofiscal.dce.utils;

import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.DCeConfig;
import com.fincatto.documentofiscal.dce.FabricaDeObjetosFakeDCe;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyStore;

public class DCeGeraQRCodeTest {

    /**
     * Em emissao normal (tpEmis=1) o QR-Code nao e assinado, entao a montagem da URL
     * depende apenas da chave de acesso. Com CNPJ alfanumerico (NT 2026.004) a chave
     * carrega letras nas posicoes 7-20 e deve trafegar intacta na URL.
     */
    @Test
    public void deveMontarUrlComChaveAlfanumericaEmEmissaoNormal() throws Exception {
        final DCe dce = FabricaDeObjetosFakeDCe.getDCeCompleta();
        dce.getInfo().getEmitente().setCnpj("AB12CD34EF5602");
        dce.getInfo().getIdentificacao().setTipoEmissao(DCeTipoEmissao.NORMAL);
        new DCeGeraChave(dce).preencher();

        final String chave = dce.getInfo().getChaveAcesso();
        final String url = new DCeGeraQRCode(dce, new DCeConfigTeste()).getQRCode();

        Assert.assertTrue("URL deve conter a chave de acesso", url.contains("chDCe=" + chave));
        Assert.assertTrue("chave deve preservar o CNPJ alfanumerico", url.contains("AB12CD34EF5602"));
        Assert.assertTrue("URL deve conter o ambiente", url.contains("tpAmb=2"));
        Assert.assertFalse("emissao normal nao deve assinar o QR-Code", url.contains("&sign="));
    }

    /**
     * Config minima de teste: o certificado nao e acessado em emissao normal,
     * entao os metodos de keystore podem retornar null.
     */
    public static class DCeConfigTeste extends DCeConfig {

        @Override
        public DFUnidadeFederativa getCUF() {
            return DFUnidadeFederativa.PR;
        }

        @Override
        public KeyStore getCertificadoKeyStore() {
            return null;
        }

        @Override
        public String getCertificadoSenha() {
            return null;
        }

        @Override
        public KeyStore getCadeiaCertificadosKeyStore() {
            return null;
        }

        @Override
        public String getCadeiaCertificadosSenha() {
            return null;
        }
    }
}
