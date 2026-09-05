package br.com.adbrascosmopolis.saac_tesouraria.dto.banco;

import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;

import java.time.LocalDate;

public class BancoResponseDTO {

    private Integer bancoId;
    private Integer codigoBanco;
    private String nome;
    private String situacao;

    public BancoResponseDTO(
            Integer bancoId,
            Integer codigoBanco,
            String nome,
            String situacao) {

        this.bancoId = bancoId;
        this.codigoBanco = codigoBanco;
        this.nome = nome;
        this.situacao = situacao;
    }

    public static BancoResponseDTO fromBanco(Banco b) {

        if (b == null) {
            return null;
        }

        return new BancoResponseDTO(
            b.getBancoId(),
            b.getCodigoBanco(),
            b.getNome(),
            b.getSituacao() != null ? b.getSituacao().getDescricao() : null
        );
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public Integer getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}