package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.PautaDomain;

import java.util.List;

public interface PautaOutPort {

    List<PautaDomain> listarPautas();

    PautaDomain salvarPauta(PautaDomain domain);

    PautaDomain consultarPauta(Long pautaId);

}
