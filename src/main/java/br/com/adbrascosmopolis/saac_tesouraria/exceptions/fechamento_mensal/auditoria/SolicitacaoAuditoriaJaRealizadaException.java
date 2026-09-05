package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class SolicitacaoAuditoriaJaRealizadaException extends BusinessException {
    public SolicitacaoAuditoriaJaRealizadaException(String mensagem){
        super(
                "SOLICITACAO_DE_AUDITORIA_JA_REALIZADA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
