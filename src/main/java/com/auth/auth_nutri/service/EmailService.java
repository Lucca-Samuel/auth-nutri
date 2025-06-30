package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.rabbitmq.email.EmailProducer;
import com.auth.auth_nutri.domain.Email;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.domain.enums.SexoEnum;
import com.auth.auth_nutri.dto.EmailDTO;
import com.auth.auth_nutri.repository.EmailRepository;
import com.auth.auth_nutri.service.responses.EmailResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EmailProducer emailProducer;

    @Value("${spring.mail.username}")
    private String remetente;


    public EmailResponseDTO createEmail(EmailDTO data){
        Medico medico = this.fromMedico(data.senderEmail());
        Paciente paciente = this.fromPaciente(data.receiverEmail());
        SexoEnum sexoEnum = SexoEnum.MASCULINO;
        /*Paciente paciente = new Paciente(
                "João",
                "Silva",
                "11999999999",
                "joao.silva@email.com",
                "recupera@email.com",
                "encryptedPassword",
                "SP",
                "São Paulo",
                "Centro",
                "Rua das Flores",
                "123",
                "01000-000",
                75.5,
                1.80,
                sexoEnum
        );*/
        Email newEmail = new Email(
            medico,
            paciente,
            data.senderEmail(),
            data.receiverEmail(),
            data.subject(),
            data.text(),
            data.pathFile()
        );

        this.save(newEmail);

        this.emailProducer.sendEmailToQueue(data);

        return EmailResponseDTO.from(newEmail);
    }

    public List<EmailResponseDTO> findAllEmails(int pagina, int itens){
        Page<Email> emails = this.repository.findAll(PageRequest.of(pagina, itens));
        return emails.stream()
                .map(email -> EmailResponseDTO.from(email))
                .toList();
    }

    public void sendEmail(EmailDTO data) throws Exception{

//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(data.receiverEmail());
//        message.setSubject(data.subject());
//        message.setText(data.text());
//        message.setFrom(remetente);
//
//        javaMailSender.send(message);
//
//        System.out.println("Email enviado: " + message.toString());

        //this.createEmail(data);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true = multipart

            helper.setTo(data.receiverEmail());
            helper.setSubject(data.subject());
            helper.setText(data.text(), true); // true para HTML
            helper.setFrom(remetente);

            for (String path : data.pathFile()) {  // Suponha que pathFiles() retorna uma List<String>
                File file = new File(path);
                if (file.exists() && file.canRead()) {
                    FileSystemResource fileResource = new FileSystemResource(file);
                    helper.addAttachment(file.getName(), fileResource);
                } else {
                    System.out.println("Arquivo inválido: " + path);
                }
            }

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // ou logue com uma lib apropriada
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }

    }


    private Medico fromMedico(String email){
        String id = this.medicoService.emailToId(email);
        System.out.println("ID LIMPO: [" + id + "] (" + id.length() + ")");
        return this.medicoService.internalFindById(id);
    }

    private Paciente fromPaciente(String email){
        String id = this.pacienteService.emailToId(email);
        Paciente paciente = this.pacienteService.findPacienteById(id);
        return paciente;
    }


    public void save(Email email){
        this.repository.save(email);
    }
}
