package com.auth.auth_nutri.repository;

import com.auth.auth_nutri.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

//@NoRepositoryBean
public interface UserReposiotry<T> extends JpaRepository<T, String> {
}
