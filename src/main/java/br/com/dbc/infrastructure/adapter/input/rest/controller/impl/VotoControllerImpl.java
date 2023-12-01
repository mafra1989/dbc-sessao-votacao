package br.com.dbc.infrastructure.adapter.input.rest.controller.impl;


import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.port.input.VotoInPort;
import br.com.dbc.infrastructure.adapter.input.rest.controller.VotoController;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.VotoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.VotoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.mapper.VotoInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotoControllerImpl implements VotoController {

    @Autowired
    private VotoInPort votoInPort;

    @Autowired
    private VotoInputMapper mapper;

    @Override
    public ResponseEntity<VotoDtoResponse> registrarVoto(Long pautaId, Long sessaoId, VotoDtoRequest request) {
        VotoDomain votoDomain = votoInPort.registrarVoto(pautaId, sessaoId, mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(votoDomain));
    }
}
