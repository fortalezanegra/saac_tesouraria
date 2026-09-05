package br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa;

public class PessoaDropdownDTO {

    private Long pessoaId;
    private String nome;

    public PessoaDropdownDTO(){}

    public PessoaDropdownDTO(Long pessoaId, String nome){
        this.pessoaId = pessoaId;
        this.nome = nome;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
