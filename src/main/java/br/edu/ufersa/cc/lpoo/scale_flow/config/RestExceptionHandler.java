package br.edu.ufersa.cc.lpoo.scale_flow.config;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ufersa.cc.lpoo.scale_flow.exceptions.UserAlreadyInBandException;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    private record ValidationError(String field, String message) {
        ValidationError(final FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrors(final MethodArgumentNotValidException e) {
        val errors = e.getFieldErrors().stream()
                .map(ValidationError::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(final DataIntegrityViolationException e) {
        for (var cause = e.getCause(); cause != null; cause = cause.getCause()) {
            if (cause instanceof SQLException sqlException && sqlException.getSQLState().equals("23505")) {
                return ResponseEntity.badRequest().body("Registro duplicado");
            }
        }

        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserAlreadyInBandException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(final UserAlreadyInBandException e) {
        for (var cause = e.getCause(); cause != null; cause = cause.getCause()) {
            if (cause instanceof SQLException sqlException && sqlException.getSQLState().equals("23505")) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        return ResponseEntity.badRequest().body("Erro de integridade dos dados");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOthers(final Exception e) {
        log.error("Erro interceptado", e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}