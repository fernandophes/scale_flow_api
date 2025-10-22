package br.edu.ufersa.cc.lpoo.scale_flow.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private @NotBlank String username;
    private @NotBlank String password;

}
