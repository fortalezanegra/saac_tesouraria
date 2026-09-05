package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class ValorTransferenciaInvalidoException extends BusinessException {

    public ValorTransferenciaInvalidoException(String mensagem){

        super(
                "VALOR_TRANSFERENCIA_INVALIDO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
