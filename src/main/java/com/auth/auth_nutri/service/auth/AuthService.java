package com.auth.auth_nutri.service.auth;

import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.repository.MedicoRepository;
import com.auth.auth_nutri.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Medico> medico = medicoRepository.findByEmail(email);
        if (medico.isPresent()){
            return medico.get();
        }

        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isPresent()){
            return paciente.get();
        }

        throw new UsernameNotFoundException("Usuário com email: " + email + ", não encontrado");
    }


}
