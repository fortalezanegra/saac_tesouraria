package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaNaoPodeSerConcluidaException extends BusinessException {
    public AuditoriaNaoPodeSerConcluidaException(String mensagem){
        super(
                "AUDITORIA_NAO_PODE_SER_CONCLUIDA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}