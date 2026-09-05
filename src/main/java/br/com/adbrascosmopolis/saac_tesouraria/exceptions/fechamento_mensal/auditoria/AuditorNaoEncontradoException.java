package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditorNaoEncontradoException extends BusinessException {
    public AuditorNaoEncontradoException(String mensagem){
        super(
                "AUDITOR_NAO_ENCONTRADO",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
