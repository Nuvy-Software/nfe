NF-e (Nuvy Fork)
===

Comunicador de nota fiscal eletrônica, nota fiscal do consumidor e declaração de conteúdo eletrônica (DC-e) da [fazenda](http://www.nfe.fazenda.gov.br/portal/principal.aspx).

[![Apache 2.0 License](https://img.shields.io/badge/license-apache%202.0-green.svg)](https://github.com/Nuvy-Software/nfe/blob/master/LICENSE)
[![](https://jitpack.io/v/Nuvy-Software/nfe.svg)](https://jitpack.io/#Nuvy-Software/nfe)

## Sobre este fork

Este é um fork de [wmixvideo/nfe](https://github.com/wmixvideo/nfe), mantido pela [Nuvy](https://github.com/Nuvy-Software), com features adicionais e ciclo de release independente publicado via JitPack.

**Diferenças em relação ao upstream:**

- Suporte completo a **DC-e — Declaração de Conteúdo eletrônica** (modelo fiscal 99, Ajuste SINIEF 05/2021)
- Publicação via JitPack a partir de tags `*-nuvy` (ex.: `5.0.55-nuvy`)
- Pipeline simplificada (sem Maven Central / Sonatype / GPG)

Todo o crédito do trabalho original vai para Diego Fincatto e contribuidores do projeto upstream. Veja [`NOTICE`](NOTICE) e [`CHANGELOG.md`](CHANGELOG.md).

## Antes de usar

Antes de começar a implementar, é altamente recomendável a leitura da documentação oficial que o governo disponibiliza em http://www.nfe.fazenda.gov.br/portal

Caso não possua conhecimento técnico para criar notas fiscais, um profissional da área (como um contador) pode lhe auxiliar.

## Instalação

### Via JitPack (Maven)

Adicione o repositório JitPack e a dependência ao seu `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.Nuvy-Software</groupId>
    <artifactId>nfe</artifactId>
    <version>5.0.55-nuvy</version>
</dependency>
```

### Via JitPack (Gradle)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Nuvy-Software:nfe:5.0.55-nuvy'
}
```

Para a lista de versões disponíveis, consulte as [releases](https://github.com/Nuvy-Software/nfe/releases) ou a [página JitPack do projeto](https://jitpack.io/#Nuvy-Software/nfe). Também é possível referenciar branches (`master-SNAPSHOT`) ou commits específicos.

### Build local (última versão em desenvolvimento)

```console
git clone https://github.com/Nuvy-Software/nfe
cd nfe
mvn clean install
```

## Como usar

Basicamente você precisará de uma implementação de **NFeConfig** (exemplificado abaixo), com informações de tipo de emissão, certificados digitais, e uma instância da **WsFacade**, essa classe tem a responsabilidade de fazer a ponte entre o seu sistema e a comunicação com os webservices da Sefaz.

```java
// Exemplo de configuracao para acesso aos serviços da Sefaz.
public class NFeConfigTeste extends NFeConfig {

    private KeyStore keyStoreCertificado = null;
    private KeyStore keyStoreCadeia = null;

    @Override
    public DFUnidadeFederativa getCUF() {
        return DFUnidadeFederativa.SC;
    }

    @Override
    public String getCertificadoSenha() {
        return "senha_certificado";
    }

    @Override
    public String getCadeiaCertificadosSenha() {
        return "senha_cadeia";
    }

    @Override
    public KeyStore getCertificadoKeyStore() throws KeyStoreException {
        if (this.keyStoreCertificado == null) {
            this.keyStoreCertificado = KeyStore.getInstance("PKCS12");
            try (InputStream certificadoStream = new FileInputStream("/tmp/certificado.pfx")) {
                this.keyStoreCertificado.load(certificadoStream, this.getCertificadoSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possibel montar o KeyStore com a cadeia de certificados", e);
            }
        }
        return this.keyStoreCertificado;
    }

    @Override
    public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
        if (this.keyStoreCadeia == null) {
            this.keyStoreCadeia = KeyStore.getInstance("JKS");
            try (InputStream cadeia = new FileInputStream("/tmp/cadeia.jks")) {
                this.keyStoreCadeia.load(cadeia, this.getCadeiaCertificadosSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possibel montar o KeyStore com o certificado", e);
            }
        }
        return this.keyStoreCadeia;
    }
}
```
### Configurar pelo repositório de certificados do Windows:

#### Carregando os alias disponíveis(certificados instalados) no Windows:
```java
KeyStore keyStoreCert = KeyStore.getInstance("Windows-MY", "SunMSCAPI"); 
keyStoreCert.load(null, null);
Enumeration<String> aliasEnum = keyStoreCert.aliases();
```

Após isso é necessário algum método para o usuário escolher entre um destes alias, 
talvez por meio de um JOptionPane(<java7)
 ou de um ChoiceDialog<String>(>=Java8), e então fazer o load com a respectivo alias escolhido e sua senha.

### Alguns exemplos
Considere para os exemplos abaixo que **config** seja uma instância da implementação da interface **NFeConfig**.

#### Status dos webservices
```java
NFStatusServicoConsultaRetorno retorno = new WSFacade(config).consultaStatus(DFUnidadeFederativa.SC, DFModelo.NFE);
System.out.println(retorno.getStatus());
System.out.println(retorno.getMotivo());
```

O Resultado será (caso o webservice responsável por SC esteja OK):
```
107
Servico em operacao
```

#### Envio do lote para o sefaz
Popule os dados do lote a ser enviado para o Sefaz:

```java
NFLoteEnvio lote = new NFLoteEnvio();
// setando os dados do lote
```

Faça o envio do lote através do facade:
```java
final NFLoteEnvioRetorno retorno = new WSFacade(config).enviaLote(lote);
```

#### Corrige nota
Faça a correção da nota através do facade:
```java
final NFEnviaEventoRetorno retorno = new WSFacade(config).corrigeNota(chaveDeAcessoDaNota, textoCorrecao, sequencialEventoDaNota);
```

#### Cancela nota
Faça o cancelamento da nota através do facade:
```java
final NFCancelamentoRetornoDados retorno = new WSFacade(config).cancelaNota(chaveDeAcessoDaNota, protocoloDaNota, motivoCancelaamento);
```

#### Consulta nota por chave de acesso ou NSU
Faça a consulta da nota através do facade:
```java
final NFDistribuicaoIntRetorno retorno = new WSFacade(config).consultarDistribuicaoDFe(cnpj, uf, chaveAcesso, nsu, ultNsu);
```

#### Consulta CTe
Faça a consulta do CTe através do CTDistribuicaoInt:
```java
final CTDistribuicaoInt distDFeInt = new CTDistribuicaoInt();
distDFeInt.setVersao("1.00");
 distDFeInt.setAmbiente(isTeste() ? DFAmbiente.HOMOLOGACAO : DFAmbiente.PRODUCAO);
distDFeInt.setUnidadeFederativaAutor(DFUnidadeFederativa.valueOf("SC"));
distDFeInt.setCnpj("60436332000145");
distDFeInt.setDistribuicao(new CTDistribuicaoNSU().setUltimoNSU("000000000036552");

final String retornoConsulta = WSDistribuicaoCTe.consultar(distDFeInt, new NFeConfig());
```

### Convertendo objetos Java em XML
Qualquer objeto que seja uma representação XML do documento NFe, pode ser obtido seu XML de forma fácil bastando chamar o método **toString**, por exemplo, para conseguir o XML do lote, invoque o toString

```java
NFLoteEnvio lote = new NFLoteEnvio();
// setando os dados do lote
...

// Obtendo o xml do objeto
String xmlGerado = lote.toString();
```

### Convertendo nota XML em Java
Existe uma classe que pode receber um File/String e converter para um objeto NFNota, faça da seguinte forma:
```java
final NFNota nota = new DFPersister().read(NFNota.class, xmlNota);
final NFNotaProcessada notaProcessada = new DFPersister().read(NFNotaProcessada.class, xmlNotaProcessada);
```

Ou desabilitando o modo estrito (habilitado por padrão):
```java
final NFNota nota = new DFPersister(false).read(NFNota.class, xmlNota);
```

### Armazenando notas autorizadas
Você precisará armazenar as notas autorizadas por questões legais e também para a geração do DANFE, uma forma de fazer é armazenar o xml das notas ao enviar o lote:
```java
final List<NFNota> notas = lote.getNotas();
// Armazena os xmls das notas
...
```
Ao fazer a consulta do lote, crie um objeto do tipo **NFNotaProcessada** e adicione o protocolo da nota correspondente, além da nota assinada:
```java
// Carregue o xml da nota do local que foi armazenado
final String xmlNotaRecuperada;
// Assine a nota
final String xmlNotaRecuperadaAssinada = new AssinaturaDigital(config).assinarDocumento(xmlNotaRecuperada);
// Converta para objeto java
final NFNota notaRecuperadaAssinada = new DFPersister().read(NFNota.class, xmlNotaRecuperadaAssinada);
// Crie o objeto NFNotaProcessada
final NFNotaProcessada notaProcessada = new NFNotaProcessada();
notaProcessada.setVersao(new BigDecimal(NFeConfig.VERSAO_NFE));
notaProcessada.setProtocolo(protocolo);
notaProcessada.setNota(notaRecuperadaAssinada);
// Obtenha o xml da nota com protocolo
String xmlNotaProcessadaPeloSefaz = notaProcessada.toString();
```

### Funcionalidades
* Possui validação de campos a nível de código;
* Valida o XML de envio de lote através dos xsd's disponibilizados pela Sefaz;
* Gera o XML dos objetos de maneira simples, invocando o metodo toString() dá conta do recado.
* Suporta diferentes TimeZones, com a implementacao do devido metodo na classe de configuração (DFConfig e suas subclasses).

## Serviços disponíveis
| Serviço                       | Status              |
| ----------------------------- | :-----------------: |
| Envio lote                    | Estável             |
| Consulta lote                 | Estável             |
| Consulta status               | Estável             |
| Consulta nota                 | Estável             |
| Download nota                 | Estável             |
| Corrige nota                  | Estável             |
| Cancela nota                  | Estável             |
| Inutiliza nota                | Estável             |
| Consulta cadastro             | Estável             |
| Manifestação de destinatário  | Estável             |

## DC-e — Declaração de Conteúdo eletrônica

Modelo fiscal 99, instituído pelo Ajuste SINIEF 05/2021. Substitui a declaração de conteúdo em papel no transporte de bens e mercadorias quando não é exigido documento fiscal.

A DC-e segue um modelo de **autorizador único nacional**, hospedado pela SEFAZ-PR (`dce.fazenda.pr.gov.br`), atendendo todas as UFs participantes. Web Services suportados (versão 1.00):

| Serviço                       | Status              |
| ----------------------------- | :-----------------: |
| Status do serviço             | Implementado        |
| Autorização (envio síncrono)  | Implementado        |
| Consulta de situação          | Implementado        |
| Recepção de evento            | Implementado        |
| Cancelamento (evento 110111)  | Implementado        |
| Geração de QR-Code para DACE  | Implementado        |
| Serviço de Distribuição (SVD) | Não suportado       |

Exemplo mínimo de uso:

```java
public class MinhaDCeConfig extends DCeConfig {
    // ... implementar getCertificadoKeyStore(), getCertificadoSenha(),
    //     getCadeiaCertificadosKeyStore(), getCadeiaCertificadosSenha(), getCUF(),
    //     getAmbiente() (PRODUCAO ou HOMOLOGACAO)
}

DCeConfig config = new MinhaDCeConfig();
WSFacade facade = new WSFacade(config);

// Consulta status do serviço
DCeConsultaStatusServicoRetorno status = facade.consultaStatus();

// Emissão de DC-e
DCe dce = /* preencher infDCe, ide, emit, dest, det, total, transp, infDec */;
new DCeGeraChave(dce).getChaveAcesso();
DCeEnvioRetornoDados r = facade.envia(dce);

// Cancelamento (até 24h após autorização)
facade.cancela(chaveAcesso, numeroProtocolo, "Justificativa com no mínimo 15 caracteres");
```

Schemas oficiais (Pacote de Liberação PL_DCe_v1.00a + NT2024.001) já incluídos em `resources/schemas/PL_DCe_v1.00a_NT2024.001/`. Validação automática contra XSD em todos os envios via `DFXMLValidador`.

## Requisitos

JDK >= 11<br>
Maven >= 3.x

## Criação do Java KeyStore (JKS)
Para usar os serviços da nota fiscal são necessários dois certificados:
1) O certificado do cliente que será utilizado para assinar as notas e comunicar com o fisco (fornecido por uma entidade certificadora);
2) A cadeia de certificados da SEFAZ que queremos acesso;

Os certificados são um ponto crítico já que estes tem validade de apenas um ano (certificado cliente).
Além disso as SEFAZ vem trocando suas cadeias de certificado a cada atualização. Dessa forma se surgirem erros de SSL vale a pena verificar se existem novas atualizações de certificados.
Para gerar a cadeia de certificados, disponibilizamos um pequeno helper que baixa os certificados das SEFAZ e gera o arquivo automaticamente:
```java
public static void main(String args[]){
    try {
        FileUtils.writeByteArrayToFile(new File("/tmp/producao.cacerts"), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.PRODUCAO, "senha"));
        FileUtils.writeByteArrayToFile(new File("/tmp/homologacao.cacerts"), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.HOMOLOGACAO, "senha"));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

## Licença

Distribuído sob [Apache License 2.0](LICENSE). Mantém a licença e atribuição do projeto original — veja [`NOTICE`](NOTICE).
