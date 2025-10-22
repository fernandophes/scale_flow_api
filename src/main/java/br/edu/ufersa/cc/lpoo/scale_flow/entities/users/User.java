package br.edu.ufersa.cc.lpoo.scale_flow.entities.users;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Integration;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "users")
@Data
@Accessors(chain = false)
public class User {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Dados primitivos
     */

    @Column
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    /*
     * Relacionamentos não mapeados
     */

    @OneToMany(mappedBy = "user")
    private List<Integration> integrations;

}
