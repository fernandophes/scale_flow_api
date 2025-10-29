package br.edu.ufersa.cc.lpoo.scale_flow.controllers.bands;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.RoleDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.RoleRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.services.bands.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Roles", description = "Endpoints relacionados ao domínio de papéis")
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping("{id}")
    @Operation(summary = "Consultar detalhes do papel")
    public ResponseEntity<RoleDto> findById(@PathVariable final UUID id) {
        return handleOptional(service.findById(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Editar dados do papel")
    public ResponseEntity<RoleDto> update(@PathVariable final UUID id, @RequestBody final RoleRequest request) {
        return handleOptional(service.update(id, request));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar papel")
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
