package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaNaoEncontradaException extends BusinessException {
    public AuditoriaNaoEncontradaException(String mensagem){
        super(
                "AUDITORIA_NAO_ENCONTRADA",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
