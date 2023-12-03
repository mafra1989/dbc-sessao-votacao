package br.com.dbc.domain.port.input;

import br.com.dbc.domain.model.SessaoDomain;

import java.util.List;

public interface SessaoRestInPort {

    List<SessaoDomain> listarSessoes(Long pautaId);

    SessaoDomain criarSessao(Long sessaoId, SessaoDomain sessaoDomain);

    SessaoDomain consultarSessao(Long sessaoId, Long pautaId);

    void enviarResultado(Long sessaoId, Long pautaId);

}
