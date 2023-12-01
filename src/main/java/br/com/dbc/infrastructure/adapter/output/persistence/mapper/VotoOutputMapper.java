package br.com.dbc.infrastructure.adapter.output.persistence.mapper;

import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.VotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotoOutputMapper {

	VotoDomain toDomain(VotoEntity entity);

	@Mapping(target = "sessaoId", source = "sessaoDomain.id")
	VotoEntity toEntity(VotoDomain domain);

}
