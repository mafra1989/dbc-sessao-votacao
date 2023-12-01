package br.com.dbc.infrastructure.adapter.output.persistence.repository;

import br.com.dbc.infrastructure.adapter.output.persistence.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {
}
