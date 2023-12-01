package br.com.dbc.infrastructure.adapter.output.persistence.mapper;

import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.infrastructure.adapter.output.persistence.entity.SessaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessaoOutputMapper {

	@Mapping(target = "id", source = "id")
	@Mapping(target = "pautaDomain.id", source = "pautaId")
	SessaoDomain toDomain(SessaoEntity entity);

	@Mapping(target = "pautaId", source = "pautaDomain.id")
	SessaoEntity toEntity(SessaoDomain domain);

}
