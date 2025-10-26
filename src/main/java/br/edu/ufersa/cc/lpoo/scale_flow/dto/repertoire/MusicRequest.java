package br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class MusicRequest {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WithThemes extends MusicRequest {
        private List<String> themes;
    }

    private @NotBlank String title;
    private String artist;

}
