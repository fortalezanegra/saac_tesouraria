package br.com.adbrascosmopolis.saac_tesouraria.exceptions.handler;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Trata exceções de negócio (sua exceção personalizada)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handlerBusinessException(BusinessException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getCodigo(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    // Trata erros de validação de @Valid (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handlerValidationException(MethodArgumentNotValidException ex) {

        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map((FieldError fieldError) -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ApiErrorResponse response = new ApiErrorResponse(
                "ERRO_VALIDACAO",
                mensagem
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // Trata qualquer outra exceção não prevista
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handlerGenericException(Exception ex) {

        log.error("Erro interno não tratado", ex);

        ApiErrorResponse response = new ApiErrorResponse(
                "ERRO_INTERNO",
                "Ocorreu um erro interno na aplicação."
        );

        return ResponseEntity
                .internalServerError()
                .body(response);
    }
}
