package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class ContaFinanceiraELivroCaixaIncompativeisException extends BusinessException {

    public ContaFinanceiraELivroCaixaIncompativeisException(String mensagem){

        super(
                "CONTA_FINANCEIRA_E_LIVRO_CAIXA_INCOMPATIVEIS",
                mensagem,
                HttpStatus.BAD_REQUEST
        );

    }
}
