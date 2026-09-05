package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaIdNaoPodeSerNullException extends BusinessException {
    public AuditoriaIdNaoPodeSerNullException(String mensagem){
        super(
                "AUDITORIA_NAO_PODE_SER_NULL",
                mensagem,
                HttpStatus.NOT_FOUND
        );

    }
}
