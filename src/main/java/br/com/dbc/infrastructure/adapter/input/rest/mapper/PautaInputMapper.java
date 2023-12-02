package br.com.dbc.infrastructure.adapter.input.rest.mapper;

import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.PautaDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PautaInputMapper {

	PautaDomain toDomain(PautaDtoRequest request);

	PautaDtoResponse toResponseDto(PautaDomain domain);

}
