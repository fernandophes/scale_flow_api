package br.edu.ufersa.cc.lpoo.scale_flow.controllers.bands;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandWithJoinCodeDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.IntegrationDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.IntegrationWithBandDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.IntegrationWithUserDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.exceptions.UserAlreadyInBandException;
import br.edu.ufersa.cc.lpoo.scale_flow.services.bands.BandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Band", description = "Endpoints relacionados ao domínio de bandas")
@RequestMapping("bands")
@RequiredArgsConstructor
public class BandController {

    private final BandService service;

    @GetMapping
    @Operation(summary = "Listar todas as bandas das quais eu participo")
    public ResponseEntity<List<IntegrationWithBandDto>> listMyBands() {
        return ResponseEntity.ok(service.listIntegrationsByLoggedUser());
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova banda")
    public ResponseEntity<BandWithJoinCodeDto> create(@RequestBody final BandRequest request) {
        val body = service.create(request);
        return ResponseEntity.status(201).body(body);
    }

    @GetMapping("{id}")
    @Operation(summary = "Consultar detalhes da banda")
    public ResponseEntity<BandDto> findById(@PathVariable final UUID id) {
        return handleOptional(service.findById(id));
    }

    @GetMapping("{id}/join-code")
    @Operation(summary = "Consultar detalhes da banda, incluindo o Join Code")
    public ResponseEntity<BandWithJoinCodeDto> findByIdWithJoinCode(@PathVariable final UUID id) {
        return handleOptional(service.findByIdWithJoinCode(id));
    }

    @GetMapping("join-code/{joinCode}")
    @Operation(summary = "Consultar detalhes da banda a partir do Join Code")
    public ResponseEntity<BandDto> findByJoinCode(@PathVariable final String joinCode) {
        return handleOptional(service.findByJoinCode(joinCode));
    }

    @PutMapping("{id}")
    @Operation(summary = "Editar dados da banda")
    public ResponseEntity<BandDto> update(@PathVariable final UUID id, @RequestBody final BandRequest request) {
        return handleOptional(service.update(id, request));
    }

    @PutMapping("{id}/join-code")
    @Operation(summary = "Gerar novo Join Code para a banda")
    public ResponseEntity<BandWithJoinCodeDto> changeJoinCode(@PathVariable final UUID id) {
        return handleOptional(service.changeJoinCode(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar a banda")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/integrants")
    @Operation(summary = "Listar integrantes da banda")
    public ResponseEntity<List<IntegrationWithUserDto>> listIntegrations(@PathVariable final UUID id) {
        return ResponseEntity.ok(service.listIntegrations(id));
    }

    @PostMapping("join/{joinCode}")
    @Operation(summary = "Ingressar na banda")
    public ResponseEntity<IntegrationDto> join(@PathVariable final String joinCode) {
        try {
            return handleOptional(service.join(joinCode));
        } catch (final DataIntegrityViolationException e) {
            throw new UserAlreadyInBandException("Você já faz parte dessa banda", e);
        }
    }

    @GetMapping("{id}/musics")
    @Operation(summary = "Listar todas as músicas do repertório da banda")
    public ResponseEntity<List<MusicDto>> listMusics(@PathVariable final UUID id) {
        return ResponseEntity.ok(service.listMusics(id));
    }

    @PostMapping("{id}/musics")
    @Operation(summary = "Cadastrar nova música no repertório da banda")
    public ResponseEntity<MusicDto> createMusic(@PathVariable final UUID id,
            @RequestBody @Valid final MusicRequest.WithThemes request) {
        return handleOptional(service.createMusic(id, request));
    }

    private <T> ResponseEntity<T> handleOptional(final Optional<T> optional) {
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
