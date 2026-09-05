package br.com.adbrascosmopolis.saac_tesouraria.exceptions.common;

import org.springframework.http.HttpStatus;

public class GenericException extends BusinessException{
    public GenericException(String mensagem){
        super(
                "EXCECAO_GENERICA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
