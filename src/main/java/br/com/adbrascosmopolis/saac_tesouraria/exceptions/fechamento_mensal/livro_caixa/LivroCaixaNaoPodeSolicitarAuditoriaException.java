package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaNaoPodeSolicitarAuditoriaException extends BusinessException {

    public LivroCaixaNaoPodeSolicitarAuditoriaException(String mensagem){
        super(
                "LIVRO_CAIXA_NAO_PODE_SOLICITAR_AUDITORIA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
