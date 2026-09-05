package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaValidacaoCamposException extends BusinessException {
    public AuditoriaValidacaoCamposException(String mensagem){
        super(
                "CAMPOS_OBRIGATORIOS_DE_AUDITORIA_NAO_PRENCHIDOS",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
