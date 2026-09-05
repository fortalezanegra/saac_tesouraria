package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaIdObrigatorioException extends BusinessException {
    public AuditoriaIdObrigatorioException(String mensagem){
        super(
                "auditoriaId_OBRIGATORIO",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
