package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaNaoPossuiLivroCaixaVinculadoException extends BusinessException {
    public AuditoriaNaoPossuiLivroCaixaVinculadoException(String mensagem){
        super(
                "AUDITORIA_NAO_PUSSUI_LIVRO_CAIXA_VINCULADO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
