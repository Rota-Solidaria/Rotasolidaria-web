# Rota Solidária

Site para organizar **excursões gratuitas de ônibus para doação de sangue**.

- O **doador** escolhe uma excursão e se inscreve (nome, telefone, cidade). Não precisa criar conta.
- O **organizador** entra na área dele, cria as excursões e vê a lista de inscritos para
  **confirmar** ou **cancelar** cada pessoa (e pode imprimir a lista).

Feito com o que foi visto em Programação para Web 3:
Spring Boot (MVC), FreeMarker, formulários com `@RequestParam`, Post/Redirect/Get,
MySQL, Flyway e `JdbcTemplate`. Sem Docker, sem Spring Security, sem JPA, sem e-mail e sem mapa.

---

## Como rodar (local, sem Docker)

**Precisa ter instalado:** Java 21 e MySQL (Server) rodando na porta 3306.

1. Abra `src/main/resources/application.properties` e coloque o usuário e a senha do **seu** MySQL:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=
   ```

2. Rode o projeto:
   - pela IDE: execute a classe `RotasolidariaApplication`, **ou**
   - pelo terminal, na pasta do projeto:
     ```
     mvn spring-boot:run      (Windows)
     ```

3. Acesse **http://localhost:8080**

Na primeira vez, o Flyway cria as tabelas e já coloca excursões de exemplo.

**Login do organizador:** `admin@rotasolidaria.com` / `admin123`
(fica na tabela `organizadores`, criada na migração `V5__inserir_dados_iniciais.sql`).

---

## Rotas

| Método | Rota | O que faz |
|---|---|---|
| GET | `/` | Página inicial com as próximas excursões |
| GET | `/campanhas` | Lista de excursões abertas |
| GET | `/campanha?id=1` | Detalhes da excursão + formulário de inscrição |
| POST | `/inscrever` | Recebe a inscrição (valida, grava e redireciona) |
| GET | `/inscricao?id=5` | Página "inscrição recebida" |
| GET | `/como-doar` | Guia do doador |
| GET/POST | `/organizador/login` | Login do organizador |
| GET | `/organizador` | Painel com todas as excursões |
| GET | `/organizador/campanha/nova` | Formulário de nova excursão |
| GET | `/organizador/campanha/editar?id=1` | Formulário de edição |
| POST | `/organizador/campanha/salvar` | Grava (INSERT se id = 0, UPDATE se não) |
| POST | `/organizador/campanha/status` | Abre / encerra inscrições |
| POST | `/organizador/campanha/excluir` | Exclui a excursão |
| GET | `/organizador/inscritos?id=1` | Lista de inscritos da excursão |
| POST | `/organizador/inscricao/status` | Confirma / cancela uma inscrição |
| GET | `/organizador/sair` | Sai da área do organizador |

---

## Estrutura

```
src/main/java/com/rotasolidaria/
├── RotasolidariaApplication.java
├── controllers/
│   ├── HomeController.java          -> /  e  /como-doar
│   ├── CampanhaController.java      -> excursões e inscrição (parte pública)
│   └── OrganizadorController.java   -> área do organizador
├── models/
│   ├── Campanha.java
│   └── Inscricao.java
└── repositories/                    -> SQL com JdbcTemplate (queryForList, queryForObject, update)
    ├── CampanhaRepository.java
    ├── InscricaoRepository.java
    └── OrganizadorRepository.java

src/main/resources/
├── application.properties
├── db/migration/                    -> migrações do Flyway (V1 a V5)
├── static/css/style.css             -> um único CSS
├── static/images/
└── templates/                       -> páginas FreeMarker (.ftlh)
    ├── head.ftlh, header.ftlh, footer.ftlh   (incluídos com <#include>)
    ├── index, campanhas, campanha, inscricao-confirmada, como-doar
    └── admin/ login, painel, campanha-form, inscritos
```

### Banco de dados

- `campanhas`: id, titulo, descricao, data_campanha, horario_saida, local_saida, hemocentro, vagas, status (ABERTA / ENCERRADA)
- `doadores`: id, nome, email, telefone, cidade, senha (hash)
- `inscricoes`: id, campanha_id, doador_id, nome, telefone, email, cidade, observacao, status (CONFIRMADA / CANCELADA), data_inscricao
- `organizadores`: id, email, senha


## Informações adicionais 

- **`Access denied for user 'root'`** → usuário/senha errados no `application.properties`.
- **`Communications link failure`** → o MySQL não está rodando, ou está em outra porta (troque o `3306` na URL).
- **Erro de checksum do Flyway** → um arquivo de migração foi editado depois de rodar.
  Em ambiente de estudo, o jeito mais simples é apagar o banco e rodar de novo:
  `DROP DATABASE rotasolidaria;`
