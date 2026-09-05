package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaNaoPodeSerReabertoException extends BusinessException {
    public LivroCaixaNaoPodeSerReabertoException(String mensagem){
        super(
                "LIVRO_CAIXA_NAO_PODE_SER_REABERTO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
