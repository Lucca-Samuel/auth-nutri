package com.auth.auth_nutri.service;

import com.auth.auth_nutri.domain.Email;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.dto.EmailDTO;
import com.auth.auth_nutri.repository.EmailRepository;
import com.auth.auth_nutri.service.responses.EmailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Value("${spring.mail.username}")
    private String remetente;


    public EmailResponseDTO createEmail(EmailDTO data){
        Medico medico = this.fromMedico(data.senderEmail());
        Paciente paciente = this.fromPaciente(data.receiverEmail());
        Email newEmail = new Email(
            medico,
            paciente,
            data.senderEmail(),
            data.receiverEmail(),
            data.subject(),
            data.text()
        );

        this.save(newEmail);

        return EmailResponseDTO.from(newEmail);
    }

    public List<EmailResponseDTO> findAllEmails(int pagina, int itens){
        Page<Email> emails = this.repository.findAll(PageRequest.of(pagina, itens));
        return emails.stream()
                .map(email -> EmailResponseDTO.from(email))
                .toList();
    }


    









    private Medico fromMedico(String email){
        String id = this.medicoService.emailToId(email);
        Medico medico = this.medicoService.internalFindById(id);
        return medico;
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
