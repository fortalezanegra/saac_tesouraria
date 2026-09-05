package br.com.adbrascosmopolis.saac_tesouraria.dto.extrato_bancario;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import br.com.adbrascosmopolis.saac_tesouraria.models.ExtratoBancario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExtratoBancarioResponseDTO {


    private Long extratoBancarioId;
    private String contaFinanceira;
    private LocalDate dataMovimento;
    private String descricao;
    private BigDecimal valor;
    private TipoMovimento tipoMovimento;
    private String numeroDocumento;
    private BigDecimal saldoConta;
    private String hashMovimento;
    private String importacaoExtrato;

    public ExtratoBancarioResponseDTO(
            Long extratoBancarioId,
            String contaFinanceira,
            LocalDate dataMovimento,
            String descricao,
            BigDecimal valor,
            TipoMovimento tipoMovimento,
            String numeroDocumento,
            BigDecimal saldoConta,
            String hashMovimento,
            String importacaoExtrato
    ){
        this.extratoBancarioId = extratoBancarioId;
        this.contaFinanceira = contaFinanceira;
        this.dataMovimento = dataMovimento;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoMovimento = tipoMovimento;
        this.numeroDocumento = numeroDocumento;
        this.saldoConta = saldoConta;
        this.hashMovimento = hashMovimento;
        this.importacaoExtrato = importacaoExtrato;
    }

    public static ExtratoBancarioResponseDTO fromExtratoBancario(ExtratoBancario e){

        if(e == null){
            return null;
        }

        return new ExtratoBancarioResponseDTO(
                e.getExtratoBancarioId(),
                e.getContaFinanceira() != null ? e.getContaFinanceira().getDescricao() : null,
                e.getDataMovimento(),
                e.getDescricao(),
                e.getValor(),
                e.getTipoMovimento(),
                e.getNumeroDocumento(),
                e.getSaldoConta(),
                e.getHashMovimento(),
                e.getImportacaoExtrato() != null ? e.getImportacaoExtrato().getNomeArquivo() : null
        );
    }

    public Long getExtratoBancarioId() {
        return extratoBancarioId;
    }

    public void setExtratoBancarioId(Long extratoBancarioId) {
        this.extratoBancarioId = extratoBancarioId;
    }

    public String getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(String contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getHashMovimento() {
        return hashMovimento;
    }

    public void setHashMovimento(String hashMovimento) {
        this.hashMovimento = hashMovimento;
    }

    public String getImportacaoExtrato() {
        return importacaoExtrato;
    }

    public void setImportacaoExtrato(String importacaoExtrato) {
        this.importacaoExtrato = importacaoExtrato;
    }
}
