package br.com.adbrascosmopolis.saac_tesouraria.dto.importacao_extrato;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoArquivo;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.models.ImportacaoExtrato;

import java.time.LocalDate;

public class ImportacaoExtratoRequestDTO {

    private Long importacaoExtratoId;
    private Long contaFinanceiraId;
    private String nomeArquivo;
    private String urlArquivo;
    private TipoArquivo tipoArquivo;
    private String hashArquivo;
    private LocalDate dataImportacao;
    private Integer quantidadeRegistros;

    public ImportacaoExtratoRequestDTO(
            Long importacaoExtratoId,
            Long contaFinanceiraId,
            String nomeArquivo,
            String urlArquivo,
            TipoArquivo tipoArquivo,
            String hashArquivo,
            LocalDate dataImportacao,
            Integer quantidadeRegistros
    ){
        this.importacaoExtratoId = importacaoExtratoId;
        this.contaFinanceiraId = contaFinanceiraId;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.tipoArquivo = tipoArquivo;
        this.hashArquivo = hashArquivo;
        this.dataImportacao = dataImportacao;
        this.quantidadeRegistros = quantidadeRegistros;
    }

    public ImportacaoExtrato mapToImportacaoExtrato(){
        ImportacaoExtrato imp = new ImportacaoExtrato();

        imp.setNomeArquivo(this.nomeArquivo);
        imp.setUrlArquivo(this.urlArquivo);
        imp.setTipoArquivo(this.tipoArquivo);
        imp.setHashArquivo(this.hashArquivo);
        imp.setDataImportacao(this.dataImportacao);
        imp.setQuantidadeRegistros(this.quantidadeRegistros);

        //Relacionamentos
        if(this.contaFinanceiraId != null){
            ContaFinanceira conta = new ContaFinanceira();
            conta.setContaFinanceiraId(this.contaFinanceiraId);
            imp.setContaFinanceira(conta);
        }

        return imp;
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
