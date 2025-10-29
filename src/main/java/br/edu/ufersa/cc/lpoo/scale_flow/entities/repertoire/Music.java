package br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "musics")
@Data
@Accessors(chain = false)
public class Music {

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
    private String title;

    @Column
    private String artist;

    @ManyToMany
    @JoinTable(name = "musics_themes", joinColumns = { @JoinColumn(name = "music_id") }, inverseJoinColumns = {
            @JoinColumn(name = "theme_id") }, uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "music_id", "theme_id" }) })
    private List<Theme> themes;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Difficulty> difficulties;

}
