package br.edu.ufersa.cc.lpoo.scale_flow.entities.bands;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Music;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.users.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "bands")
@Data
@Accessors(chain = false)
public class Band {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Dados primitivos
     */

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String joinCode;

    /*
     * Relacionamentos não mapeados
     */

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL)
    private List<Integration> integrations;

    @ManyToMany
    @JoinTable(name = "integrations", joinColumns = @JoinColumn(name = "band_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> integrants;

    @OneToMany(mappedBy = "ownerBand", cascade = CascadeType.ALL)
    private List<Music> musics;

}
