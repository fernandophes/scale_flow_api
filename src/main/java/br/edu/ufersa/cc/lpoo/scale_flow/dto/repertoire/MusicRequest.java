package br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire;

import java.util.List;

import lombok.Data;

@Data
public class MusicRequest {

    private String title;
    private String artist;
    private List<ThemeRequest> themes;

}
