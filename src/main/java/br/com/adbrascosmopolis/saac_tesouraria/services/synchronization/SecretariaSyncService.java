package br.com.adbrascosmopolis.saac_tesouraria.services.synchronization;

import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.CargoMinisterialDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.CongregacaoDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.PessoaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.TipoVinculoDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.CargoMinisterial;
import br.com.adbrascosmopolis.saac_tesouraria.models.Congregacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.Pessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.TipoVinculo;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CargoMinisterialRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CongregacaoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.TipoVinculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SecretariaSyncService {

    private final SecretariaClientService client;
    private final TipoVinculoRepository tipoVinculoRepository;
    private final CongregacaoRepository congregacaoRepository;
    private final CargoMinisterialRepository cargoMinisterialRepository;
    private final PessoaRepository pessoaRepository;

    public SecretariaSyncService(
            SecretariaClientService client,
            TipoVinculoRepository tipoVinculoRepository,
            CongregacaoRepository congregacaoRepository,
            CargoMinisterialRepository cargoMinisterialRepository,
            PessoaRepository pessoaRepository) {

        this.client = client;
        this.tipoVinculoRepository = tipoVinculoRepository;
        this.congregacaoRepository = congregacaoRepository;
        this.cargoMinisterialRepository = cargoMinisterialRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public void synchronizeAll() {

        synchronizeTiposVinculo(
                Optional.ofNullable(client.getTiposVinculo())
                        .orElse(Collections.emptyList())
        );

        synchronizeCongregacoes(
                Optional.ofNullable(client.getCongregacoes())
                        .orElse(Collections.emptyList())
        );

        synchronizeCargosMinisteriais(
                Optional.ofNullable(client.getCargosMinisteriais())
                        .orElse(Collections.emptyList())
        );

        synchronizePessoas(
                Optional.ofNullable(client.getPessoas())
                        .orElse(Collections.emptyList())
        );
    }

    private void synchronizeTiposVinculo(List<TipoVinculoDTO> lista) {

        if ( lista.isEmpty()) {
            return;
        }

        List<Integer> ids = lista.stream()
                .map(TipoVinculoDTO::getTipoVinculoId)
                .toList();

        Map<Integer, TipoVinculo> mapa = tipoVinculoRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(
                        TipoVinculo::getTipoVinculoId,
                        Function.identity()
                ));

        List<TipoVinculo> salvar = new ArrayList<>();

        for (TipoVinculoDTO dto : lista) {

            TipoVinculo tipo = mapa.computeIfAbsent(
                    dto.getTipoVinculoId(),
                    id -> new TipoVinculo()
            );

            tipo.setTipoVinculoId(dto.getTipoVinculoId());
            tipo.setDescricao(dto.getDescricao());

            salvar.add(tipo);
        }

        if(!salvar.isEmpty()){
            tipoVinculoRepository.saveAll(salvar);
        }
    }

    private void synchronizeCongregacoes(List<CongregacaoDTO> lista) {

        if (lista.isEmpty()) {
            return;
        }

        List<Integer> ids = lista.stream()
                .map(CongregacaoDTO::getCongregacaoId)
                .toList();

        Map<Integer, Congregacao> mapa = congregacaoRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(
                        Congregacao::getCongregacaoId,
                        Function.identity()
                ));

        List<Congregacao> salvar = new ArrayList<>();

        for (CongregacaoDTO dto : lista) {

            Congregacao congregacao = mapa.computeIfAbsent(
                    dto.getCongregacaoId(),
                    id -> new Congregacao()
            );

            congregacao.setCongregacaoId(dto.getCongregacaoId());
            congregacao.setNome(dto.getNome());

            salvar.add(congregacao);
        }

        if(!salvar.isEmpty()){
            congregacaoRepository.saveAll(salvar);
        }
    }

    private void synchronizeCargosMinisteriais(List<CargoMinisterialDTO> lista) {

        if (lista.isEmpty()) {
            return;
        }

        List<Integer> ids = lista.stream()
                .map(CargoMinisterialDTO::getCargoMinisterialId)
                .toList();

        Map<Integer, CargoMinisterial> mapa = cargoMinisterialRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(
                        CargoMinisterial::getCargoMinisterialId,
                        Function.identity()
                ));

        List<CargoMinisterial> salvar = new ArrayList<>();

        for (CargoMinisterialDTO dto : lista) {

            CargoMinisterial cargo = mapa.computeIfAbsent(
                    dto.getCargoMinisterialId(),
                    id -> new CargoMinisterial()
            );

            cargo.setCargoMinisterialId(dto.getCargoMinisterialId());
            cargo.setDescricao(dto.getDescricao());

            salvar.add(cargo);
        }

        if(!salvar.isEmpty()) {
            cargoMinisterialRepository.saveAll(salvar);
        }
    }

    private void synchronizePessoas(List<PessoaDTO> lista) {

        if (lista.isEmpty()) {
            return;
        }

        // Pessoas existentes
        List<Long> pessoaIds = lista.stream()
                .map(PessoaDTO::getPessoaId)
                .toList();

        Map<Long, Pessoa> pessoas = pessoaRepository.findAllById(pessoaIds)
                .stream()
                .collect(Collectors.toMap(
                        Pessoa::getPessoaId,
                        Function.identity()
                ));

        // Tipos de vínculo
        List<Integer> tipoVinculoIds = lista.stream()
                .map(PessoaDTO::getTipoVinculoId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Integer, TipoVinculo> tiposVinculo = tipoVinculoRepository.findAllById(tipoVinculoIds)
                .stream()
                .collect(Collectors.toMap(
                        TipoVinculo::getTipoVinculoId,
                        Function.identity()
                ));

        // Congregações
        List<Integer> congregacaoIds = lista.stream()
                .map(PessoaDTO::getCongregacaoId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Integer, Congregacao> congregacoes = congregacaoRepository.findAllById(congregacaoIds)
                .stream()
                .collect(Collectors.toMap(
                        Congregacao::getCongregacaoId,
                        Function.identity()
                ));

        // Cargos ministeriais
        List<Integer> cargoMinisterialIds = lista.stream()
                .map(PessoaDTO::getCargoMinisterialId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<Integer, CargoMinisterial> cargosMinisteriais = cargoMinisterialRepository.findAllById(cargoMinisterialIds)
                .stream()
                .collect(Collectors.toMap(
                        CargoMinisterial::getCargoMinisterialId,
                        Function.identity()
                ));

        List<Pessoa> salvar = new ArrayList<>();

        for (PessoaDTO dto : lista) {

            Pessoa pessoa = pessoas.computeIfAbsent(
                    dto.getPessoaId(),
                    id -> new Pessoa()
            );

            pessoa.setPessoaId(dto.getPessoaId());
            pessoa.setTipoVinculo( tiposVinculo.get(dto.getTipoVinculoId()) );
            pessoa.setCongregacao( congregacoes.get(dto.getCongregacaoId()) );
            pessoa.setCargoMinisterial( cargosMinisteriais.get(dto.getCargoMinisterialId()) );
            pessoa.setNome(dto.getNome());

            salvar.add(pessoa);
        }

        if (!salvar.isEmpty()) {
            pessoaRepository.saveAll(salvar);
        }
    }
}