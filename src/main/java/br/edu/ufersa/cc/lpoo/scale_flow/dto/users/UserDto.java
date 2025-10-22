package br.edu.ufersa.cc.lpoo.scale_flow.dto.users;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String name;
    private String email;

}
