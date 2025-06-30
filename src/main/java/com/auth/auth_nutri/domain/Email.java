package com.auth.auth_nutri.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "emails_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Medico sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Paciente receiver;

    private String senderEmail;

    private String receiverEmail;

    private String subject;

    @Lob
    private String text;

    private List<String> pathFile;

    public Email(Medico sender, Paciente receiver, String senderEmail, String receiverEmail, String subject, String text, List<String> pathFile){
        this.sender = sender;
        this.receiver = receiver;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.subject = subject;
        this.text = text;
        this.pathFile = pathFile;
    }
}
