package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.VotoDomain;

import java.util.List;
import java.util.Optional;

public interface VotoOutPort {

    List<VotoDomain> listarVotosPorSessao(Long sessaoId);

    VotoDomain salvarVoto(VotoDomain domain);

    Optional<VotoDomain> consultarVotoPorPauta(String cpf, Long pautaId);

}
