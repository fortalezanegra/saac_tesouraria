package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class LivroCaixaNaoAguardaFechamentoException extends BusinessException {

    public LivroCaixaNaoAguardaFechamentoException(String mensagem){
        super(
                "LIVRO_CAIXA_NAO_AGUARDA_FECHMANETO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
