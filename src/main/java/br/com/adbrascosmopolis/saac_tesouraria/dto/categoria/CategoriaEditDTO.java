package br.com.adbrascosmopolis.saac_tesouraria.dto.categoria;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCategoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.Categoria;

public class CategoriaEditDTO {

    Integer categoriaId;
    String descricao;
    TipoCategoria tipoCategoria;

    public static CategoriaEditDTO fromCategoria(Categoria c){

        CategoriaEditDTO dto = new CategoriaEditDTO();

        dto.categoriaId = c.getCategoriaId();
        dto.descricao = c.getDescricao();
        dto.tipoCategoria = c.getTipoCategoria();

        return dto;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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
