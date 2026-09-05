package br.com.adbrascosmopolis.saac_tesouraria.dto.categoria;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCategoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.Categoria;

public class CategoriaRequestDTO {

    String descricao;
    TipoCategoria tipoCategoria;

    CategoriaRequestDTO(
            String descricao,
            TipoCategoria tipoCategoria
    ){
        this.descricao = descricao;
        this.tipoCategoria = tipoCategoria;
    }

    public Categoria mapToCategoria(){
        Categoria categoria = new Categoria();

        categoria.setDescricao(this.descricao);
        categoria.setTipoCategoria(this.tipoCategoria);

        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }
}
