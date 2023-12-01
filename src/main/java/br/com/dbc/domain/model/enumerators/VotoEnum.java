package br.com.dbc.domain.model.enumerators;

import br.com.dbc.domain.exception.OpcaoInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum VotoEnum {

    SIM("SIM"),
    NAO("NÃO");

    private final String opcao;

    public static VotoEnum obterOpcao(String opcao) {
        return Stream.of(values())
                .filter(votoEnum -> votoEnum.getOpcao().equals(opcao))
                .findFirst().orElseThrow(() -> new OpcaoInvalidaException(
                        MensagensNegociosEnum.OPCAO_INVALIDA.getCodigo(),
                        MensagensNegociosEnum.OPCAO_INVALIDA.getMensagem()
                ));
    }

}
