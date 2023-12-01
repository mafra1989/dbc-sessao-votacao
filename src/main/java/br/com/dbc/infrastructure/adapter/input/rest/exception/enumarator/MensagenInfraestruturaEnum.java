package br.com.dbc.infrastructure.adapter.input.rest.exception.enumarator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagenInfraestruturaEnum {

    FALHA_PROCESSAMENTO("Falha no processamento da requisição."),
    VALOR_INVALIDO("Campo numérico inválido."),
    SUCESSO("Processamento realizado com sucesso.");

    private final String mensagem;

}