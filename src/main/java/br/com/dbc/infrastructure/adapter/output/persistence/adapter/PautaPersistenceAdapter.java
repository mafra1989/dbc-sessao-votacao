package br.com.dbc.infrastructure.adapter.output.persistence.adapter;

import br.com.dbc.domain.exception.PautaNotFoundException;
import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.port.output.PautaPersistenceOutPort;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.PautaEntity;
import br.com.dbc.infrastructure.adapter.output.persistence.mapper.PautaOutputMapper;
import br.com.dbc.infrastructure.adapter.output.persistence.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaPersistenceAdapter implements PautaPersistenceOutPort {

    @Autowired
    private PautaRepository repository;

    @Autowired
    private PautaOutputMapper mapper;

    @Override
    public List<PautaDomain> listarPautas() {
        List<PautaEntity> entities = repository.findAll();

        List<PautaDomain> pautas = new ArrayList<PautaDomain>();
        if(entities.size() > 0) {
            pautas = entities.stream().map(e -> mapper.toDomain(e)).collect(Collectors.toList());
        }
        return pautas;
    }

    @Override
    public PautaDomain salvarPauta(PautaDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain)));
    }

    @Override
    public PautaDomain consultarPauta(Long id) {
        return mapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new PautaNotFoundException(
                        MensagensNegociosEnum.PAUTA_NAO_ENCONTRADA.getCodigo(),
                        MensagensNegociosEnum.PAUTA_NAO_ENCONTRADA.getMensagem())));
    }

}
