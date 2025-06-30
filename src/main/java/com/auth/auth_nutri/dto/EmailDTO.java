package com.auth.auth_nutri.dto;

import java.util.List;

public record EmailDTO(
        String senderEmail,
        String receiverEmail,
        String subject,
        String text,
        List<String> pathFile
) {
}
