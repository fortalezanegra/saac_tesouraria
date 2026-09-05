package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaJaAprovadaException extends BusinessException {
    public AuditoriaJaAprovadaException(String mensagem){
        super(
            "AUDITORIA_JA_APROVADA",
            mensagem,
            HttpStatus.BAD_REQUEST
        );
    }
}
