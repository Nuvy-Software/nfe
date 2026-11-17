# Changelog

Formato baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/).
Este changelog registra apenas as mudanças do fork Nuvy em relação ao
upstream [`wmixvideo/nfe`](https://github.com/wmixvideo/nfe). Para
histórico do upstream consulte as releases do projeto original.

## [5.0.71-nuvy] - 2026-11-17

### Corrigido
- `SCAN` (SVC-AN) em `NFAutorizador400` e `NFAutorizador31` ainda apontava para
  `www.svc.fazenda.gov.br`/`hom.svc.fazenda.gov.br`, desligados na unificação
  SVAN/SVC-AN (homologação em 16/07/2024, produção em 11/02/2025) e hoje sem DNS
  (NXDOMAIN). Passa a usar `www.sefazvirtual.fazenda.gov.br`/`hom.sefazvirtual...`.
  A emissão em contingência já ia para a `SVAN` desde o upstream #1106; o efeito
  visível era o `DFCadeiaCertificados`, que percorre todos os autorizadores e
  registrava 2 ERROR de `UnknownHostException` por dia na regeneração da cadeia.

## [5.0.70-nuvy] - 2026-11-16

### Corrigido
- `DFCadeiaCertificados` registrava ERROR em todo host cujo handshake falhava,
  mesmo quando a cadeia já tinha sido capturada (ela é guardada antes da
  validação). Eram ~34 ERROR falsos por dia na regeneração da cadeia: keystore
  ainda vazio no primeiro host, CA ainda não vista (PKIX) e servidores que
  exigem certificado cliente (`bad_certificate`, `handshake_failure`,
  `certificate_unknown`). Agora só é ERROR quando nenhuma cadeia foi obtida; o
  resto vai para DEBUG.

## [5.0.69-nuvy] - 2026-08-10

### Corrigido
- CNPJ alfanumérico (NT 2026.004) na DC-e, alinhando o tratamento ao que já
  havia sido aplicado ao MDF-e. O CNPJ ocupa as posições 7-20 da chave de
  acesso e dois pontos o rejeitavam:
  - cálculo do dígito verificador da chave passou a usar conversão por valor
    de char (ASCII - 48), que cobre dígitos e letras A-Z, no lugar de
    `Integer.parseInt`, que lançava `NumberFormatException`;
  - chave de acesso do identificador, da consulta de situação e do evento
    passou a ser validada por comprimento apenas, no lugar da variante que
    também exigia caracteres numéricos.

  Os pacotes de schema empacotados (`PL_DCe_v1.00a_NT2024.001`) ainda
  restringem CNPJ e chave a caracteres numéricos, portanto a validação
  contra XSD segue rejeitando CNPJ alfanumérico até que a SEFAZ publique o
  pacote adequado para a DC-e.

## [5.0.68-nuvy] - 2026-08-10

### Adicionado
- Suporte a `KeyManager` na geração da cadeia de certificados, viabilizando
  handshake TLS com certificado cliente.

### Corrigido
- Rejeição 999 na autorização da DC-e: o payload compactado montado pelo
  `WSAutorizacao` era reembrulhado no elemento `dceDadosMsg`.

### Modificado
- Base sincronizada com o upstream `v5.0.68`, incorporando CT-e Simplificado
  (tpCTe=5, NT 2024.002), CNPJ alfanumérico (NT 2026.004) e correções de MDF-e.
- Histórico do fork reorganizado em commits temáticos sobre o upstream.

## [5.0.55-nuvy] - 2026-05-20

### Adicionado
- Suporte completo a DC-e — Declaração de Conteúdo eletrônica (modelo fiscal 99, Ajuste SINIEF 05/2021).
- Arquivo `NOTICE` com atribuição ao projeto original.
- Identidade Nuvy: groupId `com.github.Nuvy-Software`, SCM, developers.
- `jitpack.yml` fixando JDK 11 no build do JitPack.

### Modificado
- Publicação migrada para JitPack (sem Maven Central / Sonatype).
- README reescrito com foco no fork e instruções de instalação via JitPack.

### Removido
- Workflows de release acoplados ao upstream (`release.yml`, `release-tag.yml`).
- Profiles Maven `ossrh`, `github` e `deploy` do `pom.xml`.
- Plugin Coveralls (token do projeto original) e step `Coverage tests` do CI.
- Link para o grupo de WhatsApp do projeto original no README.
