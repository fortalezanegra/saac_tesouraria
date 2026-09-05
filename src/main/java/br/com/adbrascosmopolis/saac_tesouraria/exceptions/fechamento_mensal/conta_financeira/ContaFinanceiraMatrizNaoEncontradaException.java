package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class ContaFinanceiraMatrizNaoEncontradaException extends BusinessException {
    public ContaFinanceiraMatrizNaoEncontradaException(String mensagem){
        super(
                "CONTA_FINANCEIRA_MATRIZ_NAO_ENCONTRADA",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
