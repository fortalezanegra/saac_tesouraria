package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class CategoriaInvalidaException extends BusinessException {

    public CategoriaInvalidaException(String  mensagem){

        super(
            "CATEGORIA_INVALIDA",
            mensagem,
            HttpStatus.BAD_REQUEST
        );
    }
}
