package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class ContaFinanceiraNaoEncontradaException extends BusinessException {

    public ContaFinanceiraNaoEncontradaException(String mensagem){

        super(
                "CONTA_FINANCEIRA_NAO_ENCONTRADA",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
