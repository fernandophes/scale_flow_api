package br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Role;
import lombok.val;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByOwnerBandAndName(Band ownerBand, String name);

    default Role findByOwnerBandAndNameOrCreate(final Band ownerBand, final String name) {
        return findByOwnerBandAndName(ownerBand, name)
                .orElseGet(() -> {
                    val role = Role.builder()
                            .ownerBand(ownerBand)
                            .name(name).build();

                    return save(role);
                });
    }

}
