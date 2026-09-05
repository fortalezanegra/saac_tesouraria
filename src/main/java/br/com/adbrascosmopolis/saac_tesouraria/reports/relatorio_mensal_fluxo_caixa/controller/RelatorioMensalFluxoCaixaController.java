package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.controller;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.ResumoRelatorioMensalFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service.RelatorioMensalFluxoCaixaService;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/relatorio-mensal-fluxo-caixa")
public class RelatorioMensalFluxoCaixaController {

    private final RelatorioMensalFluxoCaixaService relatorioMensalService;
    private final LivroCaixaRepository livroCaixaRepository;

    public RelatorioMensalFluxoCaixaController(
            RelatorioMensalFluxoCaixaService relatorioMensalService,
            LivroCaixaRepository livroCaixaRepository
    ) {
        this.relatorioMensalService = relatorioMensalService;
        this.livroCaixaRepository = livroCaixaRepository;
    }

    @GetMapping("/{livroCaixaId}/resumo")
    public ResponseEntity<ResumoRelatorioMensalFluxoCaixaDTO> obterResumo(
            @PathVariable Long livroCaixaId
    ) {
        ResumoRelatorioMensalFluxoCaixaDTO resumo = relatorioMensalService.calcularResumo(livroCaixaId);
        return ResponseEntity.ok(resumo);
    }

    @GetMapping("/{livroCaixaId}/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioPdf(
            @PathVariable Long livroCaixaId
    ) {
        // findByLivroCaixaId não existe por padrão no JpaRepository -> usar findById
        LivroCaixa livro = livroCaixaRepository.findById(livroCaixaId)
                .orElseThrow(() -> new LivroCaixaNaoEncontradoException(
                        "Livro Caixa não encontrado: " + livroCaixaId
                ));

        byte[] pdf = relatorioMensalService.gerarRelatorioFechamentoPdf(livroCaixaId);

        String competenciaFormatada = livro.getCompetencia()
                .format(DateTimeFormatter.ofPattern("MM-yyyy"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(
                "inline",
                "relatorio-mensal-fluxo-caixa-" + livro.getCentroCusto().getDescricao() + "-" + competenciaFormatada + ".pdf"
        );
        headers.setContentLength(pdf.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
}
