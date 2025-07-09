# 🛡️ Spring Boot - API de Usuários com JWT, Perfis, E-mail e RabbitMQ

Aplicação Java com **Spring Boot** que realiza o gerenciamento de **usuários (Médicos e Pacientes)**, com **autenticação via JWT**, controle de acesso por perfil, **envio de e-mails assíncronos com RabbitMQ** e **suporte a anexos, CC e CCO**.

---

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- JavaMailSender (Spring Mail)
- RabbitMQ
- Lombok
- MySQL ou H2
- Maven

---

## 📚 Funcionalidades

- Cadastro de Médicos e Pacientes com permissões distintas
- Login separado por perfil
- Geração e validação de token JWT
- Controle de acesso com base em roles
- Envio de e-mails com suporte a anexos, CC e CCO
- Paginação de médicos
- Atualização e remoção de usuários
- **Processamento de envio de e-mails via fila com RabbitMQ**

---

## 📦 Integração com RabbitMQ

O envio de e-mails é feito de forma **assíncrona** utilizando **RabbitMQ**. Isso significa que:

- Quando um e-mail é solicitado, ele é colocado em uma **fila de mensagens** (`email_queue`)
- Um **consumidor** separado processa esse e-mail e realiza o envio
- Isso garante que o sistema não trave nem perca requisições de e-mail, mesmo sob carga alta

### Vantagens:

- ✅ Desacoplamento entre requisição e envio real
- ✅ Tolerância a falhas e reprocessamento
- ✅ Performance e escalabilidade

---

## 👥 Tipos de Usuários

| Tipo     | Permissões                                                                 |
|----------|----------------------------------------------------------------------------|
| Médico   | Visualiza pacientes, envia e-mails, gerencia seu perfil e agendamentos     |
| Paciente | Consulta seus dados e agenda, recebe e-mails e confirmações médicas        |

---

## 📧 Envio de E-mails

Utilizando `JavaMailSender`, com suporte a:

- Texto simples ou HTML
- Anexos (PDF, imagens, etc.)
- CC (com cópia) e BCC (com cópia oculta)
- Envio assíncrono com RabbitMQ

### Exemplo de payload para envio:

```json
{
  "senderEmail": "clinica@email.com",
  "receiverEmail": "paciente@email.com",
  "subject": "Resultado do Exame",
  "text": "Segue em anexo o resultado do exame.",
  "cc": ["outro.medico@email.com"],
  "bcc": ["secretaria@email.com"],
  "pathFile": ["C:/documentos/exame.pdf"]
}

