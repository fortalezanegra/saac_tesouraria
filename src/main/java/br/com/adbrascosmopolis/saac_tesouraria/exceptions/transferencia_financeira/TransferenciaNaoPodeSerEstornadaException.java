package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class TransferenciaNaoPodeSerEstornadaException extends BusinessException {

    public TransferenciaNaoPodeSerEstornadaException(String mensagem){

        super(
                "TRANSFERENCIA_NAO_PODE_SER_ESTORNADA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );

    }
}
