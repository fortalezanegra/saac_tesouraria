package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaInvalidoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CategoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.ContaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.MovimentoFinanceiroRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.TransferenciaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.security.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MovimentoFinanceiroService(
            MovimentoFinanceiroRepository movimentoFinanceiroRepository,
            LivroCaixaRepository livroCaixaRepository,
            ContaFinanceiraRepository contaFinanceiraRepository,
            TransferenciaFinanceiraRepository transferenciaFinanceiraRepository,
            CategoriaRepository categoriaRepository,
            PessoaRepository pessoaRepository
    ) {
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.transferenciaFinanceiraRepository = transferenciaFinanceiraRepository;
        this.categoriaRepository = categoriaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<MovimentoFinanceiroResponseDTO> getAll() {
        boolean global = SecurityUtils.hasAuthority("PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL");
        Long congregacaoId = global ? null : SecurityUtils.currentUnityId();

        return movimentoFinanceiroRepository.listarMovimentosFiltrado(congregacaoId)
                .stream()
                .map(MovimentoFinanceiroResponseDTO::fromMovimentoFinanceiro)
                .toList();
    }

    @Transactional
    public MovimentoFinanceiroResponseDTO create(MovimentoFinanceiroRequestDTO dto) {
        if (dto.getLivroCaixaId() == null) {
            throw new LivroCaixaInvalidoException("O livroCaixaId não pode ser null.");
        }

        LivroCaixa livro = livroCaixaRepository.findById(dto.getLivroCaixaId())
                .orElseThrow(() -> new LivroCaixaNaoEncontradoException(
                        "O livro caixa informado não foi encontrado."
                ));

        validarAcessoCongregacao(livro, "PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_CREATE_GLOBAL");

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

    @Transactional
    public MovimentoFinanceiroResponseDTO update(MovimentoFinanceiroEditDTO dto) {
        if (dto.getMovimentoFinanceiroId() == null) {
            throw new IllegalArgumentException("O atributo movimentoFinanceiroId não pode ser null!");
        }

        MovimentoFinanceiro movimento = movimentoFinanceiroRepository.findById(dto.getMovimentoFinanceiroId())
                .orElseThrow(() -> new EntityNotFoundException("Movimento Financeiro não encontrado"));

        validarAcessoCongregacao(movimento.getLivroCaixa(),
                "PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_UPDATE_GLOBAL");

        movimento.setValor(dto.getValor());
        movimento.setEletronico(dto.getEletronico());
        movimento.setDataMovimento(dto.getDataMovimento());
        movimento.setHistorico(dto.getHistorico());

        if (dto.getLivroCaixaId() != null) {
            LivroCaixa novoLivro = livroCaixaRepository.findById(dto.getLivroCaixaId())
                    .orElseThrow(() -> new EntityNotFoundException("Livro Caixa não encontrado"));
            validarAcessoCongregacao(novoLivro,
                    "PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_UPDATE_GLOBAL");
            movimento.setLivroCaixa(novoLivro);
        }

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

    @Transactional
    public MovimentoFinanceiroResponseDTO delete(MovimentoFinanceiroEditDTO dto) {
        if (dto.getMovimentoFinanceiroId() == null) {
            throw new IllegalArgumentException("O atributo movimentoFinanceiroId não pode ser null!");
        }

        MovimentoFinanceiro movimento = movimentoFinanceiroRepository.findById(dto.getMovimentoFinanceiroId())
                .orElseThrow(() -> new RuntimeException("Movimento Financeiro não encontrado!"));

        validarAcessoCongregacao(movimento.getLivroCaixa(),
                "PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_DELETE_GLOBAL");

        movimento.setDeletedAt(LocalDateTime.now());

        return MovimentoFinanceiroResponseDTO.fromMovimentoFinanceiro(
                movimentoFinanceiroRepository.save(movimento)
        );
    }

    private void validarAcessoCongregacao(LivroCaixa livro, String globalPermission) {
        if (SecurityUtils.hasAuthority(globalPermission)) {
            return;
        }

        Long usuarioCongregacaoId = SecurityUtils.currentUnityId();
        Integer livroCongregacaoId = livro.getCentroCusto().getCongregacao().getCongregacaoId();

        if (usuarioCongregacaoId == null || !usuarioCongregacaoId.equals(livroCongregacaoId.longValue())) {
            throw new AccessDeniedException("Acesso negado ao centro de custo/congregação informado.");
        }
    }
}
