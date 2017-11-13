# Luiza Labs - Desafio

Solução do desafio de busca.

## Intruções

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Pré-requisitos

Java JDK 1.8
Maven 3.5.2

### Instalação

1. Obter o projeto GIT
2. Empacotar projeto através do comando: 
	mvn package
3. No diretório target executar o comando: 
	java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar <caminho para arquivo zip> "<termos de busca>"

```
\target> java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar ..\etc\movies.zip "walt disney"
```

## Execução dos Testes

mvn test

## Autores

* **Leandro Fadelli** (https://github.com/lexfadelli)