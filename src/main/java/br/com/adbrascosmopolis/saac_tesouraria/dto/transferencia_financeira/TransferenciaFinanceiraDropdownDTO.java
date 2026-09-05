package br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaFinanceiraDropdownDTO(
        Long contaOrigemId,

        Long contaDestinoId,

        BigDecimal valor,

        LocalDateTime dataTransferencia,

        String descricao,

        Integer categoriaSaidaId,

        Integer categoriaEntradaId,

        String observacao

) {
}
