package br.com.dbc.infrastructure.adapter.output.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessaoVotacaoProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object message) {
        log.info("Enviando dados para o tópico= {}: {}", topic, message);
        this.kafkaTemplate.send(topic, message);
    }

}
