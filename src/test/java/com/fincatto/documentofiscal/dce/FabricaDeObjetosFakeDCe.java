package com.fincatto.documentofiscal.dce;

import com.fincatto.documentofiscal.DFAmbiente;
import com.fincatto.documentofiscal.DFUnidadeFederativa;
import com.fincatto.documentofiscal.dce.classes.DCe;
import com.fincatto.documentofiscal.dce.classes.DCeInfo;
import com.fincatto.documentofiscal.dce.classes.autxml.DCeAutorizadoXML;
import com.fincatto.documentofiscal.dce.classes.declaracao.DCeDeclaracaoEmitente;
import com.fincatto.documentofiscal.dce.classes.dest.DCeDestinatario;
import com.fincatto.documentofiscal.dce.classes.detalhamento.DCeDetalhamento;
import com.fincatto.documentofiscal.dce.classes.detalhamento.DCeProduto;
import com.fincatto.documentofiscal.dce.classes.emit.DCeEmitente;
import com.fincatto.documentofiscal.dce.classes.endereco.DCeEnderecoDestinatario;
import com.fincatto.documentofiscal.dce.classes.endereco.DCeEnderecoEmitente;
import com.fincatto.documentofiscal.dce.classes.evento.DCeDetalhamentoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeInfoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.DCeTipoEvento;
import com.fincatto.documentofiscal.dce.classes.evento.cancelamento.DCeEventoCancelamento;
import com.fincatto.documentofiscal.dce.classes.ide.DCeIdentificacao;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeModalidadeTransporte;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;
import com.fincatto.documentofiscal.dce.classes.total.DCeTotal;
import com.fincatto.documentofiscal.dce.classes.transporte.DCeTransporte;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

/**
 * Fabrica de objetos DC-e para testes unitarios.
 * Produz objetos validos que devem passar pela validacao do XSD oficial.
 */
public final class FabricaDeObjetosFakeDCe {

    private FabricaDeObjetosFakeDCe() {
    }

    public static DCeEnderecoEmitente getEnderecoEmitente() {
        final DCeEnderecoEmitente e = new DCeEnderecoEmitente();
        e.setLogradouro("Rua Sao Paulo");
        e.setNumero("123");
        e.setComplemento("Sala 1");
        e.setBairro("Centro");
        e.setCodigoMunicipio("4106902");
        e.setDescricaoMunicipio("Curitiba");
        e.setUf("PR");
        e.setCep("80010000");
        e.setTelefone("4133331111");
        return e;
    }

    public static DCeEnderecoDestinatario getEnderecoDestinatario() {
        final DCeEnderecoDestinatario e = new DCeEnderecoDestinatario();
        e.setLogradouro("Avenida Brasil");
        e.setNumero("456");
        e.setBairro("Centro");
        e.setCodigoMunicipio("3550308");
        e.setDescricaoMunicipio("Sao Paulo");
        e.setUf("SP");
        e.setCep("01000000");
        e.setCodigoPais("1058");
        e.setDescricaoPais("Brasil");
        return e;
    }

    public static DCeEmitente getEmitente() {
        final DCeEmitente emit = new DCeEmitente();
        emit.setCnpj("12345678000195");
        emit.setRazaoSocial("Empresa Emitente DC-e LTDA");
        emit.setEndereco(getEnderecoEmitente());
        return emit;
    }

    public static DCeDestinatario getDestinatario() {
        final DCeDestinatario dest = new DCeDestinatario();
        dest.setCpf("12345678909");
        dest.setRazaoSocial("Jose da Silva");
        dest.setEndereco(getEnderecoDestinatario());
        return dest;
    }

    public static DCeIdentificacao getIdentificacao() {
        final DCeIdentificacao ide = new DCeIdentificacao();
        ide.setCodigoUF(DFUnidadeFederativa.PR);
        ide.setCodigoNumerico("123456");
        ide.setSerie("1");
        ide.setNumero("1");
        ide.setDataHoraEmissao(ZonedDateTime.of(2025, 1, 15, 10, 30, 0, 0, ZoneOffset.ofHours(-3)));
        ide.setTipoEmissao(DCeTipoEmissao.NORMAL);
        ide.setTipoEmitente(DCeTipoEmitente.EMISSOR_PROPRIO);
        ide.setNumeroSiteAutorizador("0");
        ide.setDigitoVerificador("5");
        ide.setAmbiente(DFAmbiente.HOMOLOGACAO);
        ide.setVersaoProcesso("nuvy-1.0");
        return ide;
    }

    public static DCeDetalhamento getDetalhamento() {
        final DCeProduto prod = new DCeProduto();
        prod.setDescricao("Livro impresso");
        prod.setNcm("49019900");
        prod.setQuantidade(new BigDecimal("2.0000"));
        prod.setValorUnitario(new BigDecimal("50.00"));
        prod.setValorTotalBruto(new BigDecimal("100.00"));

        final DCeDetalhamento det = new DCeDetalhamento();
        det.setNumeroItem(1);
        det.setProduto(prod);
        return det;
    }

    public static DCeTotal getTotal() {
        final DCeTotal total = new DCeTotal();
        total.setValorTotalDeclaracao(new BigDecimal("100.00"));
        return total;
    }

    public static DCeTransporte getTransporte() {
        final DCeTransporte t = new DCeTransporte();
        t.setModalidade(DCeModalidadeTransporte.EMPRESA_TRANSPORTADORA);
        t.setCnpjTransportador("98765432000100");
        return t;
    }

    public static DCeDeclaracaoEmitente getDeclaracao() {
        return new DCeDeclaracaoEmitente();
    }

    /**
     * Monta um objeto {@link DCe} completo, com infDCe.Id calculado a partir da
     * chave de exemplo abaixo (DV ja correto).
     */
    public static DCe getDCeCompleta() {
        final DCeInfo info = new DCeInfo();
        info.setIdentificador("41250112345678000195990010000000011000123456");
        info.setIdentificacao(getIdentificacao());
        info.setEmitente(getEmitente());
        info.setDestinatario(getDestinatario());
        info.setAutorizadosXML(Collections.singletonList(getAutorizadoXML()));
        info.setDetalhamentos(Collections.singletonList(getDetalhamento()));
        info.setTotal(getTotal());
        info.setTransporte(getTransporte());
        info.setDeclaracao(getDeclaracao());

        final DCe dce = new DCe();
        dce.setInfo(info);
        return dce;
    }

    public static DCeAutorizadoXML getAutorizadoXML() {
        final DCeAutorizadoXML aut = new DCeAutorizadoXML();
        aut.setCnpj("11222333000181");
        return aut;
    }

    public static DCeEventoCancelamento getEventoCancelamento() {
        final DCeEventoCancelamento canc = new DCeEventoCancelamento();
        canc.setNumeroProtocolo("1412500000000010");
        canc.setJustificativa("Cancelamento por erro de digitacao no destinatario do envio");
        return canc;
    }

    public static DCeEvento getEvento() {
        final DCeInfoEvento info = new DCeInfoEvento();
        info.setCodigoOrgao(41);
        info.setAmbiente(DFAmbiente.HOMOLOGACAO);
        info.setTipoEmitente("2");
        info.setCnpjAutor("12345678000195");
        info.setCnpjUsuarioEmitente("12345678000195");
        info.setChaveAcesso("41250112345678000195990010000000011000123456");
        info.setDataHoraEvento(ZonedDateTime.of(2025, 1, 15, 11, 0, 0, 0, ZoneOffset.ofHours(-3)));
        info.setTipoEvento(DCeTipoEvento.CANCELAMENTO);
        info.setNumeroSequencialEvento(1);
        info.setIdentificador("110111412501123456780001959900100000000110001234560001"
                .substring(0, 53));
        final DCeDetalhamentoEvento det = new DCeDetalhamentoEvento();
        det.setEventoCancelamento(getEventoCancelamento());
        info.setDetalhamentoEvento(det);

        final DCeEvento evento = new DCeEvento();
        evento.setInfo(info);
        return evento;
    }
}
