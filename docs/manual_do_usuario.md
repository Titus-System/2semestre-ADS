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