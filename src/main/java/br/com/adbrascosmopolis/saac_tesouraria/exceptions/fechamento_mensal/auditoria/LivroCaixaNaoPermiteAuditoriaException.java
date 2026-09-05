package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaNaoPermiteAuditoriaException extends BusinessException {
    public LivroCaixaNaoPermiteAuditoriaException(String mensagem){
        super(
                "LIVRO_CAIXA_NAO_PERMITE_AUDITORIA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
