package br.edu.ufersa.cc.lpoo.scale_flow.services.bands;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandWithJoinCodeDto;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands.BandRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class BandService {

    private final ModelMapper mapper;

    private final BandRepository repository;

    public List<BandDto> listAll() {
        // Buscar
        return repository.findAll().stream()

                // Gerar DTO
                .map(entity -> mapper.map(entity, BandDto.class))
                .toList();
    }

    public BandWithJoinCodeDto create(final BandRequest request) {
        // Gerar entidade
        val entity = mapper.map(request, Band.class);

        // Definir código de ingresso
        val joinCode = RandomStringUtils.secure().nextAlphanumeric(6);
        entity.setJoinCode(joinCode);

        // Salvar
        val saved = repository.save(entity);

        // Gerar DTO
        return mapper.map(saved, BandWithJoinCodeDto.class);
    }

    public Optional<BandDto> findById(final UUID id) {
        // Buscar
        return repository.findById(id)

                // Se encontrar, gerar DTO
                .map(entity -> mapper.map(entity, BandDto.class));
    }

    public Optional<BandDto> findByJoinCode(final String joinCode) {
        // Buscar
        return repository.findByJoinCode(joinCode)

                // Se encontrar, gerar DTO
                .map(entity -> mapper.map(entity, BandDto.class));
    }

    public Optional<BandDto> update(final UUID id, final BandRequest request) {
        // Buscar original
        return repository.findById(id)

                // Se encontrar...
                .map(original -> {
                    // Editar
                    mapper.map(request, original);

                    // Salvar
                    repository.save(original);

                    // Gerar DTO
                    return mapper.map(original, BandDto.class);
                });
    }

    public Optional<BandWithJoinCodeDto> changeJoinCode(final UUID id) {
        // Buscar original
        return repository.findById(id)

                // Se encontrar...
                .map(original -> {
                    // Definir novo código de ingresso
                    val joinCode = RandomStringUtils.secure().nextAlphanumeric(6);
                    original.setJoinCode(joinCode);

                    // Salvar
                    repository.save(original);

                    // Gerar DTO
                    return mapper.map(original, BandWithJoinCodeDto.class);
                });
    }

    public void delete(final UUID id) {
        // Deletar
        repository.deleteById(id);
    }

}
