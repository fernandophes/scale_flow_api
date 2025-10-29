package br.edu.ufersa.cc.lpoo.scale_flow.entities.bands;

import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.users.User;
import br.edu.ufersa.cc.lpoo.scale_flow.enums.IntegrationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "integrations", uniqueConstraints = @UniqueConstraint(columnNames = { "band_id", "user_id" }))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false)
public class Integration {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Relacionamentos mapeados
     */

    @ManyToOne(optional = false)
    private Band band;

    @ManyToOne(optional = false)
    private User user;

    /*
     * Enumerações
     */

    @Enumerated(EnumType.STRING)
    private IntegrationType type;

}
