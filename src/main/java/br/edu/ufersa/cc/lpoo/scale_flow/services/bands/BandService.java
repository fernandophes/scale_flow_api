package br.edu.ufersa.cc.lpoo.scale_flow.services.bands;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.BandWithJoinCodeDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.bands.IntegrationDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Integration;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.users.User;
import br.edu.ufersa.cc.lpoo.scale_flow.enums.IntegrationType;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.bands.BandRepository;
import br.edu.ufersa.cc.lpoo.scale_flow.services.repertoire.MusicService;
import br.edu.ufersa.cc.lpoo.scale_flow.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class BandService {

    private static final int JOIN_CODE_SIZE = 6;

    private final ModelMapper mapper;

    private final BandRepository repository;

    private final AuthUtils authUtils;
    private final MusicService musicService;

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
        entity.setJoinCode(generateJoinCode());

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

    public Optional<BandWithJoinCodeDto> findByIdWithJoinCode(final UUID id) {
        // Buscar
        return repository.findById(id)

                // Se encontrar, gerar DTO
                .map(entity -> mapper.map(entity, BandWithJoinCodeDto.class));
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
                    original.setJoinCode(generateJoinCode());

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

    public Optional<IntegrationDto> join(final String joinCode) {
        val sanitized = joinCode.toUpperCase().trim();

        return repository.findByJoinCode(sanitized)
                .map(band -> {
                    // Obter usuário logado
                    val loggedUser = mapper.map(authUtils.getLoggedUser(), User.class);

                    // Construir integração
                    val integration = new Integration();
                    integration.setBand(band);
                    integration.setUser(loggedUser);
                    integration.setType(IntegrationType.MEMBER);

                    // Adicionar à banda
                    band.getIntegrations().add(integration);

                    // Salvar
                    repository.save(band);

                    // Gerar DTO
                    return mapper.map(integration, IntegrationDto.class);
                });
    }

    public List<MusicDto> listMusics(final UUID id) {
        return repository.findById(id)
                .map(entity -> entity.getMusics().stream()
                        .map(music -> mapper.map(music, MusicDto.class))
                        .toList())
                .orElseGet(Collections::emptyList);
    }

    public Optional<MusicDto> createMusic(final UUID id, final MusicRequest request) {
        return repository.findById(id)
                .map(entity -> musicService.create(entity, request));
    }

    private String generateJoinCode() {
        return RandomStringUtils.secure()
                .nextAlphanumeric(JOIN_CODE_SIZE)
                .toUpperCase();
    }

}
