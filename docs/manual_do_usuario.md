# IdScan

# *Manual de Instalação*
## Requisitos mínimos de Hardware:
 - **Processador:** CPU quad-core de 64 bits com suporte a instruções AVX
 - **Memória RAM:** 8 GB
 - **Espaço em disco:** 10 GB de espaço livre para instalação e armazenamento de modelos
 - **Placa de vídeo:** Gráficos integrados são suficientes, mas uma GPU dedicada é recomendada para melhor desempenho
 - **Sistema Operacional:** Windows 10 ou superior; distribuição Linux compatível com JavaFX e Tesseract.

## Java versão 17
Instale a versão correta do Java para que o aplicativo funcione corretamente
### Para sistemas Linux:
```
sudo apt update
sudo apt install openjdk-17-jdk
```
### Para sistemas windows:
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

## Maven:
Istale o Maven para automação de compilação e gerenciamento de projetos
### Para sistemas Linux:
```
sudo apt update
sudo apt install maven
```
### Para sistemas Windows:
https://maven.apache.org/download.cgi
baixar o arquivo "apache-maven-3.9.9-bin.zip"
siga as instruções: https://medium.com/@gauravshah97/how-to-install-maven-on-windows-39ff317e40cf


## Instalar Tesseract
### Linux
```
sudo apt install tesseract-ocr
sudo apt install tesseract-ocr-por
```

### Windows
Baixar e executar o arquivo .exe: https://github.com/UB-Mannheim/tesseract/wiki

#### adicionar Tesseract ao PATH:
 - Pressione `Windows + R`, digite `sysdm.cpl` e pressione Enter
 - Na aba Avançado, clique no botão Variáveis de Ambiente
 - Na seção Variáveis do sistema, encontre a variável chamada `Path`, selecione-a e clique em Editar
 - Clique em `Novo` e adicione o caminho completo para a pasta onde o Tesseract está instalado. O caminho geralmente será algo como: `C:\Program Files\Tesseract-OCR\`


## Instalar Ollama
Ollama é um conjunto de ferramentas voltadas para a integração e execução de modelos de IA.
Linux:
```
sudo apt update
curl -fsSL https://ollama.com/install.sh | sh
```
windows:
https://ollama.com/download/windows

## instalar modelos ollama:
ollama pull [nome-do-modelo]
```
ollama pull gemma2:2b
ollama pull moondream
ollama pull minicpm-v
```
## Certifique-se de que o Ollama está rodando
Para verificar se o Ollama está rodando, acesse http://localhost:11434/
Você deverá ver a mensagem: `Ollama is running`
Caso contrário, siga os passos abaixo:

### Linux
Verifique o status do serviço Ollama:
```
systemctl status ollama.service
```
Se o serviço estiver inativo (Innactive), inicie-o:
```
systemctl start ollama.service
```
Quando terminar de usar a aplicação, você pode encerrar o serviço Ollama:
```
systemctl stop ollama.service
```

### Windows:
No terminal de comando do Windows, inicie o Ollama:
```
ollama serve
```

Para encerrar o Ollama, basta fechar o terminal ou encerrar o processo com `ctrl+c`


## clone o repositório e execute o projeto
```
git clone https://github.com/Titus-System/2semestre-ADS.git
cd 2semestre-ADS
mvn clean compile
mvn exec:java
```

# *Manual do Usuário*
## Para que serve o aplicativo:
IdScan é um aplicativo de processamento de imagens de documentos de identidade (RG) e armazenamento desses dados em um banco de dados MySQL local. Ele foi projetado para facilitar a digitalização, extração e organização de informações contidas em documentos físicos, tornando o gerenciamento de dados mais eficiente.

## Usando o IdScan
Ao abrir o aplicativo, você terá as seguintes opções:
 - **Carregar Imagem:** Permite carregar uma imagem de documento a partir do seu dispositivo.
 - **Processar Imagem:** Realiza o reconhecimento óptico de caracteres (OCR) na imagem carregada e extrai informações como nome, data de nascimento, número do documento, etc.
 - **Salvar Dados:** Armazena as informações extraídas no banco de dados MySQL local
 - **Busca de Documentos:** Permite pesquisar documentos armazenados no banco de dados usando critérios como nome, número do documento ou data.
 - **Edição de Informações:** Possibilita a edição manual dos dados extraídos, caso alguma informação esteja incorreta.

## Solução de Problemas Comuns:
### O aplicativo não inicia.
 - Verifique se o JDK 17 está instalado e configurado corretamente.
 - Certifique-se de que o MySQL está em execução e o banco de dados idscan_db foi criado.
### O OCR não está funcionando.
 - Verifique se o Tesseract está instalado e configurado corretamente nas variáveis de ambiente.
 - Certifique-se de que a imagem do documento está nítida e legível.
### Erro de conexão com o banco de dados.
 - Certifique-se de que o MySQL está em execução e acessível.


 ## Tutorial de Execução do IDScan

 ## 1. Abrir o IDScan

1. O primeiro passo é acessar o arquivo **App.java**, localizado dentro do diretório **java**.
2. Após isso, o usuário tem duas opções para abrir o IDScan:
   - Executar o comando `mvn exec:java` no terminal.
   - Selecionar a linha `public class App` (linha 10) e pressionar **F5** para iniciar a execução do programa.
3. O usuário deverá aguardar alguns segundos e para que o programa seja carregado pelo **Ollama** e a aplicação seja iniciada.


 ## 2. Tela de Upload
 - Na tela de upload, o usuário pode inserir o arquivo do seu documento de **RG** (legível), que deve estar nos formatos **jpeg**, **png** ou **jpg**. *Documentos em formato **pdf** não são aceitos*.
 - O arquivo também pode ser arrastado diretamente para a tela de upload.

 <img alt="upload" src="/docs/Tela de Upload.png">

 - Após o upload, o usuário deve aguardar enquanto o **Tesseract** processa as informações do RG (dados pessoais do usuário). Durante esse processo, será exibida uma tela de carregamento no **IDScan**.
- O progresso também pode ser acompanhado no terminal, onde é possível ver as informações sendo processadas pela máquina virtual.

 <img alt="processamento" src="docs/Tela de processamento.png">

 ## 3. Tela de Formulário do RG
  - Após o processamento, será exibida uma tela com um formulário contendo os dados extraídos do RG. Contudo, é possível que nem todos os dados sejam identificados corretamente, deixando algumas lacunas vazias ou com informações incorretas.
- O usuário pode preencher manualmente as lacunas com as informações corretas.
- O usuário também poderá:
  - **Limpar** todas as lacunas clicando no botão **Limpar**.
  - **Salvar** os dados preenchidos, clicando no botão **Salvar**, para registrar as informações no programa.

 <img alt="formulário" src="/docs/Tela de formulário.jpg">

 ## 4. Tela de Consulta de Dados
  - O usuário pode acessar a tela de **Consulta de Dados**. Nela, ele poderá digitar as informações que foram registradas no **IDScan** para buscar os dados desejados.
  - Após inserir as informações, o usuário deve clicar no botão **Buscar** para ser direcionado a outra tela.

  <img alt="consulta" src="/docs/Tela de Consulta.jpg">

 ## 5. Tela de Resultado da pesquisa

- Após realizar a consulta, os resultados serão exibidos na tela de **Resultados de Pesquisa**. Por exemplo, se o usuário digitar o nome "pedro cruz silva", a tela retornará todas as entradas que contêm esse nome.

<img alt="pesquisa" src="/docs/Tela de Pesquisa.jpg">

- Ao clicar em uma das entradas, o usuário poderá visualizar o nome e os dados completos do documento correspondente.
- O usuário também poderá **editar manualmente** as lacunas que contêm informações incorretas sobre o documento, caso necessário.
- Após isso, ele poderá **salvar alterações** ou até mesmo **excluir** os dados registrados.

<img alt="edição" src="/docs/Tela de edição.jpg">





## Resumo geral do funcionamento do IDScan

O **IDScan** é um aplicativo que utiliza **OCR** (Reconhecimento Óptico de Caracteres) para processar e extrair dados de documentos de identidade, como o **RG**.

## Passos:

1. **Carregar Documento**: O usuário faz o upload de uma foto legível do **RG** (nos formatos **jpeg**, **png**, **jpg**).

2. **Processamento OCR**: O **Tesseract OCR** lê os dados do RG e os processa, exibindo uma tela de carregamento.

3. **Formulário**: O aplicativo exibe os dados extraídos. Caso necessário, o usuário pode corrigir ou preencher manualmente as lacunas.

4. **Salvar ou Limpar**: O usuário pode salvar as informações ou limpar os campos para tentar novamente.

5. **Consulta de Dados**: O usuário pode buscar e editar os dados salvos.

6. **Resultados**: O aplicativo exibe os resultados da busca, permitindo visualização, edição e exclusão dos dados. 

---

O **IDScan** permite digitalizar documentos de identidade, extrair informações automaticamente com alta precisão, corrigir dados quando necessário e consultar essas informações de forma rápida e simples. O aplicativo oferece uma interface intuitiva para facilitar o processo de registro e busca de dados pessoais.

