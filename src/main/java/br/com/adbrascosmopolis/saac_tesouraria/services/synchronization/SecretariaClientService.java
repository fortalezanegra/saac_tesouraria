package br.com.adbrascosmopolis.saac_tesouraria.services.synchronization;

import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.CargoMinisterialDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.CongregacaoDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.PessoaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization.TipoVinculoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class SecretariaClientService {

    private final RestClient _restClient;

    public SecretariaClientService(RestClient restClient) {
        this._restClient = restClient;
    }

    public List<TipoVinculoDTO> getTiposVinculo(){
        return _restClient.get()
                .uri("/tipo-vinculo")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TipoVinculoDTO>>(){});
    }

    public List<CongregacaoDTO> getCongregacoes(){
        return _restClient.get()
                .uri("/congregacao")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CongregacaoDTO>>(){});
    }

    public List<CargoMinisterialDTO> getCargosMinisteriais(){
        return _restClient.get()
                .uri("/cargo-ministerial")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CargoMinisterialDTO>>(){});
    }

    public List<PessoaDTO> getPessoas() {
        return _restClient.get()
                .uri("/pessoa")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PessoaDTO>>(){});
    }
}
