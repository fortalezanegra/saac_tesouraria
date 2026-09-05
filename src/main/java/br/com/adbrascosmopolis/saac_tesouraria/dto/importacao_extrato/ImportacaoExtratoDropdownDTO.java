package br.com.adbrascosmopolis.saac_tesouraria.dto.importacao_extrato;

public class ImportacaoExtratoDropdownDTO {

    private Long importacaoExtratoId;
    private String nomeArquivo;

    public ImportacaoExtratoDropdownDTO(Long importacaoExtratoId, String nomeArquivo){
        this.importacaoExtratoId = importacaoExtratoId;
        this.nomeArquivo = nomeArquivo;
    }

    public Long getImportacaoExtratoId() {
        return importacaoExtratoId;
    }

    public void setImportacaoExtratoId(Long importacaoExtratoId) {
        this.importacaoExtratoId = importacaoExtratoId;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
