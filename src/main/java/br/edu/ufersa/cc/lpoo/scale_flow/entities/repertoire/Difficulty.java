package br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire;

import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "bands")
@Data
@Accessors(chain = false)
public class Difficulty {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Chaves estrangeiras
     */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Music music;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Role role;

    /*
     * Dados primitivos
     */

    @Column(nullable = false)
    private double level;

}
