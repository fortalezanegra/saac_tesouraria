Como você está usando um monólito por módulo em Spring Boot, a forma mais limpa é manter a sincronização em uma camada separada.

A estrutura pode ficar assim:

```
tesouraria
 ├── controller
 │     PessoaSyncController.java   (opcional)
 ├── dto
 │     PessoaSyncDTO.java
 ├── entity
 │     Pessoa.java                 (já existe)
 ├── repository
 │     PessoaRepository.java
 ├── service
 │     PessoaSyncService.java
 └── client
       SecretariaClient.java
```

---

# 1. DTO

**PessoaSyncDTO.java**

```java
package br.com.tesouraria.dto;

public class PessoaSyncDTO {

    private Long pessoaId;
    private Long tipoVinculoId;
    private Long congregacaoId;
    private Long cargoMinisterialId;
    private String nome;

    public PessoaSyncDTO() {
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getTipoVinculoId() {
        return tipoVinculoId;
    }

    public void setTipoVinculoId(Long tipoVinculoId) {
        this.tipoVinculoId = tipoVinculoId;
    }

    public Long getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Long congregacaoId) {
        this.congregacaoId = congregacaoId;
    }

    public Long getCargoMinisterialId() {
        return cargoMinisterialId;
    }

    public void setCargoMinisterialId(Long cargoMinisterialId) {
        this.cargoMinisterialId = cargoMinisterialId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
```

---

# 2. Repository

Caso ainda não exista.

**PessoaRepository.java**

```java
package br.com.tesouraria.repository;

import br.com.tesouraria.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
```

---

# 3. Cliente REST

Separar o consumo da API é uma boa prática.

**SecretariaClient.java**

```java
package br.com.tesouraria.client;

import br.com.tesouraria.dto.PessoaSyncDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SecretariaClient {

    private final RestTemplate restTemplate;

    public SecretariaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PessoaSyncDTO> buscarPessoas() {

        String url = "http://10.0.0.76:8081/sync/pessoa";

        return restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PessoaSyncDTO>>() {
                }).getBody();
    }

}
```

---

# 4. Configuração do RestTemplate

**RestTemplateConfig.java**

```java
package br.com.tesouraria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```

---

# 5. Serviço de sincronização

**PessoaSyncService.java**

```java
package br.com.tesouraria.service;

import br.com.tesouraria.client.SecretariaClient;
import br.com.tesouraria.dto.PessoaSyncDTO;
import br.com.tesouraria.entity.Pessoa;
import br.com.tesouraria.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaSyncService {

    private final SecretariaClient secretariaClient;
    private final PessoaRepository pessoaRepository;

    public PessoaSyncService(
            SecretariaClient secretariaClient,
            PessoaRepository pessoaRepository) {

        this.secretariaClient = secretariaClient;
        this.pessoaRepository = pessoaRepository;
    }

    public void sincronizar() {

        List<PessoaSyncDTO> pessoas = secretariaClient.buscarPessoas();

        if (pessoas == null || pessoas.isEmpty()) {
            return;
        }

        for (PessoaSyncDTO dto : pessoas) {

            Pessoa pessoa = pessoaRepository
                    .findById(dto.getPessoaId())
                    .orElse(new Pessoa());

            pessoa.setPessoaId(dto.getPessoaId());
            pessoa.setTipoVinculoId(dto.getTipoVinculoId());
            pessoa.setCongregacaoId(dto.getCongregacaoId());
            pessoa.setCargoMinisterialId(dto.getCargoMinisterialId());
            pessoa.setNome(dto.getNome());

            pessoaRepository.save(pessoa);
        }
    }

}
```

---

# 6. Endpoint para disparar a sincronização

**PessoaSyncController.java**

```java
package br.com.tesouraria.controller;

import br.com.tesouraria.service.PessoaSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sync")
public class PessoaSyncController {

    private final PessoaSyncService pessoaSyncService;

    public PessoaSyncController(PessoaSyncService pessoaSyncService) {
        this.pessoaSyncService = pessoaSyncService;
    }

    @PostMapping("/pessoa")
    public ResponseEntity<String> sincronizar() {

        pessoaSyncService.sincronizar();

        return ResponseEntity.ok("Sincronização concluída.");
    }

}
```

---

# Funcionamento

Quando você fizer:

```
POST
http://localhost:8082/sync/pessoa
```

a Tesouraria executará:

```
GET
http://10.0.0.76:8081/sync/pessoa
```

receberá algo como:

```json
[
  {
    "pessoaId":1,
    "tipoVinculoId":2,
    "congregacaoId":3,
    "cargoMinisterialId":4,
    "nome":"João"
  },
  {
    "pessoaId":2,
    "tipoVinculoId":1,
    "congregacaoId":5,
    "cargoMinisterialId":null,
    "nome":"Maria"
  }
]
```

e fará um **upsert**:

* se a pessoa já existir (mesmo `pessoaId`), atualiza;
* se não existir, insere um novo registro.

### Observação importante

No seu texto há um pequeno detalhe: você escreveu o campo como **`cargoMinisteriald`**, mas imagino que o correto seja **`cargoMinisterialId`**. O nome do atributo no `PessoaSyncDTO` deve corresponder exatamente ao nome retornado pelo JSON da API da Secretaria para que o mapeamento automático do Spring funcione. Se o endpoint realmente retornar `cargoMinisteriald`, ajuste o atributo (ou use `@JsonProperty("cargoMinisteriald")`).
