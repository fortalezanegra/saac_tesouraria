package br.com.adbrascosmopolis.saac_tesouraria.dto.importacao_extrato;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoArquivo;
import br.com.adbrascosmopolis.saac_tesouraria.models.ImportacaoExtrato;

import java.time.LocalDate;

public class ImportacaoExtratoEditDTO {

    private Long importacaoExtratoId;
    private Long contaFinanceiraId;
    private String nomeArquivo;
    private String urlArquivo;
    private TipoArquivo tipoArquivo;
    private String hashArquivo;
    private LocalDate dataImportacao;
    private Integer quantidadeRegistros;

    public static ImportacaoExtratoEditDTO fromImportacaoExtrato(ImportacaoExtrato i){

        ImportacaoExtratoEditDTO dto = new ImportacaoExtratoEditDTO();

        dto.importacaoExtratoId = i.getImportacaoExtratoId();
        dto.contaFinanceiraId = i.getContaFinanceira() != null ? i.getContaFinanceira().getContaFinanceiraId() : null;
        dto.nomeArquivo = i.getNomeArquivo();
        dto.urlArquivo = i.getUrlArquivo();
        dto.tipoArquivo = i.getTipoArquivo();
        dto.hashArquivo = i.getHashArquivo();
        dto.dataImportacao = i.getDataImportacao();
        dto.quantidadeRegistros = i.getQuantidadeRegistros();

        return dto;
    }

    public Long getImportacaoExtratoId() {
        return importacaoExtratoId;
    }

    public void setImportacaoExtratoId(Long importacaoExtratoId) {
        this.importacaoExtratoId = importacaoExtratoId;
    }

    public Long getContaFinanceiraId() {
        return contaFinanceiraId;
    }

    public void setContaFinanceiraId(Long contaFinanceiraId) {
        this.contaFinanceiraId = contaFinanceiraId;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getHashArquivo() {
        return hashArquivo;
    }

    public void setHashArquivo(String hashArquivo) {
        this.hashArquivo = hashArquivo;
    }

    public LocalDate getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDate dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public Integer getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(Integer quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }
}
