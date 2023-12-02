package br.com.dbc.domain.model.enumerators;

import br.com.dbc.domain.exception.CpfInvalidoException;
import br.com.dbc.domain.exception.OpcaoInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
    ABLE_TO_VOTE("ABLE_TO_VOTE");

    private final String status;

    public static StatusEnum obterStatus(String opcao) {
        return Stream.of(values())
                .filter(votoEnum -> votoEnum.getStatus().equals(opcao))
                .findFirst().orElseThrow(() -> new CpfInvalidoException(
                        MensagensNegociosEnum.CPF_INVALIDO.getCodigo(),
                        MensagensNegociosEnum.CPF_INVALIDO.getMensagem()
                ));
    }

}
