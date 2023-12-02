package br.com.dbc.infrastructure.adapter.output.persistence.adapter;

import br.com.dbc.domain.exception.SessaoNotFoundException;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.port.output.SessaoPersistenceOutPort;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.SessaoEntity;
import br.com.dbc.infrastructure.adapter.output.persistence.mapper.SessaoOutputMapper;
import br.com.dbc.infrastructure.adapter.output.persistence.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessaoPersistenceAdapter implements SessaoPersistenceOutPort {

    @Autowired
    private SessaoRepository repository;

    @Autowired
    private SessaoOutputMapper mapper;

    @Override
    public List<SessaoDomain> listarSessoes(Long pautaId) {
        List<SessaoEntity> entities = repository.findAllByPautaId(pautaId);

        List<SessaoDomain> sessoes = new ArrayList<SessaoDomain>();
        if(entities.size() > 0) {
            sessoes = entities.stream().map(e -> mapper.toDomain(e)).collect(Collectors.toList());
        }
        return sessoes;
    }

    @Override
    public SessaoDomain salvarSessao(SessaoDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain)));
    }

    @Override
    public SessaoDomain consultarSessao(Long sessaoId, Long pautaId) {
        return mapper.toDomain(repository.findByIdAndPautaId(sessaoId, pautaId).orElseThrow(() -> new SessaoNotFoundException(
                MensagensNegociosEnum.SESSAO_NAO_ENCONTRADA.getCodigo(),
                MensagensNegociosEnum.SESSAO_NAO_ENCONTRADA.getMensagem())));
    }

}
