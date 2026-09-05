package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.inicializacao;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos/auditoria")
@CrossOrigin(origins = "*")
public class InicializacaoAuditoriaController {

    private final InicializacaoAuditoriaService service;

    public InicializacaoAuditoriaController(InicializacaoAuditoriaService service) {
        this.service = service;
    }

    /**
     * Dispara o processo em lote: busca todos os Livros Caixa com situação
     * "Aguardando Auditoria" e cria uma Auditoria PENDENTE para cada um.
     */
    @PostMapping("/buscar-livros-para-auditar")
    public ResponseEntity<List<AuditoriaResponseDTO>> inicializarAuditorias() {
        List<AuditoriaResponseDTO> criadas = service.inicializarAuditorias();
        return ResponseEntity.ok(criadas);
    }

    /**
     * Inicia individualmente uma auditoria PENDENTE:
     * define o auditor, dataInicio e muda a situação para EM_ANALISE.
     * Também atualiza o Livro Caixa para EM_AUDITORIA.
     */
    @PostMapping("/iniciar")
    public ResponseEntity<AuditoriaResponseDTO> iniciarAuditoria(
            @RequestBody IniciarAuditoriaRequestDTO dto) {
        AuditoriaResponseDTO atualizado = service.iniciarAuditoria(dto);
        return ResponseEntity.ok(atualizado);
    }
}
