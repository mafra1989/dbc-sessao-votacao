package br.com.dbc.infrastructure.adapter.output.persistence.repository;

import br.com.dbc.infrastructure.adapter.output.persistence.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    List<SessaoEntity> findAllByPautaId(Long pautaId);

    Optional<SessaoEntity> findByIdAndPautaId(Long sessaoId, Long pautaId);

}
