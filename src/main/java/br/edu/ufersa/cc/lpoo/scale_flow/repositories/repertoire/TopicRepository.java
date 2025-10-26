package br.edu.ufersa.cc.lpoo.scale_flow.repositories.repertoire;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Topic;
import lombok.val;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    Optional<Topic> findByName(String name);

    default Topic findByNameOrCreate(final String name) {
        return findByName(name)
                .orElseGet(() -> {
                    val topic = new Topic();
                    topic.setName(name);

                    return save(topic);
                });
    }

}
