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
git clone https://github.com/pedro-fs-garcia/testOllama4j
cd 2semester-ADS
mvn clean compile
mvn exec:java
```