package br.com.dbc.infrastructure.adapter.output.persistence.repository;

import br.com.dbc.infrastructure.adapter.output.persistence.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {

    List<VotoEntity> findAllBySessaoId(Long sessaoId);

    Optional<VotoEntity> findByCpfAndPautaId(String cpf, Long pautaId);

}
