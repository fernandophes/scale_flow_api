package br.edu.ufersa.cc.lpoo.scale_flow.services.repertoire;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.repertoire.MusicRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.bands.Band;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.repertoire.Music;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.repertoire.MusicRepository;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.repertoire.ThemeRepository;
import lombok.AllArgsConstructor;
import lombok.val;

@Service
@AllArgsConstructor
public class MusicService {

    private final ModelMapper mapper;

    private final MusicRepository repository;
    private final ThemeRepository themeRepository;

    public MusicDto create(final Band band, final MusicRequest request) {
        val entity = mapper.map(request, Music.class);
        entity.setOwnerBand(band);

        val saved = repository.save(entity);
        return mapper.map(saved, MusicDto.class);
    }

    public Optional<MusicDto> findById(final UUID id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, MusicDto.class));
    }

    public Optional<MusicDto> update(final UUID id, final MusicRequest request) {
        // Buscar original
        return repository.findById(id)

                // Se encontrar...
                .map(original -> {
                    // Editar
                    mapper.map(request, original);

                    // Salvar
                    repository.save(original);

                    // Gerar DTO
                    return mapper.map(original, MusicDto.class);
                });
    }

    public void delete(final UUID id) {
        repository.deleteById(id);
    }

    public void addThemes(final UUID id, final Collection<String> themeNames) {
        // Buscar original
        repository.findById(id)

                // Se encontrar...
                .ifPresent(original -> {
                    // Gerar entidades dos temas
                    val themes = themeNames.stream()
                            .map(themeRepository::findByNameOrCreate)
                            .toList();

                    // Adicionar
                    original.getThemes().addAll(themes);

                    // Salvar
                    repository.save(original);
                });
    }

    public void removeThemes(final UUID id, final Collection<String> themeNames) {
        // Buscar original
        repository.findById(id)

                // Se encontrar...
                .ifPresent(original -> {
                    // Gerar entidades dos temas
                    val themes = themeNames.stream()
                            .map(themeRepository::findByName)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .toList();

                    // Remover
                    original.getThemes().removeAll(themes);

                    // Salvar
                    repository.save(original);
                });
    }

}
