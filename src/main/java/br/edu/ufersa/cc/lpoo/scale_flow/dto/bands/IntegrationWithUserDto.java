package br.edu.ufersa.cc.lpoo.scale_flow.dto.bands;

import java.util.UUID;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserDto;
import br.edu.ufersa.cc.lpoo.scale_flow.enums.IntegrationType;
import lombok.Data;

@Data
public class IntegrationWithUserDto {

    private UUID id;
    private UUID bandId;
    private UserDto user;
    private IntegrationType type;

}
