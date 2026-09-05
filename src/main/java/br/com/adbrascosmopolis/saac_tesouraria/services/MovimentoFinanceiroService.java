package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaInvalidoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import br.com.adbrascosmopolis.saac_tesouraria.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoFinanceiroService {

    private final MovimentoFinanceiroRepository movimentoFinanceiroRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final TransferenciaFinanceiraRepository transferenciaFinanceiraRepository;
    private final CategoriaRepository categoriaRepository;
    private final PessoaRepository pessoaRepository;
    private final SituacaoRepository situacaoRepository;

    public MovimentoFinanceiroService(
            MovimentoFinanceiroRepository movimentoFinanceiroRepository,
            LivroCaixaRepository livroCaixaRepository,
            ContaFinanceiraRepository contaFinanceiraRepository,
            TransferenciaFinanceiraRepository transferenciaFinanceiraRepository,
            CategoriaRepository categoriaRepository,
            PessoaRepository pessoaRepository,
            SituacaoRepository situacaoRepository
    ){
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.transferenciaFinanceiraRepository = transferenciaFinanceiraRepository;
        this.categoriaRepository = categoriaRepository;
        this.pessoaRepository = pessoaRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Listar todos os Movimentos
    @Transactional
    public List<MovimentoFinanceiroResponseDTO> getAll(){
        return movimentoFinanceiroRepository.listarMovimentos()
                .stream()
                .map(MovimentoFinanceiroResponseDTO::fromMovimentoFinanceiro)
                .toList();
    }

    //CreateTransference
    @Transactional
    public MovimentoFinanceiroResponseDTO createTransference(MovimentoFinanceiro transferencia){

        MovimentoFinanceiro movimento = new MovimentoFinanceiro();

        movimento.setValor(transferencia.getValor());
        movimento.setDataMovimento(LocalDate.now());
        movimento.setHistorico(transferencia.getHistorico());

        //Relacionamentos
        if(transferencia.getLivroCaixa().getLivroCaixaId() != null){
            movimento.setLivroCaixa(
                    livroCaixaRepository.findById(transferencia.getLivroCaixa().getLivroCaixaId())
                            .orElseThrow( () -> new EntityNotFoundException("Livro Caixa não encontrado!"))
            );
        }

        if(transferencia.getContaFinanceira().getContaFinanceiraId() != null){
            movimento.setContaFinanceira(
                    contaFinanceiraRepository.findById(transferencia.getContaFinanceira().getContaFinanceiraId())
                            .orElseThrow( () -> new EntityNotFoundException("Conta Financeira não encontrada!") )
            );
        }

        if(transferencia.getCategoria().getCategoriaId() != null){
            movimento.setCategoria(
                    categoriaRepository.findById(transferencia.getCategoria().getCategoriaId())
                            .orElseThrow( () -> new EntityNotFoundException("Categoria não encontrada!") )
            );
        }

        return MovimentoFinanceiroResponseDTO.fromMovimentoFinanceiro(movimentoFinanceiroRepository.save(movimento));
    }

    //Create
    @Transactional
    public MovimentoFinanceiroResponseDTO create(MovimentoFinanceiroRequestDTO dto) {

        if (dto.getLivroCaixaId() == null) {
            throw new LivroCaixaInvalidoException("O livroCaixaId não pode ser null.");
        }

        // Única consulta reutilizada em todo o método
        var livro = livroCaixaRepository.findById(dto.getLivroCaixaId())
                .orElseThrow(() -> new LivroCaixaNaoEncontradoException(
                        "O livro caixa informado não foi encontrado."
                ));

        //Valida se o Livro Caixa de destino possui situacao ABERTO ou REABERTO
        if (!livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.ABERTO)
                && !livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.REABERTO)) {
            throw new LivroCaixaInvalidoException(
                    "Livro Caixa precisa estar ABERTO ou REABERTO para receber novos lançamentos."
            );
        }

        MovimentoFinanceiro movimento = new MovimentoFinanceiro();
        movimento.setValor(dto.getValor());
        movimento.setEletronico(dto.getEletronico());
        movimento.setDataMovimento(dto.getDataMovimento());
        movimento.setHistorico(dto.getHistorico());

        // Reaproveita o livro já carregado — sem nova consulta
        movimento.setLivroCaixa(livro);

        if (dto.getContaFinanceiraId() != null) {
            movimento.setContaFinanceira(
                    contaFinanceiraRepository.findById(dto.getContaFinanceiraId())
                            .orElseThrow(() -> new EntityNotFoundException("Conta Financeira não encontrada!"))
            );
        }

        if (dto.getCategoriaId() != null) {
            movimento.setCategoria(
                    categoriaRepository.findById(dto.getCategoriaId())
                            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"))
            );
        }

        if (dto.getPessoaId() != null) {
            movimento.setPessoa(
                    pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada!"))
            );
        }

        return MovimentoFinanceiroResponseDTO.fromMovimentoFinanceiro(
                movimentoFinanceiroRepository.save(movimento)
        );
    }

    //Update
    @Transactional
    public MovimentoFinanceiroResponseDTO update(MovimentoFinanceiroEditDTO dto){

        if(dto.getMovimentoFinanceiroId() == null){
            throw new RuntimeException("O atributo movimentoFinanceiroId não pode ser null!");
        }

        MovimentoFinanceiro result = movimentoFinanceiroRepository.findById(dto.getMovimentoFinanceiroId())
                .orElseThrow( () -> new EntityNotFoundException("Movimento Financeiro não encontrado") );

        //Atribuindo novos valores
        result.setValor(dto.getValor());
        result.setEletronico(dto.getEletronico());
        result.setDataMovimento(dto.getDataMovimento());
        result.setHistorico(dto.getHistorico());

        //Relacionamentos
        if(dto.getLivroCaixaId() != null){
            result.setLivroCaixa(
                    livroCaixaRepository.getReferenceById(dto.getLivroCaixaId())
            );
        }

        if(dto.getContaFinanceiraId() != null){
            result.setContaFinanceira(
                    contaFinanceiraRepository.getReferenceById(dto.getContaFinanceiraId())
            );
        }

        if(dto.getCategoriaId() != null){
            result.setCategoria(
                    categoriaRepository.getReferenceById(dto.getCategoriaId())
            );
        }

        if(dto.getPessoaId() != null){
            result.setPessoa(
                    pessoaRepository.getReferenceById(dto.getPessoaId())
            );
        }

        return MovimentoFinanceiroResponseDTO.fromMovimentoFinanceiro(movimentoFinanceiroRepository.save(result));
    }

    //Delete
    @Transactional
    public MovimentoFinanceiroResponseDTO delete(MovimentoFinanceiroEditDTO dto){

        if(dto.getMovimentoFinanceiroId() == null){
            throw new RuntimeException("O atributo movimentoFinanceiroId não pode ser null!");
        }

        MovimentoFinanceiro result = movimentoFinanceiroRepository.findById(dto.getMovimentoFinanceiroId())
                .orElseThrow( () -> new RuntimeException("Movimento Financeiro não encontrado!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDateTime.now());

        return MovimentoFinanceiroResponseDTO.fromMovimentoFinanceiro(movimentoFinanceiroRepository.save(result));
    }

}
