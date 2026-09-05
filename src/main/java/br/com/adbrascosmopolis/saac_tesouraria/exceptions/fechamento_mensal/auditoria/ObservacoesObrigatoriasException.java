package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class ObservacoesObrigatoriasException extends BusinessException {
    public ObservacoesObrigatoriasException(String mensagem){
        super(
                "OBSERVACOES_OBRIGATORIAS",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
