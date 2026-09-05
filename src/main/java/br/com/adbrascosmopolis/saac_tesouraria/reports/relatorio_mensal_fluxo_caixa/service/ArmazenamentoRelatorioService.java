package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ArmazenamentoRelatorioService {

    @Value("${app.relatorios.diretorio}")
    private String diretorioBase;

    /**
     * Salva o PDF em disco e retorna o caminho absoluto do arquivo gerado.
     */
    public String salvarPdf(byte[] pdf, String centroCusto, String competencia, Long livroCaixaId) {
        try {
            Path diretorio = Paths.get(diretorioBase);
            Files.createDirectories(diretorio); // garante que a pasta existe

            String nomeArquivo = montarNomeArquivo(centroCusto, competencia, livroCaixaId);
            Path caminhoCompleto = diretorio.resolve(nomeArquivo);

            Files.write(caminhoCompleto, pdf);

            return caminhoCompleto.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o relatório em disco: " + e.getMessage(), e);
        }
    }

    private String montarNomeArquivo(String centroCusto, String competencia, Long livroCaixaId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String centroCustoLimpo = normalizar(centroCusto);
        String competenciaLimpa = competencia.replace("/", "-");

        return String.format(
                "relatorio_%s_%s_livro%d_%s.pdf",
                centroCustoLimpo,
                competenciaLimpa,
                livroCaixaId,
                timestamp
        );
    }

    // Remove acentos e espaços para nome de arquivo seguro
    private String normalizar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return semAcento.replaceAll("[^a-zA-Z0-9]", "_");
    }
}
