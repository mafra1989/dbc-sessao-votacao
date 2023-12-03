package br.com.dbc.infrastructure.adapter.output.kafka.adapter;

import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.port.output.SessaoVotacaoKafkaOutPort;
import br.com.dbc.infrastructure.adapter.output.kafka.producer.SessaoVotacaoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SessaoVotacaoKafkaAdapter implements SessaoVotacaoKafkaOutPort {

    @Autowired
    private SessaoVotacaoProducer sessaoVotacaoProducer;

    @Value("${spring.kafka.topico.sessao-votacao:NOT_DEFINED}")
    private String topicoSessaoVotacao;


    @Override
    public void enviarResultado(SessaoDomain sessaoDomain) {
        sessaoVotacaoProducer.send(topicoSessaoVotacao, sessaoDomain);
    }

}