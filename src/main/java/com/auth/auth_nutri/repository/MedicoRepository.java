package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, String> {
    Optional<Medico> findByEmail(String email);

    Page<Medico> findAll(Pageable pageable);
}
