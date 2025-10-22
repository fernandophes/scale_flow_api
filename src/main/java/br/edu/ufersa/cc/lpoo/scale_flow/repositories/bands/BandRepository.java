package br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;

@Repository
public interface BandRepository extends JpaRepository<Band, UUID> {

    Optional<Band> findByJoinCode(String joinCode);

}
