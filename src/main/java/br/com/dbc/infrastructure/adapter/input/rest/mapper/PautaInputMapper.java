package br.com.dbc.infrastructure.adapter.input.rest.mapper;

import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.PautaDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.SessaoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.SessaoDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PautaInputMapper {

	PautaDomain toDomain(PautaDtoRequest request);

	PautaDtoResponse toResponseDto(PautaDomain domain);

	SessaoDomain toDomain(SessaoDtoRequest request);

	@Mapping(target = "pautaId", source = "pautaDomain.id")
	SessaoDtoResponse toResponseDto(SessaoDomain domain);

}
