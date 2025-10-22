package br.edu.ufersa.cc.lpoo.scale_flow.repositories.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.users.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
