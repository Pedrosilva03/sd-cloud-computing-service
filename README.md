<h1 align="center">Projeto da UC de Sistemas Distribuídos - 2023/2024</h1>
<h2 align="center">Serviço de execução de tarefas na cloud</h2>
<h3 align="center">Remake</h3>

## Definição
Um serviço que permite a um cliente enviar o código de uma tarefa de computação para ser executado num servidor, logo que haja disponibilidade, recebendo de volta o resultado.

## Disclaimer
- Esta versão do projeto é um remake, pelo que não foi verificada por nenhum docente. Pode ser utilizada como guia para uma possível solução, mas não como resposta absoluta. Esta versão segue os pontos do [enunciado](https://github.com/Pedrosilva03/sd-cloud-computing-service/blob/main/docs/enunciado.pdf) de 2023/2024.
- Porquê? A primeira versão que eu fiz (que foi avaliada) não foi a melhor, então decidi fazer outra versão que tenta corrigir todos os erros da primeira.

## Visão geral dos programas
Este repositório contém dois programas

### Cliente
- Criação de contas
- Sistema de login/logout
- Permite enviar qualquer tipo de ficheiro para teste
- Acesso ao estado do sistema
- Recebe respostas do servidor passivamente (enquanto o utilizador faz outras tarefas)

### Servidor
- Permite receber vários pedidos do mesmo cliente sem ter respondido aos pedidos anteriores
- Permite vários clientes conectados ao mesmo tempo
- Gestão da fila de espera

## Funcionalidades
Todos os comandos devem ser executados na raiz do repositório

### Compilação
- O código pode ser compilado utilizando o comando
```console
make
```
- Para além disso cada programa pode ser compilado individualmente com os comandos
```console
make client
```
```console
make server
```
### Client
- Para correr o cliente basta executar o comando
```console
make run_client
```
- O cliente tenta efetuar uma conexão com o servidor e se conseguir, mostrará o menu de login.
- É necessário criar uma conta (```username``` e ```password```) para utilizar o serviço
#### Pedido de execução
- O ficheiro a ser utilizado para teste deve estar na pasta [programas](https://github.com/Pedrosilva03/sd-cloud-computing-service/tree/main/programs)
- Como dito antes, qualquer tipo de ficheiro funciona visto que apenas os bytes puros são utilizados na simulação.
- O resultado das tarefas será colocado na pasta [results](https://github.com/Pedrosilva03/sd-cloud-computing-service/tree/main/results)

#### Pedido de estado
- É possível consultar o estado do serviço. É possível ver os pedidos que estão a ser executados e em espera, bem como a memória disponível

### Server
- Para correr o servidor basta executar o comando
```console
make run_server
```
- O servidor guarda os utilizadores com conta criada de forma persistente
- Tenta ao máximo minimizar o número de threads abertas
- Ao receber pedidos de execução de programas, devolve imediatamente o ID do pedido
- O servidor pode ser fechado com o comando ```close```

## Conclusão
- Versão original do projeto realizada por Pedro Silva, António Silva, Diogo Barros e Duarte Leitão
- Remake por Pedro Silva
