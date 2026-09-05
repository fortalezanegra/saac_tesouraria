package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class TransferenciaJaEstornadaException extends BusinessException {

    public TransferenciaJaEstornadaException(Long transferenciaFinanceiraId){

        super(
            "TRANSFERENCIA_JA_ESTORNADA",
            "A transferencia financeira informada já foi estornada: " + transferenciaFinanceiraId,
            HttpStatus.BAD_REQUEST
        );
    }
}
