package br.com.adbrascosmopolis.saac_tesouraria.exceptions.response;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private String codigo;
    private String mensagem;
    private LocalDateTime dataHora;

    public ApiErrorResponse(String codigo, String mensagem){
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
