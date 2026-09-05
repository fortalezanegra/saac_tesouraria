package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaInvalidoException extends BusinessException {

    public LivroCaixaInvalidoException(String mensagem){
        super(
                "LIVRO_CAIXA_INVALIDO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
