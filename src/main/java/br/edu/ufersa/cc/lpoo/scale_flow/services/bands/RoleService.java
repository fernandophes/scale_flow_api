package br.edu.ufersa.cc.lpoo.scale_flow.services.bands;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.RoleDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.RoleRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.val;

@Service
@AllArgsConstructor
public class RoleService {

    private final ModelMapper mapper;

    private final RoleRepository repository;

    public RoleDto create(final Band band, final RoleRequest request) {
        val saved = repository.findByOwnerBandAndNameOrCreate(band, request.getName());
        return mapper.map(saved, RoleDto.class);
    }

    public Optional<RoleDto> findById(final UUID id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, RoleDto.class));
    }

    public Optional<RoleDto> update(final UUID id, final RoleRequest request) {
        // Buscar original
        return repository.findById(id)

                // Se encontrar...
                .map(original -> {
                    // Editar
                    mapper.map(request, original);

                    // Salvar
                    repository.save(original);

                    // Gerar DTO
                    return mapper.map(original, RoleDto.class);
                });
    }

    public void delete(final UUID id) {
        repository.deleteById(id);
    }

}
