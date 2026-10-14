package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.inicializar;

import br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.preparar.PrepararAuditoriaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.preparar.PrepararAuditoriaService;
import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos/auditoria")
@CrossOrigin(origins = "*")
public class InicializarAuditoriaController {

    private final InicializarAuditoriaService service;

    public InicializarAuditoriaController(InicializarAuditoriaService service) {
        this.service = service;
    }

    /**
     * Inicia individualmente uma auditoria PENDENTE:
     * define o auditor, dataInicio e muda a situação para EM_ANALISE.
     * Também atualiza o Livro Caixa para EM_AUDITORIA.
     */
    @PostMapping("/iniciar")
    public ResponseEntity<AuditoriaResponseDTO> iniciarAuditoria(
            @RequestBody PrepararAuditoriaRequestDTO dto) {
        AuditoriaResponseDTO atualizado = service.iniciarAuditoria(dto);
        return ResponseEntity.ok(atualizado);
    }
}
