package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class CompetenciaNaoEncontradaException extends BusinessException {
    public CompetenciaNaoEncontradaException(String  mensagem){
        super(
                "COMPETENCIA_NAO_ENCONTRADA",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
