package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    //Lista Pessoa filtrando por Congregação
    public List<PessoaDropdownDTO> filtrar(Integer congregacaoId) {
        List<PessoaDropdownDTO> pessoas = pessoaRepository.filtrarPorCongregacao(congregacaoId);
        return pessoas;
    }

}
