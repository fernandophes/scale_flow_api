package br.edu.ufersa.cc.lpoo.scale_flow.dto.bands;

import java.util.UUID;

import lombok.Data;

@Data
public class RoleDto {

    private UUID id;
    private UUID ownerBandId;
    private String name;

}
