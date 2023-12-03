package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.SessaoDomain;

import java.util.List;

public interface SessaoPersistenceOutPort {

    List<SessaoDomain> listarSessoes(Long pautaId);

    SessaoDomain salvarSessao(SessaoDomain sessaoDomain);

    SessaoDomain consultarSessao(Long sessaoId, Long pautaId);

}
