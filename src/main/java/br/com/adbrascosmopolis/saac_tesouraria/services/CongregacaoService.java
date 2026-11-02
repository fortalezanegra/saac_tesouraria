package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.congregacao.CongregacaoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Congregacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CongregacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CongregacaoService {

    private final CongregacaoRepository congregacaoRepository;

    public CongregacaoService(CongregacaoRepository congregacaoRepository){
        this.congregacaoRepository = congregacaoRepository;
    }

    // Dropdown/ComboBox
    public List<CongregacaoDropdownDTO> dropdown() {
        List<Congregacao> congregacoes = congregacaoRepository.findAll();

        return congregacoes.stream()
                .map(p -> new CongregacaoDropdownDTO(
                        p.getCongregacaoId(),
                        p.getNome()
                ))
                .collect(Collectors.toList());
    }
}
