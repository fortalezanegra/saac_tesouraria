package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class FechamentoEmExecucaoException extends BusinessException {

    public FechamentoEmExecucaoException(String mensagem){
        super(
                "FECHAMENTO_EM_EXECUCAO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
