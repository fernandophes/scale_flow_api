package br.edu.ufersa.cc.lpoo.scale_flow.controllers.repertoire;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.services.repertoire.MusicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Music", description = "Endpoints relacionados ao domínio de músicas")
@RequestMapping("musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService service;

    @GetMapping("{id}")
    public ResponseEntity<MusicDto> findById(@PathVariable final UUID id) {
        return handleOptional(service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<MusicDto> update(@PathVariable final UUID id, @RequestBody final MusicRequest request) {
        return handleOptional(service.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/themes")
    public ResponseEntity<MusicDto> addThemes(@PathVariable final UUID id,
            @RequestBody final Collection<String> themeNames) {
        return handleOptional(service.addThemes(id, themeNames));
    }

    @DeleteMapping("{id}/themes")
    public ResponseEntity<MusicDto> removeThemes(@PathVariable final UUID id,
            @RequestBody final Collection<String> themeNames) {
        return handleOptional(service.removeThemes(id, themeNames));
    }

    private <T> ResponseEntity<T> handleOptional(final Optional<T> optional) {
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
