package br.com.adbrascosmopolis.saac_tesouraria.dto.categoria;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCategoria;

public class CategoriaDropdownDTO {

    private Integer categoriaId;
    private String descricao;
    private TipoCategoria tipoCategoria;

    public CategoriaDropdownDTO(){}

    public CategoriaDropdownDTO(
            Integer categoriaId,
            String descricao,
            TipoCategoria tipoCategoria
    ){
        this.categoriaId = categoriaId;
        this.descricao = descricao;
        this.tipoCategoria = tipoCategoria;
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
