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
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, UserDto.class))
                .toList();
    }

    public UserDto create(final UserRequest request) {
        // Salvar usuário
        final var entity = mapper.map(request, User.class);
        final var savedUser = repository.save(entity);

        return mapper.map(savedUser, UserDto.class);
    }

    public Optional<UserDto> findById(final UUID id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, UserDto.class));
    }

    public UserWithPasswordDto findByUsernameWithPassword(final String username) {
        return repository.findByUsername(username)
                .map(user -> mapper.map(user, UserWithPasswordDto.class))
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
    }

    public UserDto update(final UUID id, final UserRequest form) {
        final var original = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_MESSAGE));
        mapper.map(form, original);

        repository.save(original);
        return mapper.map(original, UserDto.class);
    }

    public void delete(final UUID id) {
        repository.deleteById(id);
    }

}
