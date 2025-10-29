package br.edu.ufersa.cc.lpoo.scale_flow.entities.bands;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Difficulty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = { "owner_band_id", "name" }))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    /*
     * Relacionamentos não mapeados
     */

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Difficulty> difficulties;

}
