# Aplicação Web de Agendamento de Transferências

Aplicação Web onde o usuário pode consultar e gravar novos agendamentos de transferências.

Consome os endpoints (resources) expostos pela API de agendamentos de transferências (agendamento-transf-api).

### Compilação
Na pasta raiz da aplicação, execute o comando maven:
```
mvn clean install
```

### Modo de execução - Opção 1
Na pasta raiz da aplicação, execute o comando maven:
```
mvn spring-boot:run
```
### Modo de execução - Opção 2
Na pasta raiz da aplicação, execute o comando maven:
```
java -jar target/agendamento-transf-api.jar
```

### URL para acessar a aplicação localmente:
Após a aplicação ser inicializada, ela estará disponível através do seguinte endereço:
http://localhost:8091/agendamentos/
