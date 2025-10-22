package br.edu.ufersa.cc.lpoo.scale_flow.controllers.bands;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandWithJoinCodeDto;
import br.edu.ufersa.cc.lpoo.scale_flow.services.bands.BandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Band")
@RequestMapping("bands")
@RequiredArgsConstructor
public class BandController {

    private final BandService service;

    @GetMapping
    public ResponseEntity<List<BandDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    public ResponseEntity<BandWithJoinCodeDto> create(final BandRequest request) {
        final var body = service.create(request);
        return ResponseEntity.status(201).body(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<BandDto> findById(@PathVariable final UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("join-code/{joinCode}")
    public ResponseEntity<BandDto> findByJoinCode(@PathVariable final String joinCode) {
        return service.findByJoinCode(joinCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<BandDto> update(final UUID id, final BandRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("join-code")
    public ResponseEntity<BandWithJoinCodeDto> changeJoinCode(final UUID id) {
        return service.changeJoinCode(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
