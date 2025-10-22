package br.edu.ufersa.cc.lpoo.scale_flow.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    private @NotBlank String name;
    private @NotBlank @Email String email;
    private @NotBlank String password;

}
