package br.edu.ufersa.cc.lpoo.scale_flow.services.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserDto;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserRequest;
import br.edu.ufersa.cc.lpoo.scale_flow.dto.users.UserWithPasswordDto;
import br.edu.ufersa.cc.lpoo.scale_flow.entities.users.User;
import br.edu.ufersa.cc.lpoo.scale_flow.repositories.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private static final String NOT_FOUND_MESSAGE = "Usuário não encontrado.";

    private final ModelMapper mapper;

    private final UserRepository repository;

    public List<UserDto> findAll() {
        // Buscar
        return repository.findAll().stream()

                // Gerar DTO
                .map(entity -> mapper.map(entity, UserDto.class))
                .toList();
    }

    public UserDto create(final UserRequest request) {
        // Gerar entidade
        final var entity = mapper.map(request, User.class);

        // Salvar
        final var savedUser = repository.save(entity);

        // Gerar DTO
        return mapper.map(savedUser, UserDto.class);
    }

    public Optional<UserDto> findById(final UUID id) {
        // Buscar
        return repository.findById(id)

                // Se encontrar, gerar DTO
                .map(entity -> mapper.map(entity, UserDto.class));
    }

    public UserWithPasswordDto findByEmailWithPassword(final String email) {
        // Buscar
        return repository.findByEmail(email)

                // Se encontrar, gerar DTO
                .map(user -> mapper.map(user, UserWithPasswordDto.class))

                // Senão, lançar exceção
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    public UserDto update(final UUID id, final UserRequest form) {
        final var original = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
        mapper.map(form, original);

        repository.save(original);
        return mapper.map(original, UserDto.class);
    }

    public void delete(final UUID id) {
        // Deletar
        repository.deleteById(id);
    }

}
