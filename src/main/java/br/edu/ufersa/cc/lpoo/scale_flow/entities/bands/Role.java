package br.edu.ufersa.cc.lpoo.scale_flow.entities.bands;

import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "roles")
@Data
@Accessors(chain = false)
public class Role {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Chaves estrangeiras
     */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Band ownerBand;

    /*
     * Dados primitivos
     */

    @Column(nullable = false)
    private String name;

}
