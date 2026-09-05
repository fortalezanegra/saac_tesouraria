package br.com.adbrascosmopolis.saac_tesouraria.dto.banco;

import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class BancoRequestDTO {

    private Integer codigoBanco;
    private String nome;
    private Integer situacaoId;

    public BancoRequestDTO(
            Integer codigoBanco,
            String nome,
            Integer situacaoId
    ){
        this.codigoBanco = codigoBanco;
        this.nome = nome;
        this.situacaoId = situacaoId;
    }

    public Banco mapToBanco(){
        Banco banco = new Banco();

        banco.setCodigoBanco(this.codigoBanco);
        banco.setNome(this.nome);

        //Relacionamentos
        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            banco.setSituacao(situacao);
        }

        return banco;
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
