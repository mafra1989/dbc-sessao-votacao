package br.com.dbc.infrastructure.adapter.input.rest.mapper;

import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.VotoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.VotoDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotoInputMapper {

	@Mapping(target = "cpf", source = "cpf")
	VotoDomain toDomain(VotoDtoRequest request);

	VotoDtoResponse toResponseDto(VotoDomain domain);

}
