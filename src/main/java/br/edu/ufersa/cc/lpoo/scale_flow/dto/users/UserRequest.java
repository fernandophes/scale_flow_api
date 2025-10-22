package br.edu.ufersa.cc.lpoo.scale_flow.dto.users;

import java.time.LocalDate;

import br.edu.ufersa.cc.lpoo.scale_flow.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    private @NotBlank String fullName;
    private String nickname;
    private @NotNull Gender gender;
    private @NotNull LocalDate birthDate;
    private String phone;
    private @NotBlank String username;
    private @NotBlank @Email String email;
    private @NotBlank String password;

}
