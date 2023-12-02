package br.com.dbc.infrastructure.adapter.output.persistence.adapter;

import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.port.output.VotoOutPort;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.VotoEntity;
import br.com.dbc.infrastructure.adapter.output.persistence.mapper.VotoOutputMapper;
import br.com.dbc.infrastructure.adapter.output.persistence.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VotoPersistenceAdapter implements VotoOutPort {

    @Autowired
    private VotoRepository repository;

    @Autowired
    private VotoOutputMapper mapper;

    @Override
    public List<VotoDomain> listarVotosPorSessao(Long sessaoId) {
        List<VotoEntity> entities = repository.findAllBySessaoId(sessaoId);

        List<VotoDomain> votos = new ArrayList<VotoDomain>();
        if(entities.size() > 0) {
            votos = entities.stream().map(e -> mapper.toDomain(e)).collect(Collectors.toList());
        }
        return votos;
    }

    @Override
    public VotoDomain salvarVoto(VotoDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain)));
    }

    @Override
    public Optional<VotoDomain> consultarVotoPorPauta(String cpf, Long pautaId) {
        Optional<VotoEntity> entity = repository.findByCpfAndPautaId(cpf, pautaId);

        if(entity.isPresent()) {
            return Optional.ofNullable(mapper.toDomain(entity.get()));
        }

        return Optional.empty();
    }

}
