package br.com.adbrascosmopolis.saac_tesouraria.dto.banco;

public class BancoDropdownDTO {

    private Integer bancoId;
    private Integer codigoBanco;
    private String nome;

    public BancoDropdownDTO(){}

    public BancoDropdownDTO(Integer bancoId, Integer codigoBanco ,String nome){
        this.bancoId = bancoId;
        this.codigoBanco = codigoBanco;
        this.nome = nome;
    }

    public Integer getBancoId() { return bancoId; }

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
}
