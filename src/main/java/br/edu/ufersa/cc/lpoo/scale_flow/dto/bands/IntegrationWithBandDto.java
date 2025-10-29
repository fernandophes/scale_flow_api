package br.edu.ufersa.cc.lpoo.scale_flow.dto.bands;

import java.util.UUID;

import br.edu.ufersa.cc.lpoo.scale_flow.enums.IntegrationType;
import lombok.Data;

@Data
public class IntegrationWithBandDto {

    private UUID id;
    private UUID userId;
    private BandDto band;
    private IntegrationType type;

}
