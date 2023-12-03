package br.com.dbc.domain.port.output;

import br.com.dbc.domain.model.SessaoDomain;

public interface SessaoVotacaoKafkaOutPort {

    void enviarResultado(SessaoDomain sessaoDomain);

}
