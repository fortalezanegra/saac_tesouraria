package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.solicitacao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processos")
@CrossOrigin(origins = "*")
public class SolicitacaoAuditoriaController {

    private final SolicitacaoAuditoriaService solicitacaoAuditoria;

    public SolicitacaoAuditoriaController(SolicitacaoAuditoriaService solicitacaoAuditoria){
        this.solicitacaoAuditoria = solicitacaoAuditoria;
    }

    //Create
    @PostMapping("solicitar-auditoria")
    public ResponseEntity<byte[]> solicitarAuditoria(@RequestBody SolicitacaoAuditoriaRequestDTO dto ){

        byte[] pdf = solicitacaoAuditoria.solicitarAuditoria(dto.getLivroCaixaId());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_fluxo_caixa.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
