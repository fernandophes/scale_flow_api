package br.edu.ufersa.cc.lpoo.scale_flow.dto.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ufersa.cc.lpoo.scale_flow.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String fullName;
    private String nickname;
    private Gender gender;
    private LocalDate birthDate;
    private String phone;
    private String username;
    private String email;
    private LocalDateTime emailVerifiedAt;

}
