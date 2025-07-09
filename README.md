# 🛡️ Spring Boot - CRUD de Usuários com Autenticação JWT

Este projeto é uma aplicação Java desenvolvida com **Spring Boot** que oferece um **CRUD de usuários**, servindo como base para sistemas que utilizam **autenticação e autorização via JWT**.

## 🔧 Tecnologias Utilizadas

- Java 17+ (ou 21)
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Lombok
- H2 / PostgreSQL (banco de dados)
- Maven

## 📚 Funcionalidades

- Cadastro de usuários
- Atualização de dados do usuário
- Exclusão de usuários
- Consulta (listar e buscar por ID)
- Autenticação com e-mail/login e senha
- Geração de token JWT no login
- Validação automática do token nas requisições protegidas

## 🔐 Segurança

A aplicação utiliza **Spring Security** para proteger as rotas e **JWT** para autenticação. Apenas usuários autenticados podem acessar endpoints protegidos.  
Senhas são criptografadas com `BCrypt`.

## 📁 Estrutura de Pastas

