package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaAtivaException extends BusinessException {
    public AuditoriaAtivaException(String mensagem){
        super(
                "AUDITORIA_ATIVA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
