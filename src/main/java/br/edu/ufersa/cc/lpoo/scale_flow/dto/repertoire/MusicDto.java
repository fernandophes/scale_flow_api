package br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class MusicDto {

    private UUID id;
    private UUID ownerBandId;
    private String title;
    private String artist;
    private List<ThemeDto> themes;

}
