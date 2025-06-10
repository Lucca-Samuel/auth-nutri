package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, String> {
}
