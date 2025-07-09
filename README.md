## 🛠️ Endpoints Principais

| Método | Rota                                      | Descrição                                  | Autenticação | Permissão      |
|--------|-------------------------------------------|--------------------------------------------|--------------|----------------|
| POST   | /auth/medico/register                     | Cadastro de novo médico                     | ❌ Não       | Livre          |
| POST   | /auth/paciente/register                   | Cadastro de novo paciente                   | ❌ Não       | Livre          |
| POST   | /auth/medico/login                        | Login do médico e geração do token JWT      | ❌ Não       | Livre          |
| POST   | /auth/paciente/login                      | Login do paciente e geração do token JWT    | ❌ Não       | Livre          |
| GET    | /medico/pagination?pagina=0&itens=2       | Paginação de médicos                        | ✅ Sim       | ADMIN / MÉDICO |
| GET    | /paciente/pacienteId?id={id}              | Buscar dados do paciente pelo ID            | ✅ Sim       | MÉDICO         |
| GET    | /email/send                               | Envio de e-mail com ou sem anexo            | ✅ Sim       | MÉDICO         |
| PUT    | /users/{id}                               | Atualizar dados do usuário                  | ✅ Sim       | Dono / Admin   |
| DELETE | /users/{id}                               | Remover um usuário                          | ✅ Sim       | Dono / Admin   |

---

## ⚙️ Configuração (application.properties)

A configuração da aplicação utiliza um arquivo application.yml com suporte a variáveis externas definidas no .env. Isso facilita a separação entre ambiente de desenvolvimento, produção e segurança dos dados sensíveis (como senhas e tokens).

📄 Arquivo application.yml
----------------------------------------------------------------
spring:
  config:
    import: optional:file:.env[.properties]

  application:
    name: auth-nutri

  mail:
    host: ${MAIL_HOST}
    port: ${MAIL_PORT}
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}
    protocol: ${MAIL_PROTOCOL}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: ${MAIL_TLS_ENABLED}

  datasource:
    url: jdbc:mysql://localhost:3306/auth-nutri
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

  rabbitmq:
    listener:
      simple:
        retry:
          enabled: true
          max-attempts: 5
          initial-interval: 1000
          multiplier: 2.0
          max-interval: 10000

auth-nutri:
  security:
    secret: ${JWT_SECRET}
----------------------------------------------------------------

---

## 🌱 Arquivo .env
Crie um arquivo .env na raiz do projeto com as seguintes variáveis:

----------------------------------------------------------------
# Banco de dados
DB_USERNAME=root
DB_PASSWORD=senha123

# JWT
JWT_SECRET=palavra-secreta

# E-mail
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=seu.email@gmail.com
MAIL_PASSWORD=sua_senha_app
MAIL_PROTOCOL=smtp
MAIL_TLS_ENABLED=true
----------------------------------------------------------------
