package br.com.adbrascosmopolis.saac_tesouraria.repository.projection;

import java.math.BigDecimal;

public interface ContaFinanceiraSaldoProjection {

    BigDecimal getTotalEntradas();

    BigDecimal getTotalSaidas();

}
