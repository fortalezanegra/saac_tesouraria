package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.TransferenciaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaFinanceiraRepository extends JpaRepository<TransferenciaFinanceira, Long> {

    //Listar Todos
    @Query("""
    SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraResponseDTO(
        t.transferenciaFinanceiraId,
        t.descricao,
        t.contaOrigem.descricao,
        t.contaDestino.descricao,
        t.valor,
        t.dataTransferencia,
        t.codigoTransacao,
        t.situacao.descricao
    )
    FROM TransferenciaFinanceira t
    WHERE t.deletedAt IS NULL
    ORDER BY t.dataTransferencia DESC
""")
    List<TransferenciaFinanceiraResponseDTO> listarTodos();

}
