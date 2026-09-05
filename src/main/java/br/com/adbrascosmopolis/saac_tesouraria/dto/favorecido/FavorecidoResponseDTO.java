package br.com.adbrascosmopolis.saac_tesouraria.dto.favorecido;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoPessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Favorecido;

public class FavorecidoResponseDTO {

    private Long favorecidoId;
    private String nome;
    private TipoPessoa tipoPessoa;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String observacao;
    private String situacao;

    public FavorecidoResponseDTO(
            Long favorecidoId,
            String nome,
            TipoPessoa tipoPessoa,
            String cpfCnpj,
            String telefone,
            String email,
            String observacao,
            String situacao
    ){
        this.favorecidoId = favorecidoId;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
        this.situacao = situacao;
    }

    public static FavorecidoResponseDTO fromFavorecido(Favorecido f){

        if(f == null){
            return null;
        }

        return new  FavorecidoResponseDTO(
                f.getFavorecidoId(),
                f.getNome(),
                f.getTipoPessoa(),
                f.getCpfCnpj(),
                f.getTelefone(),
                f.getEmail(),
                f.getObservacao(),
                f.getSituacao() != null ? f.getSituacao().getDescricao() : null
        );
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
