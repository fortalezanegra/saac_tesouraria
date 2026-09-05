package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class BancoNaoEncontradoException extends BusinessException {
    public BancoNaoEncontradoException(String mensagem){
        super(
                "BANCO_NAO_ENCONTRADO",
                mensagem,
                HttpStatus.NOT_FOUND

        );
    }
}
