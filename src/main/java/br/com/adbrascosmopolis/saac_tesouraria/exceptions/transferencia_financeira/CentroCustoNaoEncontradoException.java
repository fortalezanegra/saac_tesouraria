package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class CentroCustoNaoEncontradoException extends BusinessException {

    public CentroCustoNaoEncontradoException(String mensagem){
        super(
                "CENTRO_DE_CUSTO_NAO_ENCONTRADO",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }

}
