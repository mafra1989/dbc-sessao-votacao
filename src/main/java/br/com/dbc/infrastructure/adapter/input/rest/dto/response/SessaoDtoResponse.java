package br.com.dbc.infrastructure.adapter.input.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessaoDtoResponse {

    private Long id;
    private Integer tempoVotacao;
    private Long quantidadeVotos;
    private Long sim;
    private Long nao;
    private Long pautaId;

}
