package br.edu.ufersa.cc.lpoo.scale_flow.repositories.repertoire;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, UUID> {

}
