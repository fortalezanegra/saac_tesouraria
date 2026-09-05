package br.com.adbrascosmopolis.saac_tesouraria.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Id
    @Column(name = "pessoaId")
    private Long pessoaId;

    @ManyToOne(fetch = FetchType.LAZY) // ou EAGER se quiser carregar junto
    @JoinColumn(name = "tipoVinculoId", referencedColumnName = "tipoVinculoId")
    private TipoVinculo tipoVinculo;

    @ManyToOne(fetch = FetchType.LAZY) // ou EAGER se quiser carregar junto
    @JoinColumn(name = "congregacaoId", referencedColumnName = "congregacaoId")
    private Congregacao congregacao;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY) // ou EAGER se quiser carregar junto
    @JoinColumn(name = "cargoMinisterialId", referencedColumnName = "cargoMinisterialId")
    private CargoMinisterial cargoMinisterial;

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public TipoVinculo getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(TipoVinculo tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    public Congregacao getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(Congregacao congregacao) {
        this.congregacao = congregacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CargoMinisterial getCargoMinisterial() {
        return cargoMinisterial;
    }

    public void setCargoMinisterial(CargoMinisterial cargoMinisterial) {
        this.cargoMinisterial = cargoMinisterial;
    }

}
