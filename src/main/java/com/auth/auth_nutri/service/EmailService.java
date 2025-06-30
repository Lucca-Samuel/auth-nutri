package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.rabbitmq.email.EmailProducer;
import com.auth.auth_nutri.domain.Email;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.domain.enums.SexoEnum;
import com.auth.auth_nutri.dto.EmailDTO;
import com.auth.auth_nutri.repository.EmailRepository;
import com.auth.auth_nutri.service.responses.EmailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

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
            data.text()
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

    public void sendEmail(EmailDTO data){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(data.receiverEmail());
        message.setSubject(data.subject());
        message.setText(data.text());
        message.setFrom(remetente);

        javaMailSender.send(message);

        System.out.println("Email enviado: " + message.toString());

        //this.createEmail(data);
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
