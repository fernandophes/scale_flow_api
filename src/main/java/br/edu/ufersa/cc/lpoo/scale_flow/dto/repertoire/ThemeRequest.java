package br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ThemeRequest {

    private @NotBlank String name;

}
