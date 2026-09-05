package br.com.adbrascosmopolis.saac_tesouraria.exceptions.common;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

    private final  String codigo;
    private final HttpStatus status;

    protected BusinessException(
            String codigo,
            String mensagem,
            HttpStatus status
    ){
        super(mensagem);
        this.codigo = codigo;
        this.status = status;
    }

    public String getCodigo(){
        return codigo;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
