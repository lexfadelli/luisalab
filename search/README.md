# Luiza Labs - Desafio

Solução do desafio de busca.

### Pré-requisitos

* Java JDK 1.8
* Maven 3.5.2

### Instalação

1. Obter projeto:

```
git clone https://github.com/lexfadelli/luisalab
```

2. Criar pacote.
Navegar até a pasta do projeto e executar:

```
mvn package
```

3. No diretório target executar o comando:

```
java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar <caminho para arquivo zip> "<termos de busca>"
```

Exemplo:

```
\target> java -jar search-0.0.1-SNAPSHOT-jar-with-dependencies.jar ..\etc\movies.zip "walt disney"
```

## Execução dos Testes

```
mvn test
```

## Autor

* **Leandro Fadelli** (https://github.com/lexfadelli)