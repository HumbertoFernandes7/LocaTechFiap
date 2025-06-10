package br.com.fiap.locatech.locatech.controllers.handlers;

import br.com.fiap.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        var statusCode = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(statusCode.value()).body(new ResourceNotFoundDTO(e.getMessage(), statusCode.value()));
    }
}
