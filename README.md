# 🚀 API de Gerenciamento de Produtos

Esta é uma API RESTful desenvolvida em **Java** com **Spring Boot** e **Maven**, projetada para a gestão eficiente de produtos. A API interage com um banco de dados relacional **MySQL** para persistir informações como nome, preço e categoria dos produtos.

## ✨ Funcionalidades

A API oferece os seguintes endpoints para gerenciamento de produtos e categorias:

### Endpoints de Produtos:

*   `GET /api/produtos`: Lista todos os produtos cadastrados.
*   `GET /api/produtos/{id}`: Busca um produto específico pelo seu ID.
*   `POST /api/produtos`: Cria um novo produto.
*   `PUT /api/produtos/{id}`: Atualiza um produto existente.
*   `DELETE /api/produtos/{id}`: Remove um produto pelo seu ID.

### Endpoints de Categorias:

*   `GET /api/categorias`: Lista todas as categorias cadastradas.
*   `GET /api/categorias/{id}`: Busca uma categoria específica pelo seu ID.
*   `POST /api/categorias`: Cria uma nova categoria.
*   `PUT /api/categorias/{id}`: Atualiza uma categoria existente.

## ⚠️ Observações Importantes

É crucial ter ciência das seguintes limitações e comportamentos da API na sua versão atual:

*   **`DELETE` de Categorias:** A funcionalidade de exclusão (`DELETE`) para categorias **não está disponível**. Isso se deve à necessidade de implementar tratamentos de erros adequados que impeçam a remoção de categorias que possam estar associadas a produtos existentes. A exclusão de uma categoria em uso poderia levar a inconsistências nos dados.
*   **Integridade Referencial de Categorias:**
    *   Não há validação para impedir que um produto seja cadastrado ou editado com uma categoria inexistente.
    *   **Caso ocorra**, será necessário **editar manualmente** a tabela `Produto` diretamente no banco de dados para inserir um `ID` válido de categoria. Essa ação é fundamental para restaurar o funcionamento normal da aplicação.

## 🛠️ Pré-requisitos

Para que a aplicação funcione corretamente, você precisará ter os seguintes softwares instalados:

*   **Java Development Kit (JDK) 1.8** (ou versão superior):
    *   Para verificar a instalação, execute no terminal:
        ```bash
        java -version
        ```
*   **Apache Maven** (Opcional):
    *   O projeto utiliza o Maven Wrapper (`mvnw`), que facilita a execução de comandos Maven sem a necessidade de uma instalação global do Apache Maven.
*   **MySQL 8**:
    *   Durante a instalação, é **essencial** que você defina uma senha para o usuário `root`.

## ⚙️ Configuração do Banco de Dados

Siga os passos abaixo para preparar o banco de dados `bdprodutosapi` e o usuário `api_user` no MySQL 8:

1.  **Acesse o MySQL:**
    ```bash
    mysql -u root -p
    # Digite a senha do 'root' quando solicitado
    ```
2.  **Crie o Banco de Dados:**
    ```sql
    CREATE DATABASE bdprodutosapi;
    ```
3.  **Crie Usuário e Conceda Permissões:**
    ```sql
    CREATE USER 'api_user'@'localhost' IDENTIFIED BY 'sua_senha_segura';
    GRANT ALL PRIVILEGES ON bdprodutosapi.* TO 'api_user'@'localhost';
    FLUSH PRIVILEGES;
    ```
    > **ATENÇÃO:** Substitua `sua_senha_segura` por uma senha forte e segura de sua escolha.

4.  **Saia do MySQL:**
    ```sql
    EXIT;
    ```

## 🖥️ Configuração da Aplicação

Após configurar o banco de dados, você precisará ajustar as credenciais no arquivo de propriedades da aplicação:

*   Edite o arquivo `src/main/resources/application.properties` com as seguintes configurações (certifique-se de substituir `sua_senha_segura` pela senha real do `api_user` que você definiu):

    ```properties
    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/bdprodutosapi?useSSL=false&serverTimezone=UTC
    spring.datasource.username=api_user
    spring.datasource.password=sua_senha_segura # SUBSTITUA PELA SENHA REAL DO SEU api_user
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # JPA and Hibernate Configuration
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update # Cria/atualiza tabelas automaticamente
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    ```

## ▶️ Executando a Aplicação

Siga esta sequência para preparar e iniciar sua API, partindo do `.zip` baixado do GitHub:

1.  **Extração e Navegação:**
    *   Extraia o conteúdo do `.zip` em uma pasta de sua preferência.
    *   Abra o terminal (Linux/macOS) ou o Prompt de Comando/PowerShell (Windows).
    *   Navegue até a pasta raiz do projeto extraído, que é onde se encontra o arquivo `pom.xml`. Exemplo:
        ```bash
        cd /caminho/para/sua/pasta/do/projeto
        ```
2.  **Invocando o Maven Wrapper:**
    *   Gere os arquivos do Maven Wrapper (`.mvn`, `mvnw`, `mvnw.cmd`):
        ```bash
        mvn -N io.takari:maven:wrapper
        ```
3.  **Verificação do Wrapper (Opcional):**
    *   Confirme que os arquivos do Wrapper foram gerados:
        *   Linux/macOS:
            ```bash
            ls -l .mvn/wrapper
            ```
        *   Windows:
            ```bash
            dir .mvn\wrapper\
            ```
4.  **Obtenção de Dependências e Empacotamento:**
    *   Obtenha as dependências, compile, instale e empacote a aplicação. Aguarde até ver a mensagem `BUILD SUCCESS` no final. Se houver erros, revise a instalação do JDK.
        *   Linux/macOS:
            ```bash
            ./mvnw clean install
            ```
        *   Windows:
            ```bash
            mvnw.cmd clean install
            ```
5.  **Iniciando a API:**
    *   Inicie a aplicação Spring Boot. Mantenha este terminal aberto, pois ele estará executando a API.
        *   Linux/macOS:
            ```bash
            ./mvnw spring-boot:run
            ```
        *   Windows:
            ```bash
            mvnw.cmd spring-boot:run
            ```
    *   Aguarde até ver uma mensagem similar a: `...Tomcat started on port(s): 8080 (http) with context path...` Isso indica que sua API está no ar e pronta para receber requisições!

## �� Testando a API

Com a API em execução (o terminal do passo anterior deve estar ativo), você pode testar os endpoints usando ferramentas como Postman, seu navegador ou, como exemplificado abaixo, via `curl` no terminal.

*   A base da URL para todos os endpoints será: `http://localhost:8080/api`.

> Os parâmetros `-s`, `|` e `jq` nos exemplos de `curl` são usados para tornar a saída no terminal mais legível e amigável. Certifique-se de ter o `jq` instalado (ferramenta de linha de comando para processar JSON).

### Exemplos de Requisições com `curl` (para Produtos):

*   **`GET /api/produtos`** (Lista todos os produtos)
    ```bash
    curl -s -X GET http://localhost:8080/api/produtos | jq
    ```
*   **`GET /api/produtos/{id}`** (Busca produto por ID)
    ```bash
    curl -s -X GET http://localhost:8080/api/produtos/1 | jq
    ```
*   **`POST /api/produtos`** (Cria novo produto)
    ```bash
    curl -s -X POST http://localhost:8080/api/produtos \
         -H "Content-Type: application/json" \
         -d '{ "nome": "Produto Qualquer", "preco": 789.00, "categoria": { "id" : 2 } }' | jq
    ```
    > **ATENÇÃO:** O `ID` da categoria (no exemplo acima) **deve ser válido** e estar presente na tabela `Categoria` do seu banco de dados.

*   **`PUT /api/produtos/{id}`** (Atualiza produto)
    ```bash
    curl -s -X PUT http://localhost:8080/api/produtos/1 \
         -H "Content-Type: application/json" \
         -d '{ "nome": "Produto Um Atualizado", "preco": 1000.00, "categoria": { "id" : 2 } }' | jq
    ```
    > **ATENÇÃO:** O `ID` da categoria (no exemplo acima) **deve ser válido** e estar presente na tabela `Categoria` do seu banco de dados.

*   **`DELETE /api/produtos/{id}`** (Remove produto)
    ```bash
    curl -s -X DELETE http://localhost:8080/api/produtos/1 | jq
    ```

> Os comandos acima podem ser facilmente adaptados para interagir com os endpoints de **Categorias** (substituindo `/api/produtos` por `/api/categorias`, e ajustando os dados JSON conforme necessário para `POST`/`PUT`).

---

