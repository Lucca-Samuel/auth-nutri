package com.auth.auth_nutri.dto;

public record EmailDTO(
        String senderEmail,
        String receiverEmail,
        String subject,
        String text
) {
}
