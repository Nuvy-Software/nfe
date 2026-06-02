# Changelog

Formato baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/).
Este changelog registra apenas as mudanças do fork Nuvy em relação ao
upstream [`wmixvideo/nfe`](https://github.com/wmixvideo/nfe). Para
histórico do upstream consulte as releases do projeto original.

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
