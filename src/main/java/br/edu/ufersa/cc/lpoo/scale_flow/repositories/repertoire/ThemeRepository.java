package br.edu.ufersa.cc.lpoo.scale_flow.repositories.repertoire;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Theme;
import lombok.val;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, UUID> {

    Optional<Theme> findByName(String name);

    default Theme findByNameOrCreate(final String name) {
        return findByName(name)
                .orElseGet(() -> {
                    val theme = Theme.builder()
                            .name(name).build();

                    return save(theme);
                });
    }

}
