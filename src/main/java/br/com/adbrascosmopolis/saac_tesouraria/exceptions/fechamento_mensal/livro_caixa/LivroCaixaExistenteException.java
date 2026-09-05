package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaExistenteException extends BusinessException {
    public LivroCaixaExistenteException(String mensagem){
        super(
                "LIVRO_CAIXA_EXISTENTE",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
