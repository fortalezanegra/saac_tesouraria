package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto;

import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.models.RepositorioRelatorioFluxoCaixa;

public class RepositorioRelatorioFluxoCaixaDTO {

    private Long repositorioId;
    private String nomeArquivo;
    private String urlArquivo;

    public RepositorioRelatorioFluxoCaixaDTO(

            Long repositorioId,
            String nomeArquivo,
            String urlArquivo
    ){
        this.repositorioId = repositorioId;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public RepositorioRelatorioFluxoCaixa mapToRepositorioRelatorioFluxoCaixa(){
        RepositorioRelatorioFluxoCaixa repositorio = new RepositorioRelatorioFluxoCaixa();

        repositorio.setNomeArquivo(this.nomeArquivo);
        repositorio.setUrlArquivo(this.urlArquivo);

        return repositorio;
    }

    public static RepositorioRelatorioFluxoCaixaDTO fromRepositorioRelatorioFluxoCaixa(
            RepositorioRelatorioFluxoCaixa r
    ){
        if(r == null){
            return null;
        }

        return new RepositorioRelatorioFluxoCaixaDTO(
                r.getRepositorioId(),
                r.getNomeArquivo(),
                r.getUrlArquivo()
        );
    }

    public Long getRepositorioId() {
        return repositorioId;
    }

    public void setRepositorioId(Long repositorioId) {
        this.repositorioId = repositorioId;
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
}
