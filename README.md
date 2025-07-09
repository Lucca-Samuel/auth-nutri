# 🛡️ Spring Boot - CRUD de Usuários com Autenticação JWT (Médicos e Pacientes)

Este projeto é uma aplicação Java desenvolvida com **Spring Boot** que oferece um **CRUD de usuários**, servindo como base para sistemas que utilizam **autenticação e autorização via JWT**.  

O sistema diferencia dois tipos de usuários: **Médicos** e **Pacientes**, cada um com permissões e acessos específicos.

---

## 🧠 Contexto

Este projeto pode ser utilizado como base para sistemas de **agendamento médico, prontuários online, clínicas, hospitais ou planos de saúde**, onde o controle de acesso baseado em perfis é essencial.

---

## 🔧 Tecnologias Utilizadas

- Java 17+ (ou 21)
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Lombok
- H2 / PostgreSQL (banco de dados)
- Maven

---

## 📚 Funcionalidades

- Cadastro de usuários (Paciente ou Médico)
- Atualização e exclusão de contas
- Login e geração de token JWT
- Controle de acesso com base no tipo de usuário
- Validação do token nas requisições protegidas
- Senhas criptografadas com `BCrypt`

---

## 🧍‍♂️ Tipos de Usuários

| Tipo     | Acesso e Permissões                                                                 |
|----------|--------------------------------------------------------------------------------------|
| Médico   | Pode acessar informações de pacientes, administrar horários, visualizar agendamentos |
| Paciente | Pode consultar seu perfil, agendamentos e prontuários próprios                      |

---

## 🛠️ Endpoints Principais

| Método | Rota                  | Descrição                              | Autenticação | Permissão |
|--------|-----------------------|-----------------------------------------|--------------|-----------|
| POST   | `/auth/register`      | Cadastro de novo usuário (médico/paciente) | ❌ Não       | Livre     |
| POST   | `/auth/login`         | Autenticação e geração do token         | ❌ Não       | Livre     |
| GET    | `/users`              | Listar todos os usuários                | ✅ Sim       | ADMIN     |
| GET    | `/users/{id}`         | Buscar usuário por ID                   | ✅ Sim       | ADMIN     |
| GET    | `/pacientes/me`       | Dados do paciente logado                | ✅ Sim       | PACIENTE  |
| GET    | `/medicos/me`         | Dados do médico logado                  | ✅ Sim       | MÉDICO    |
| PUT    | `/users/{id}`         | Atualizar dados do usuário              | ✅ Sim       | Dono/Admin|
| DELETE | `/users/{id}`         | Remover usuário                         | ✅ Sim       | Dono/Admin|

---
