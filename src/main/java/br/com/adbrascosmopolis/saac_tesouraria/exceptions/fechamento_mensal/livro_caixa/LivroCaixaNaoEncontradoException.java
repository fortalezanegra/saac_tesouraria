package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaNaoEncontradoException extends BusinessException {

    public LivroCaixaNaoEncontradoException(String mensagem){
        super(
                "LIVRO_NAO_ENCONTRADO_EXCEPTION",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
