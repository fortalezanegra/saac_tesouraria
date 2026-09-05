package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaJaExistenteException extends BusinessException {
    public AuditoriaJaExistenteException(String mensagem){
        super(
                "AUDITORIA_JA_EXISTENTE",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
