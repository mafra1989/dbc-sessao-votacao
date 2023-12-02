package br.com.dbc.domain.model.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensNegociosEnum {

    CPF_INVALIDO("10","O cpf não é válido."),
    OPCAO_INVALIDA("20","A opção informada não é válida."),
    ERRO_SERVICO_EXTERNO("30","Erro na chamada do serviço externo."),
    PAUTA_NAO_ENCONTRADA("DBC010","Pauta não encontrada."),
    SESSAO_NAO_ENCONTRADA("DBC020","Sessão não encontrada."),
    VOTO_CPF_EXISTENTE_NA_PAUTA("DBC030","Voto com cpf existente na pauta."),
    TEMPO_VOTACAO_EXCEDIDO("DBC040","Tempo de votação excedido.");

    private final String codigo;
    private final String mensagem;

}
