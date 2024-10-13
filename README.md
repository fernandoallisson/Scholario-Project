
# Backend do Scholario Project
<details>
  <summary><strong>Sobre o Backend da Aplicação</strong></summary>
  
## Visão Geral

O Scholario é uma aplicação de gerenciamento escolar que permite o cadastro de alunos, professores, turmas, matérias e a gestão de presenças e notas. O projeto é desenvolvido utilizando Java com Spring Boot, Hibernate para persistência de dados e MySQL como banco de dados. O objetivo é proporcionar uma solução completa para gerenciamento de informações escolares.

## Funcionalidades

- Gerenciamento de alunos, professores e turmas
- Controle de presença (attendance) dos alunos
- Registro de notas dos alunos em diferentes matérias
- Associações entre alunos e turmas, professores e matérias
- Autenticação e autorização de usuários com Spring Security

## Tecnologias Utilizadas

- Java 17: Linguagem de programação utilizada.
- Spring Boot: Framework para simplificar a configuração e o desenvolvimento da aplicação.
- Hibernate: Framework de ORM para persistência de dados.
- MySQL: Banco de dados relacional.
- Docker: Containerização da aplicação.
- Imgur: Serviço de armazenamento de imagens para uploads de fotos.

## Requisitos

- Java 17
- Docker e Docker Compose
- MySQL 8.x

## Estrutura do projeto

```bash
Scholario-Project/
├── app/
│   ├── backend/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/com/scholario/scholario_demo/
│   │   │   │   ├── resources/
│   │   ├── Dockerfile
│   │   └── docker-compose.yaml
├── mysql_data/ (Volume para os dados do MySQL)
```

1. Clone o repositório:

```bash
git clone git@github.com:fernandoallisson/Scholario-Project.git
cd Scholario-Project/app/backend
```

2. Configurar o Banco de Dados:
   Certifique-se de que o MySQL está configurado corretamente no arquivo docker-compose.yaml. A configuração padrão usa:

- Usuário: root
- Senha: root
- Porta: 3306

3. Executar com Docker Compose

```bash
docker-compose up --build
```

Isso criará os containers para a aplicação e para o MySQL, configurando o ambiente automaticamente.

4. Acessar a Aplicação

A aplicação estará disponível em http://localhost:8080.

# Rotas para uso

Endpoint Disponíveis

## Para estudantes

<details>
<summary><strong>Rotas para Estudantes</strong></summary>

| Método HTTP | Rota                                    | Descrição                                      |
| ----------- | --------------------------------------- | ---------------------------------------------- |
| GET         | `/students`                             | Lista todos os alunos                          |
| GET         | `/students/{id}`                        | Busca um aluno específico por ID               |
| GET         | `/students/search?name=`                | Busca uma lista de alunos com base no nome     |
| GET         | `/students/class/{classId}`             | Busca uma lista de alunos com base na classe   |
| POST        | `/students`                             | Cria um novo aluno                             |
| PUT         | `/students/{id}`                        | Atualiza os dados de um aluno específico       |
| PUT         | `/students/{studentId}/class/{classId}` | Associa um estudante à uma turma específica    |
| DELETE      | `/students/{studentId}/class/{classId}` | Desassocia um estudante à uma turma específica |
| DELETE      | `/students/{id}`                        | Deleta um aluno específico                     |

</details>

## Para Professores

<details>
<summary><strong>Rotas para Professores</strong></summary>

| Método HTTP | Rota                                        | Descrição                                            |
| ----------- | ------------------------------------------- | ---------------------------------------------------- |
| GET         | `/teachers`                                 | Lista todos os professores                           |
| GET         | `/teachers/{id}`                            | Busca um professor específico por ID                 |
| GET         | `/teachers/search?name={name}`              | Procura todos os professores com base em um nome     |
| GET         | `/teachers/subjetc/{subjectId}`             | Lista todos os professores por disciplina específica |
| POST        | `/teachers`                                 | Cria um novo professor                               |
| PUT         | `/teachers/{id}`                            | Atualiza os dados de um professor específico         |
| PUT         | `/teachers/{teacherId}/subject/{subjectId}` | Associa um professor a uma matéria específica        |
| PUT         | `/teachers/{teacherId}/class/{classId}`     | Associa um professor a uma classe específica         |
| DELETE      | `/teachers/{id}`                            | Deleta um professor específico                       |
| DELETE      | `/teachers/{teacherId}/subject/{subjectId}` | Desassocia um professor a uma matéria específica     |
| DELETE      | `/teachers/{teacherId}/class/{classId}`     | Desassocia um professor a uma classe específica      |

</details>

## Para Administradores

<details>
<summary><strong>Rotas para Administradores</strong></summary>
  
| Método HTTP | Rota                                            | Descrição                                             |
|-------------|-------------------------------------------------|-------------------------------------------------------|
| GET         | `/administrators`                                | Busca um administradore específico por ID             |
| GET         | `/administrators/{id}`                           | Lista todos os administradores                        |
| PUT         | `/administrators/{id}`                           | Atualiza um administrador                             |
| POST        | `/administrators`                                | Cria um novo administrador                            |
| DELETE      | `/administrators/{id}`                           | Deleta um administrador específica                    |

</details>

## Para Disciplinas

<details>
<summary><strong>Rotas para Disciplinas</strong></summary>
  
| Método HTTP | Rota                                            | Descrição                                             |
|-------------|-------------------------------------------------|-------------------------------------------------------|
| GET         | `/subjects`                                     | Busca uma disciplina específica por ID                |
| GET         | `/subjects/{id}`                                | Lista todos as disciplinas                            |
| POST        | `/subjects`                                     | Cria uma nova disciplina                              |
| PUT         | `/subjects/{id}`                                | Atualiza uma disciplina                               |
| DELETE      | `/subjects/{id}`                                | Deleta uma disciplina específica                      |

</details>

## Para Turmas

<details>
<summary><strong>Rotas para Turmas</strong></summary>
  
| Método HTTP | Rota                                            | Descrição                                             |
|-------------|-------------------------------------------------|-------------------------------------------------------|
| GET         | `/classes`                                     | Busca uma turma específica por ID                      |
| GET         | `/classes/{id}`                                | Lista todos as turmas                                  |
| POST        | `/classes`                                     | Cria uma nova turma                                    |
| PUT         | `/classes/{id}`                                | Atualiza uma turma                                     |
| DELETE      | `/classes/{id}`                                | Deleta uma turma específica                            |

</details>

## Para Registro de Presenças

<details>
<summary><strong>Rotas para Presenças</strong></summary>
  
| Método HTTP | Rota                                            | Descrição                                               |
|-------------|-------------------------------------------------|-------------------------------------------------------  |
| GET         | `/attendances`                                  | Busca todas os registros de frequência                  |
| GET         | `/attendances/{id}`                             | Deleta uma turma específica                             |
| GET         | `/attendances/class/{classId}`                  | Busca os registros de frequência de uma turma           |
| GET         | `/attendances/student/{studentId}`              | Busca os registros de frequência de um estudante        |
| POST        | `/attendances/{studentId}/{classId}`            | Registra de frequência com base no estudante e turma    |
| PUT         | `/attendances/{id}`                             | Atualiza um registro de frequência pelo id da frequência|
| DELETE      | `/attendances/{id}`                             | Deleta uma frequência específica                        |

</details>

## Para Registro de Notas

<details>
<summary><strong>Rotas para Notas</strong></summary>

| Método HTTP | Rota                                              | Descrição                                                                        |
| ----------- | ------------------------------------------------- | -------------------------------------------------------------------------------- |
| GET         | `/grades`                                         | Busca todas os registros de Notas                                                |
| GET         | `/grades/{id}`                                    | usca todas os registros de Notas pelo Id                                         |
| GET         | `/grades/student/{studentId}`                     | Busca os registros de nota de um estudante                                       |
| GET         | `/grades/subject/{subjectId}`                     | Busca os registros de notas de uma disciplina específica                         |
| GET         | `/grades/subject/{subjectId}/student/{studentId}` | Busca os registros de nota com base em um estudante e uma disciplina específicos |
| POST        | `/grades/{subjectId}/{studentId}`                 | Registra uma nota com base em uma disciplina e um estudante específicos          |
| PUT         | `/grades/{id}`                                    | Atualiza um registro de nota pelo id da nota                                     |
| DELETE      | `/grades/{id}`                                    | Deleta uma nota específica                                                       |

</details>

## Segurança da API

Este projeto utiliza autenticação e autorização para garantir a segurança das rotas. As seguintes rotas estão disponíveis e suas respectivas permissões são detalhadas abaixo:

<details>
<summary><strong>/teachers</strong></summary>

### Rotas Acessíveis
- `GET /teachers`: Permite a qualquer usuário autenticado visualizar a lista de todos os professores. Não requer permissões específicas.
- `GET /teachers/{id}`: Qualquer usuário autenticado pode acessar as informações de um professor específico.
### Rotas Protegidas
- `POST /teachers`: Qualquer usuário autenticado pode criar um novo professor.
- `PUT /teachers/{id}`: Apenas usuários com a autoridade "teacher" podem atualizar seus próprios dados.
- `DELETE /teachers/{id}`: Apenas usuários com a autoridade "admin" podem remover um professor do sistema.
- `PUT /teachers/{teacherId}/subjects/{subjectId}`: Apenas usuários com a autoridade "admin" podem associar uma disciplina a um professor.
- `DELETE /teachers/{teacherId}/subjects/{subjectId}`: Apenas usuários com a autoridade "admin" podem remover a associação de uma disciplina com um professor.
- `PUT /teachers/{teacherId}/classes/{classId}`: Apenas usuários com a autoridade "admin" podem associar uma turma a um professor.
- `DELETE /teachers/{teacherId}/classes/{classId}`: Apenas usuários com a autoridade "admin" podem remover a associação de uma turma com um professor.
- `GET /teachers/subject/{subjectId}`: Permite que qualquer usuário autenticado visualize os professores associados a uma disciplina específica.
- `GET /teachers/search?name`: Qualquer usuário autenticado pode buscar professores pelo nome.
</details>

<details>
<summary><strong>/students</strong></summary>

### Rotas Acessíveis
- `GET /students`: Permite que qualquer usuário autenticado visualize a lista de todos os estudantes. Não requer permissões específicas.
- `GET /students/{id}`: Qualquer usuário autenticado pode acessar as informações de um estudante específico.
- `GET /students/search`: Permite a busca de estudantes pelo nome, acessível a todos os usuários autenticados.
- `GET /students/class/{classId}`: Permite que qualquer usuário autenticado visualize todos os estudantes associados a uma determinada classe.

### Rotas Protegidas
- `POST /students`: Qualquer usuário autenticado pode criar novos estudantes.
- `PUT /students/{id}`: Qualquer usuário autenticado pode atualizar os detalhes de um estudante existente.
- `DELETE /students/{id}`: Apenas usuários com a autoridade "admin" podem remover um estudante do sistema.
- `PUT /students/{studentId}/classes/{classId}`: Apenas usuários com a autoridade "admin" podem associar um estudante a uma classe específica.
- `DELETE /students/{studentId}/classes/{classId}`: Apenas usuários com a autoridade "admin" podem remover um estudante de uma classe específica.
</details>

<details>

<summary><strong>/administrators</strong></summary>

### Rotas Acessíveis
- `GET /administrators`: Permite que apenas usuários com a autoridade "admin" visualizem a lista de todos os administradores registrados.
- `GET /administrators/{id}`: Permite que apenas usuários com a autoridade "admin" acessem as informações de um administrador específico, identificado pelo seu ID.
### Rotas Protegidas
- `POST /administrators`: Apenas usuários com a autoridade "admin" podem criar um novo administrador no sistema.
- `PUT /administrators/{id}`: Apenas usuários com a autoridade "admin" podem atualizar as informações de um administrador existente.
- `DELETE /administrators/{id}`: Apenas usuários com a autoridade "admin" podem excluir um administrador do sistema.
</details>
<details>

<summary><strong>/Classes</strong></summary>

## Rotas Acessíveis
- `GET /classes`: Permite que qualquer usuário autenticado visualize a lista de todas as turmas.
- `GET /classes/{id}`: Qualquer usuário autenticado pode acessar as informações de uma turma específica.
## Rotas Protegidas
- `POST /classes`: Apenas usuários com a autoridade "admin" podem criar uma nova turma.
- `PUT /classes/{id}`: Apenas usuários com a autoridade "admin" podem atualizar uma turma existente.
- `DELETE /classes/{id}`: Apenas usuários com a autoridade "admin" podem excluir uma turma do sistema.
</details>

<details>

<summary><strong>/subjects</strong></summary>

### Rotas Acessíveis
- `GET /subjects`: Permite a qualquer usuário autenticado visualizar a lista de todas as disciplinas. Não requer permissões específicas.
- `GET /subjects/{id}`: Qualquer usuário autenticado pode acessar as informações de uma disciplina específica.

### Rotas Protegidas
- `POST /subjects`: Apenas usuários com a autoridade "admin" podem criar novas disciplinas.
- `PUT /subjects/{id}`: Apenas usuários com a autoridade "admin" podem atualizar os detalhes de uma disciplina existente.
- `DELETE /subjects/{id}`: Apenas usuários com a autoridade "admin" podem remover uma disciplina do sistema.
</details>
<details>
<summary><strong>/grades</strong></summary>

### Rotas Acessíveis
- `GET /grades`: Permite que qualquer usuário autenticado visualize a lista de todas as notas.
- `GET /grades/{id}`: Qualquer usuário autenticado pode acessar as informações de uma nota específica.
- `GET /grades/student/{studentId}`: Permite que qualquer usuário autenticado visualize todas as notas de um estudante específico.
- `GET /grades/subject/{subjectId}`: Permite que qualquer usuário autenticado visualize todas as notas de uma disciplina específica.
- `GET /grades/subject/{subjectId}/student/{studentId}`: Permite que qualquer usuário autenticado visualize as notas de um estudante em uma disciplina específica.

### Rotas Protegidas
- `POST /grades/{subjectId}/{studentId}`: Apenas usuários com a autoridade "admin" ou "teacher" podem criar uma nova nota para um estudante em uma disciplina.
- `PUT /grades/{id}`: Apenas usuários com a autoridade "admin" ou "teacher" podem atualizar uma nota existente.
- `DELETE /grades/{id}`: Apenas usuários com a autoridade "admin" podem excluir uma nota do sistema.
</details>
<details>
<summary><strong>/attendances</strong></summary>

### Rotas Acessíveis
- `GET /attendances`: Permite que qualquer usuário autenticado visualize a lista de todas as presenças registradas.
- `GET /attendances/student/{studentId}`: Permite que qualquer usuário autenticado visualize as presenças de um aluno - específico, identificado pelo seu ID.
- `GET /attendances/class/{classId}`: Permite que qualquer usuário autenticado visualize as presenças registradas em uma turma específica, identificada pelo seu ID.
- `GET /attendances/{id}`: Qualquer usuário autenticado pode acessar as informações de uma presença específica, identificada pelo seu ID.
### Rotas Protegidas
- `POST /attendances/{studentId}/{classId}`: Apenas usuários com a autoridade "admin" ou "teacher" podem registrar a presença de um aluno em uma turma.
- `PUT /attendances/{id}`: Apenas usuários com a autoridade "admin" ou "teacher" podem atualizar uma presença existente.
- `DELETE /attendances/{id}`: Apenas usuários com a autoridade "admin" podem excluir um registro de presença do sistema.
</details>


## Desafios e Aprendizados 🚀

Um dos maiores desafios enfrentados durante o desenvolvimento foi a configuração correta das relações entre as entidades. Em particular, garantir que os relacionamentos entre turmas, alunos e presenças estivessem configurados para evitar loops infinitos na serialização.

</details>

## Status do Projeto

🚧 **Projeto em desenvolvimento** 🚧

Este projeto está atualmente em desenvolvimento e algumas funcionalidades ainda podem estar incompletas ou sujeitas a mudanças. A documentação e o código estão sendo constantemente atualizados.

## Licença ⚠️

Este projeto é licenciado sob uma licença proprietária. **Todos os direitos são reservados**. O uso, modificação, distribuição ou qualquer outra forma de utilização deste software é estritamente proibida sem a permissão explícita do autor.
