package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
    Page<Email> findAll(Pageable pageable);
}
