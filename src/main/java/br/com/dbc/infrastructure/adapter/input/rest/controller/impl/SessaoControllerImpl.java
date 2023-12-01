package br.com.dbc.infrastructure.adapter.input.rest.controller.impl;


import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.port.input.SessaoInPort;
import br.com.dbc.infrastructure.adapter.input.rest.controller.SessaoController;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.SessaoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.SessaoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.mapper.SessaoInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SessaoControllerImpl implements SessaoController {

    @Autowired
    private SessaoInPort sessaoInPort;

    @Autowired
    private SessaoInputMapper mapper;

    @Override
    public ResponseEntity<List<SessaoDtoResponse>> listarSessoes(Long pautaId) {
        List<SessaoDomain> domains = sessaoInPort.listarSessoes(pautaId);

        List<SessaoDtoResponse> sessoes = new ArrayList<SessaoDtoResponse>();
        if(domains.size() > 0) {
            sessoes = domains.stream().map(e -> mapper.toResponseDto(e)).collect(Collectors.toList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(sessoes);
    }

    @Override
    public ResponseEntity<SessaoDtoResponse> criarSessao(Long sessaoId, SessaoDtoRequest request) {
        SessaoDomain sessaoDomain = sessaoInPort.criarSessao(sessaoId, mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(sessaoDomain));
    }

    @Override
    public ResponseEntity<SessaoDtoResponse> consultarSessao(Long sessaoId, Long pautaId) {
        SessaoDomain sessaoDomain = sessaoInPort.consultarSessao(sessaoId, pautaId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDto(sessaoDomain));
    }

}
