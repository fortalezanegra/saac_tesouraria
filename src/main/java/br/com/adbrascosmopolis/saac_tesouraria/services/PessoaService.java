package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    //Dropdwon Pessoas
    public List<PessoaDropdownDTO> dropdownPessoas(){
        List<PessoaDropdownDTO> pessoas = pessoaRepository.listarPessoas();
        return pessoas;
    }




}
