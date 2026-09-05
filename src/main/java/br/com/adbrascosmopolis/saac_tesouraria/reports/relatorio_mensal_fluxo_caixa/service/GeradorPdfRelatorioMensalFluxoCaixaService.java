package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;

@Service
public class GeradorPdfRelatorioMensalFluxoCaixaService {

    private final TemplateEngine templateEngine;

    public GeradorPdfRelatorioMensalFluxoCaixaService() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(resolver);
    }

    public byte[] gerarPdf(Object relatorioDTO) {
        try {
            Context context = new Context(LocaleContextHolder.getLocale());
            context.setVariable("relatorio", relatorioDTO);

            String htmlRenderizado = templateEngine.process("relatorio-mensal-fluxo-caixa", context);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlRenderizado, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o PDF do relatório mensal de fluxo de caixa: " + e.getMessage(), e);
        }
    }
}
