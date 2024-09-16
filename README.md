# 2semestre-ADS

## Java versão 17
### Linux:
```
sudo apt update
sudo apt install openjdk-17-jdk
```
### windows:
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

## instale o maven:
### Linux:
```
sudo apt update
sudo apt maven
```
### Windows:
https://maven.apache.org/download.cgi
baixar o arquivo "apache-maven-3.9.9-bin.zip"
siga as instruções: https://medium.com/@gauravshah97/how-to-install-maven-on-windows-39ff317e40cf


## Instalar Tesseract
### Linux
```
sudo apt install tesseract-ocr
sudo apt install tesseract-oct-por
```
datapath para Linux: "/usr/share/tesseract-ocr/4.00/tessdata"

### Windows
Baixar e executar o arquivo .exe: https://github.com/UB-Mannheim/tesseract/wiki

#### adicionar Tesseract ao PATH:
 - Pressione `Windows + R`, digite `sysdm.cpl` e pressione Enter
 - Na aba Avançado, clique no botão Variáveis de Ambiente
 - Na seção Variáveis do sistema, encontre a variável chamada `Path`, selecione-a e clique em Editar
 - Clique em `Novo` e adicione o caminho completo para a pasta onde o Tesseract está instalado. O caminho geralmente será algo como: `C:\Program Files\Tesseract-OCR\`

datapath for Windows: "C:\Program Files\Tesseract-OCR"

## Instalar ollama
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
ollama pull llama2
ollama pull moondream
```

## Ollama
rodar ollama no linux:
```
systemctl status ollama.service
systemctl start ollama.service
systemctl stop ollama.service
```

rodar ollama no windows:
```
ollama serve
```
ollama irá rodar em http://localhost:11434/

## clone o repositório e execute o projeto
```
git clone https://github.com/Titus-System/2semestre-ADS.git
cd 2semester-ADS
mvn clean compile
mvn exec:java
```