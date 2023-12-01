package br.com.dbc.infrastructure.adapter.output.persistence.mapper;

import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.PautaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PautaOutputMapper {

	PautaDomain toDomain(PautaEntity entity);

	PautaEntity toEntity(PautaDomain domain);

}
