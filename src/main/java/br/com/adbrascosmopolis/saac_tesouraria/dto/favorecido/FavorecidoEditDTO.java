package br.com.adbrascosmopolis.saac_tesouraria.dto.favorecido;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoPessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Favorecido;

public class FavorecidoEditDTO {

    private Long favorecidoId;
    private String nome;
    private TipoPessoa tipoPessoa;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String observacao;
    private Integer situacaoId;

    public static FavorecidoEditDTO fromFavorecido(Favorecido f){

        FavorecidoEditDTO dto = new FavorecidoEditDTO();

        dto.favorecidoId = f.getFavorecidoId();
        dto.nome = f.getNome();
        dto.tipoPessoa = f.getTipoPessoa();
        dto.cpfCnpj = f.getCpfCnpj();
        dto.telefone = f.getTelefone();
        dto.email = f.getEmail();
        dto.observacao = f.getObservacao();
        dto.situacaoId = f.getSituacao() != null ? f.getSituacao().getSituacaoId() : null;

        return dto;
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

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
