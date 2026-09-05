package br.com.adbrascosmopolis.saac_tesouraria.dto.favorecido;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoPessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Favorecido;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class FavorecidoRequestDTO {

    private Long favorecidoId;
    private String nome;
    private TipoPessoa tipoPessoa;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String observacao;
    private Integer situacaoId;

    public FavorecidoRequestDTO(
            Long favorecidoId,
            String nome,
            TipoPessoa tipoPessoa,
            String cpfCnpj,
            String telefone,
            String email,
            String observacao,
            Integer situacaoId
    ){
        this.favorecidoId = favorecidoId;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
        this.situacaoId = situacaoId;
    }

    public Favorecido matToFavorecido(){
        Favorecido favorecido = new Favorecido();

        favorecido.setNome(this.nome);
        favorecido.setTipoPessoa(this.tipoPessoa);
        favorecido.setCpfCnpj(this.cpfCnpj);
        favorecido.setTelefone(this.telefone);
        favorecido.setEmail(this.email);
        favorecido.setObservacao(this.observacao);

        //Relacionamentos
        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            favorecido.setSituacao(situacao);
        }

        return favorecido;
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
