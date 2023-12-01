package br.com.dbc.domain.port.input;

import br.com.dbc.domain.model.SessaoDomain;

import java.util.List;

public interface SessaoInPort {

    List<SessaoDomain> listarSessoes(Long pautaId);

    SessaoDomain criarSessao(Long sessaoId, SessaoDomain sessaoDomain);

    SessaoDomain consultarSessao(Long sessaoId, Long pautaId);

}
