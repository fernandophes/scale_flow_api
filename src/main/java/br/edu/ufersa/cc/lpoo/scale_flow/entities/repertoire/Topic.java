package br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "topics")
@Data
@Accessors(chain = false)
public class Topic {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Dados primitivos
     */

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Music> musics;

}
