package br.com.dbc.infrastructure.adapter.input.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDtoRequest {

    private String opcao;
    private String cpf;

}
