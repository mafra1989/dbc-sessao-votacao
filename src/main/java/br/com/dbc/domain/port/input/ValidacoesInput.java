package br.com.dbc.domain.port.input;

import java.util.Optional;

public interface ValidacoesInput<Input> {

    Optional<StringBuilder> execute(Input input);

}