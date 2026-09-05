package br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.situacao.SituacaoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira.BancoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira.CentroCustoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.repository.BancoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CentroCustoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.ContaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaFinanceiraService {

    @Autowired
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final CentroCustoRepository centroCustoRepository;
    private final BancoRepository bancoRepository;
    private final SituacaoRepository situacaoRepository;

    public ContaFinanceiraService(
            ContaFinanceiraRepository contaFinanceiraRepository,
            CentroCustoRepository centroCustoRepository,
            BancoRepository bancoRepository,
            SituacaoRepository situacaoRepository
    ){
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.centroCustoRepository = centroCustoRepository;
        this.bancoRepository = bancoRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Dropdown Contas Financeiras
    @Transactional
    public List<ContaFinanceiraDropdownDTO> dropdownContas(){
        List<ContaFinanceiraDropdownDTO> contas = contaFinanceiraRepository.listarContasDropdown();
        return contas;
    }

    //Create
    @Transactional
    public ContaFinanceiraResponseDTO create(ContaFinanceiraRequestDTO dto){
        ContaFinanceira conta = new ContaFinanceira();

        conta.setDescricao(dto.getDescricao());
        conta.setTipoConta(dto.getTipoConta());
        conta.setAgencia(dto.getAgencia());
        conta.setConta(dto.getConta());
        conta.setDigito(dto.getDigito());
        conta.setSaldoInicial(dto.getSaldoInicial());
        conta.setDataAbertura(dto.getDataAbertura());

        //Relacionamentos
        if(dto.getCentroCustoId() != null){
            conta.setCentroCusto(
                    centroCustoRepository.findById(dto.getCentroCustoId())
                            .orElseThrow( () -> new CentroCustoNaoEncontradoException("Centro de Custo não encontrado!") )
            );
        }

        if(dto.getBancoId() != null){
            conta.setBanco(
                    bancoRepository.findById(dto.getBancoId())
                            .orElseThrow( () -> new BancoNaoEncontradoException("Banco não encontrado!") )
            );
        }

        if(dto.getSituacaoId() != null){
            conta.setSituacao(
                    situacaoRepository.findById(dto.getSituacaoId())
                            .orElseThrow( () -> new SituacaoNaoEncontradoException("Situação não encontrada!") )
            );
        }

        return ContaFinanceiraResponseDTO.fromContaFinanceira(contaFinanceiraRepository.save(conta));
    }

    //Update
    @Transactional
    public ContaFinanceiraResponseDTO update(ContaFinanceiraEditDTO dto){
        if(dto.getContaFinanceiraId() == null){
            throw new RuntimeException("O atributo contaFinanceiraId não pode ser null!");
        }

        ContaFinanceira result = contaFinanceiraRepository.findById(dto.getContaFinanceiraId())
                .orElseThrow( () -> new RuntimeException("Conta não encontrada!") );

        //Atribuindo Valores
        result.setDescricao(dto.getDescricao());
        result.setTipoConta(dto.getTipoConta());
        result.setAgencia(dto.getAgencia());
        result.setConta(dto.getConta());
        result.setDigito(dto.getDigito());
        result.setSaldoInicial(dto.getSaldoInicial());
        result.setDataAbertura(dto.getDataAbertura());

        //Relacionamentos
        if(dto.getCentroCustoId() != null){
            result.setCentroCusto(
                    centroCustoRepository.getReferenceById(dto.getCentroCustoId())
            );
        }

        if(dto.getBancoId() != null){
            result.setBanco(
                    bancoRepository.getReferenceById(dto.getBancoId())
            );
        }

        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var contaAtualizada = contaFinanceiraRepository.save(result);

        return ContaFinanceiraResponseDTO.fromContaFinanceira(contaAtualizada);
    }

    //Delete
    @Transactional
    public ContaFinanceiraResponseDTO delete(ContaFinanceiraEditDTO dto){
        if(dto.getContaFinanceiraId() == null){
            throw new RuntimeException("O atributo contaFinanceiraId não pode ser null!");
        }

        ContaFinanceira result = contaFinanceiraRepository.findById(dto.getContaFinanceiraId())
                .orElseThrow( () -> new RuntimeException("Conta não encontrada!") );

        //Aplicando SoftDelete
        result.setDeletedAt(LocalDateTime.now());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var contaAtualizada = contaFinanceiraRepository.save(result);
        return ContaFinanceiraResponseDTO.fromContaFinanceira(contaAtualizada);
    }
}
