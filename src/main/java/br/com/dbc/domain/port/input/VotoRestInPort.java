package br.com.dbc.domain.port.input;

import br.com.dbc.domain.model.VotoDomain;

public interface VotoRestInPort {

    VotoDomain registrarVoto(Long pautaId, Long sessaoId, VotoDomain votoDomain);

}
