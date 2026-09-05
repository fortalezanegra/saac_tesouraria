package br.com.adbrascosmopolis.saac_tesouraria.exceptions.situacao;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class SituacaoNaoEncontradoException extends BusinessException {

    public SituacaoNaoEncontradoException(String  mensagem){
        super(
            "SITUACAO_NAO_ENCONTRADA",
            mensagem,
            HttpStatus.NOT_FOUND
        );
    }
}
