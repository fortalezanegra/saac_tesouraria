package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaFechadoException extends BusinessException {

    public LivroCaixaFechadoException(String mensagem){
        super(
                "LIVRO_CAIXA_FECHADO_EXCEPTION",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
