package br.com.adbrascosmopolis.saac_tesouraria.dto.favorecido;

public class FavorecidoDropdownDTO {

    private Long favorecidoId;
    private String nome;

    public FavorecidoDropdownDTO(){}

    public FavorecidoDropdownDTO(Long favorecidoId, String nome){
        this.favorecidoId = favorecidoId;
        this.nome = nome;
    }

    public Long getFavorecidoId() {
        return favorecidoId;
    }

    public void setFavorecidoId(Long favorecidoId) {
        this.favorecidoId = favorecidoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
