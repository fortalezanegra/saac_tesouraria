package br.com.adbrascosmopolis.saac_tesouraria.dto.banco;

import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;

public class BancoEditDTO {

    private Integer bancoId;
    private Integer codigoBanco;
    private String nome;
    private Integer situacaoId;

    public static BancoEditDTO fromBanco(Banco b) {

        BancoEditDTO dto = new BancoEditDTO();

        dto.bancoId = b.getBancoId();
        dto.codigoBanco =  b.getCodigoBanco();
        dto.nome =  b.getNome();
        dto.situacaoId = b.getSituacao() != null ? b.getSituacao().getSituacaoId() : null;

        return dto;
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

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
