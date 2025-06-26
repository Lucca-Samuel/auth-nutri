package com.auth.auth_nutri.service.responses;

import com.auth.auth_nutri.domain.Email;

public record EmailResponseDTO(
        String senderEmail,
        String receiverEmail,
        String subject,
        String text
) {
    public static EmailResponseDTO from(Email email){
        return new EmailResponseDTO(
                email.getSenderEmail(),
                email.getReceiverEmail(),
                email.getSubject(),
                email.getText()
        );
    }
}
