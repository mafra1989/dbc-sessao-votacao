package br.com.dbc.domain.port.input;

import br.com.dbc.domain.model.PautaDomain;

import java.util.List;

public interface PautaRestInPort {

    List<PautaDomain> listarPautas();

    PautaDomain cadastrarPauta(PautaDomain pautaDomain);

    PautaDomain consultarPauta(Long id);

}
