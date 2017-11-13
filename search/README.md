# Luiza Labs - Desafio

Solu��o do desafio de busca.

### Pr�-requisitos

* Java JDK 1.8
* Maven 3.5.2

### Instala��o

1. Obter projeto:

```
git clone https://github.com/lexfadelli/luisalab
```

2. Criar pacote.
Navegar at� a pasta do projeto e executar:

```
mvn package
```

3. No diret�rio target executar o comando:

```
java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar <caminho para arquivo zip> "<termos de busca>"
```

Exemplo:

```
\target> java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar ..\etc\movies.zip "walt disney"
```

## Execu��o dos Testes

```
mvn test
```

## Autor

* **Leandro Fadelli** (https://github.com/lexfadelli)