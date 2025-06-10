package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
}
