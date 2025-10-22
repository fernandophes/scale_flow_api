package br.edu.ufersa.cc.lpoo.scale_flow.dto.bands;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BandWithJoinCodeDto extends BandDto {

    private String joinCode;

}
