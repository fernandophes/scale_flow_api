package br.edu.ufersa.cc.lpoo.scale_flow.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

}
