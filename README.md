API de Gerenciamento de Produtos

API RESTful em Java com Spring Boot e Maven para gerenciar produtos (nome, preço, e categoria) e com banco de dados relacional MySQL.

Funcionalidades:

- GET /api/produtos: Lista todos os produtos
- GET /api/produtos/{id}: Busca produto por ID
- POST /api/produtos: Cria novo produto
- PUT /api/produtos/{id}: Atualiza produto
- DELETE /api/produtos/{id}: Remove produto

- GET /api/categorias: Lista todas os categorias
- GET /api/categorias/{id}: Busca categoria por ID
- POST /api/categorias: Cria nova  categoria
- PUT /api/categorias/{id}: Atualiza  categoria

Observações:
O DELETE para categorias não está disponível nesta versão pois ainda não foram implementados os tratamentos de erros que impedem a exclusão de uma categoria eventualmente presente em algum produto. 
Outras duas providências que também ainda não estão disponíveis nesta versão é impedir que um produto seja cadastrado ou editado com uma categoria inexistente. 
Caso aconteça, será necessário editar a tabela ‘Produto’ manualmente no banco para inserir um ID válido da categoria dentro da tabela ‘Produto’ para que a aplicação volte a funcionar normalmente. 

Pré-requisitos

Instale os seguintes softwares:

1.  Java Development Kit (JDK) 1.8 (ou superior):
- Verifique a instalação:
$> java -version

2.  Apache Maven (Opcional):
    -  O projeto usa Maven Wrapper (‘mvnw’)

3.  MySQL 8:
    - Durante a instalação, defina a senha para o usuário ‘root’.

Configuração do Banco de Dados

Prepare o banco de dados ‘bdprodutosapi’ e o usuário ‘api_user’ no MySQL 8:

1.  Acesse o MySQL:
        $> mysql -u root -p
    
    (Digite a senha do ‘root’ quando solicitado)

2.  Crie o Banco de Dados:
    sql → CREATE DATABASE bdprodutosapi;
    
3.  Crie Usuário e Conceda Permissões:
    sql → CREATE USER 'api_user'@'localhost' IDENTIFIED BY 'sua_senha_segura';
    GRANT ALL PRIVILEGES ON bdprodutosapi.* TO 'api_user'@'localhost';
    FLUSH PRIVILEGES;
    
    ATENÇÃO: Substitua ‘sua_senha_segura’ por uma senha forte de sua escolha.

4.  Saia do MySQL:
    sql → EXIT;


Configuração da Aplicação

Edite o arquivo ‘src/main/resources/application.properties’:

# database configs
spring.datasource.url=jdbc:mysql://localhost:3306/bdprodutosapi?useSSL=false&serverTimezone=UTC
spring.datasource.username=api_user
spring.datasource.password=sua_senha_segura # SUBSTITUA PELA SENHA REAL DO SEU api_user
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update # Cria/atualiza tabelas automaticamente
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

Executando a Aplicação

1.  Navegue até o Diretório do Projeto:
    - Abra seu terminal (Linux/macOS) ou prompt de comando (Windows).
    - Use o comando ‘cd’ para navegar até a pasta raiz do projeto que você baixou do GitHub. É a pasta onde você encontra o arquivo ‘pom.xml’ e os scripts ‘mvnw’ e ‘mvnw.cmd’.
     - Exemplo: ‘cd /caminho/para/sua/pasta/api-produtos’

2.  Compile o Projeto:
    - Este comando irá compilar todo o código-fonte, baixar as dependências necessárias (se ainda não o fez) e empacotar a aplicação em um arquivo executável.
    - Digite o comando apropriado para o seu sistema operacional e pressione Enter:
      
        Para Linux/macOS:
        $> ./mvnw clean install

        Para Windows:
        mvnw.cmd clean install
        
    - Aguarde até que o processo seja concluído. Você deverá ver a mensagem `BUILD SUCCESS` no final. Se houver algum erro, revise os passos anteriores, especialmente a instalação do JDK.

3.  Inicie a API:
    - Agora, vamos rodar o servidor da API!
    - Digite o comando apropriado para o seu sistema operacional e pressione Enter:

        Para Linux/macOS:
        $> ./mvnw spring-boot:run

        Para Windows:
        mvnw.cmd spring-boot:run
        
    - Aguarde. Você verá uma série de mensagens no terminal. Procure por uma linha que indique que o servidor Tomcat foi iniciado, geralmente algo como:
        
       ‘ ...Tomcat started on port(s): 8080 (http) with context path...’
        
- Isso significa que sua API está no ar e pronta para receber requisições!
Mantenha este terminal aberto, pois ele está executando a API.

Testando a API

A base da URL para todos os endpoints será ‘http://localhost:8080/api/produtos’.

Com a API em execução (o terminal do passo anterior deve estar aberto), você pode testar os endpoints usando seu navegador ou ferramentas como [Postman](https://www.postman.com/downloads/)

 Aqui vamos utilizar o `curl` no terminal.

Comandos do curl para API: 
Os parâmetros ‘-s’, ‘|’ e ‘jq’  são utilizados para tornar a saída no terminal mais legíveis e amigáveis.
Os comandos abaixo se referem à ‘Produto’ mas podem ser facilmente convertidos para ‘Categorias’.

- GET /api/produtos → lista todos os produtos
$> curl -s -X GET http://localhost:8080/produtos | jq

- GET /api/produtos/{id} → busca produto por id
$> curl -s -X GET http://localhost:8080/produtos/1 | jq

- POST /api/produtos → cria novo produto
$> curl -s -X POST http://localhost:8080/produtos/ \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "Produto Qualquer",
           "preco": 789.00,
           "categoria": { 
           		"id" : 2
           }
         }' | jq

ATENÇÃO: O ID da categoria (acima) tem que ser válido (presente na tabela ‘Categoria’)!

- PUT /api/produtos/{id} → atualiza produto
$> curl -s -X PUT http://localhost:8080/produtos/1 \
     -H "Content-Type: application/json" \
     -d '{
           "nome": "Produto Um",
           "preco": 1000.00,
           "categoria": { 
           		"id" : 2
           }
         }' | jq

ATENÇÃO: O ID da categoria (acima) tem que ser válido (presente na tabela ‘Categoria’)!

- DELETE /api/produtos/{id} → remove produto
$> curl -s -X DELETE http://localhost:8080/produtos/1 | jq

Os comandos acima podem ser facilmente adaptados para as ‘Categorias’.
