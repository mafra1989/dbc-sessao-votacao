package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.SessaoDomain;

import java.util.List;

public interface SessaoOutPort {

    List<SessaoDomain> listarSessoes(Long pautaId);

    SessaoDomain salvarSessao(SessaoDomain domain);

    SessaoDomain consultarSessao(Long sessaoId, Long pautaId);

}
