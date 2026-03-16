# Projeto Site de venda de carnes - Açougue

## Descrição

Este projeto funciona de forma a ser um site de vendas para um açougue, no qual os clientes podem escolher produtos dentre departamentos e adicioná-los ao seu carrinho(se os produtos estiverem em estoque) que mostra todos os produtos escolhidos, suas quantidades e o valor total. Para finalizar a compra o cliente devá efetuar o login ou se cadastrar para adicionar a compra em seu perfil. O sistema registra o valor total, quem efetuou e quando efetuou(data e hora) a venda.

O site atualiza conforme o banco de dados, atualizando departamentos e produtos automaticamente.

## Tecnologias utilizadas

- Java (Java Class, Servlets)
- HTML, CSS, JavaScript
- PostgreSQL (Banco de dados)
- NetBeans (IDE principal)
- pgAdmin 4 (IDE para gerenciamento do banco de dados)

## Como executar o projeto

1. Configure o banco de dados PostgreSQL com o script disponível na pasta `SQL`.  
2. Importe o projeto no NetBeans.  
3. Configure o servidor (Tomcat ou outro) para rodar o projeto.   
4. Acesse `http://localhost:8080/Loja/index.html` para abrir a loja.

## Principais funcionalidades

- Atualização de estoque em tempo real.
- Verificação para saber se o cliente está logado no momento de fechar o carrinho e efetuar a compra.
- Mensagens de erro amigáveis

## Como usar a loja

- Crie uma conta inicial.
- Entre nos departamentos disponíveis.
- Escolha os produtos que deseja adicionar ao carrinho(se tiverem estoque).
- Verifique seu carrinho clicando no botão no topo da página.
- Faça o login para efetuar a compra(caso já o tenha feito antes, não precisará fazer novamente).
Finalize a compra e verifique as informações de confirmação.

## Arquitetura

O modelo principal é o padrão MVC com divisão nas funções de model, controller e view. O FrontEnd utiliza HTML, CSS e JavaScript para interação, já o BackEnd é feito em Java com Servlets e comunicação com o banco de dados.

## Autor

- 😉 Matheus Sant'Ana Fuckner Clementino