package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PacienteRepository extends UserReposiotry<Paciente> {
    Optional<Paciente> findByEmail(String email);
}
