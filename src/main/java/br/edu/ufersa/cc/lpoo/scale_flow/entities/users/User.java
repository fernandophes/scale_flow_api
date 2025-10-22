package br.edu.ufersa.cc.lpoo.scale_flow.entities.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;

import br.edu.ufersa.cc.lpoo.scale_flow.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@DynamicUpdate
@Table(name = "users")
@Data
@Accessors(chain = false)
@EqualsAndHashCode(callSuper = false)
public class User {

    /*
     * Chave primária
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
     * Enumerações
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    /*
     * Dados primitivos
     */

    @Column(nullable = false)
    private String fullName;

    @Column
    private String nickname;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column
    private String phone;

    /*
     * Dados de acesso
     */

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column
    private LocalDateTime emailVerifiedAt;

    @Column(nullable = false)
    private String password;

}
