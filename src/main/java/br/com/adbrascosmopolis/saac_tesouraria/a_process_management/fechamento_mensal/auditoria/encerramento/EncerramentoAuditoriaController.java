package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.encerramento;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/processos/auditoria/encerramento")
@CrossOrigin(origins = "*")
public class EncerramentoAuditoriaController {

    private final EncerramentoAuditoriaService service;

    public EncerramentoAuditoriaController(EncerramentoAuditoriaService service) {
        this.service = service;
    }

    @PatchMapping
    public ResponseEntity<AuditoriaResponseDTO> encerrar(@RequestBody EncerramentoAuditoriaRequestDTO dto) {
        AuditoriaResponseDTO response = service.encerrar(dto);
        return ResponseEntity.ok(response);
    }
}
