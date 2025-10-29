package br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

}
