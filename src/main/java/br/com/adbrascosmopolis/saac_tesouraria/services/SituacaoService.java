package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.situacao.SituacaoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class SituacaoService {

    private final SituacaoRepository repository;

    public SituacaoService(SituacaoRepository repository){
        this.repository = repository;
    }

    public Situacao buscarSituacao(String descricao, String contexto) {

        return repository
                .findByDescricaoAndContexto(descricao, contexto)
                .orElseThrow(() ->
                        new SituacaoNaoEncontradoException(
                                "Situação informada não encontrada."
                        )
                );
    }
}
