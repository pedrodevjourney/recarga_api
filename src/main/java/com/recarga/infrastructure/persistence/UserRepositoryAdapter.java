package com.recarga.infrastructure.persistence;

import com.recarga.domain.User;
import com.recarga.domain.ports.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPasswordHash()
        );
        UserJpaEntity saved = jpaRepository.save(entity);
        return new User(saved.getId(), saved.getFullName(), saved.getEmail(), saved.getPasswordHash());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(e -> new User(e.getId(), e.getFullName(), e.getEmail(), e.getPasswordHash()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
