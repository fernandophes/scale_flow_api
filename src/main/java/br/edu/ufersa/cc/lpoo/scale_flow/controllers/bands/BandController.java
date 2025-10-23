package br.edu.ufersa.cc.lpoo.scale_flow.controllers.bands;

import java.util.List;
import java.util.Optional;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Band", description = "Endpoints relacionados ao domínio de bandas")
@RequestMapping("bands")
@RequiredArgsConstructor
public class BandController {

    private final BandService service;

    @GetMapping
    @Operation(summary = "Listar tudo", description = "Listar todas as bandas")
    public ResponseEntity<List<BandDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    @Operation(summary = "Cadastrar", description = "Cadastrar nova banda")
    public ResponseEntity<BandWithJoinCodeDto> create(@RequestBody final BandRequest request) {
        final var body = service.create(request);
        return ResponseEntity.status(201).body(body);
    }

    @GetMapping("{id}")
    @Operation(summary = "Detalhar", description = "Consultar detalhes da banda")
    public ResponseEntity<BandDto> findById(@PathVariable final UUID id) {
        return handleOptional(service.findById(id));
    }

    @GetMapping("{id}/join-code")
    @Operation(summary = "Detalhar com Join Code", description = "Consultar detalhes da banda, incluindo o Join Code")
    public ResponseEntity<BandWithJoinCodeDto> findByIdWithJoinCode(@PathVariable final UUID id) {
        return handleOptional(service.findByIdWithJoinCode(id));
    }

    @GetMapping("join-code/{joinCode}")
    @Operation(summary = "Detalhar por Join Code", description = "Consultar detalhes da banda a partir do Join Code")
    public ResponseEntity<BandDto> findByJoinCode(@PathVariable final String joinCode) {
        return handleOptional(service.findByJoinCode(joinCode));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar", description = "Editar dados da banda")
    public ResponseEntity<BandDto> update(@PathVariable final UUID id, @RequestBody final BandRequest request) {
        return handleOptional(service.update(id, request));
    }

    @PutMapping("{id}/join-code")
    @Operation(summary = "Redefinir Join Code", description = "Gerar novo Join Code para a banda")
    public ResponseEntity<BandWithJoinCodeDto> changeJoinCode(@PathVariable final UUID id) {
        return handleOptional(service.changeJoinCode(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar", description = "Deletar a banda")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> ResponseEntity<T> handleOptional(final Optional<T> optional) {
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
