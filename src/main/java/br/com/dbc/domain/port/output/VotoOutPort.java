package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.VotoDomain;

import java.util.List;

public interface VotoOutPort {

    VotoDomain salvarVoto(VotoDomain domain);

    List<VotoDomain> consultarVotosPorSessao(Long sessaoId);

}
