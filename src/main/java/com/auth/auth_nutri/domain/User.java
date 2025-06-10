package com.auth.auth_nutri.domain;

import com.auth.auth_nutri.domain.rolesConfig.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private UserRole role;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @Size(max = 50)
    private String sobrenome;

    @NotNull
    @Size(max = 20)
    private String telefone;

    @NotNull
    @Email
    @Size(max = 155)
    @Column(unique = true)
    private String email;

    @Email
    @Size(max = 155)
    private String emailRecovery;

    @NotNull
    @Size(max = 255)
    private String senha;

    @NotNull
    @Size(max = 155)
    private String estado;

    @NotNull
    @Size(max = 155)
    private String cidade;

    @Size(max = 155)
    private String bairro;

    @Size(max = 155)
    private String logradouro;

    @Size(max = 10)
    private String numCasa;

    @Size(max = 8)
    private String cep;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
