package br.com.dbc.infrastructure.adapter.input.rest.controller.impl;


import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.port.input.PautaRestInPort;
import br.com.dbc.infrastructure.adapter.input.rest.controller.PautaController;
import br.com.dbc.infrastructure.adapter.input.rest.dto.BaseDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.PautaDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.DadosVotacaoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.dbc.infrastructure.adapter.input.rest.mapper.PautaInputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class PautaControllerImpl implements PautaController {

    @Autowired
    private PautaRestInPort pautaRestInPort;

    @Autowired
    private PautaInputMapper mapper;

    @Override
    public ResponseEntity<List<PautaDtoResponse>> listarPautas() {
        List<PautaDomain> domains = pautaRestInPort.listarPautas();

        List<PautaDtoResponse> pautas = new ArrayList<PautaDtoResponse>();
        if(domains.size() > 0) {
            pautas = domains.stream().map(e -> mapper.toResponseDto(e)).collect(Collectors.toList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(pautas);
    }

    @Override
    public ResponseEntity<BaseDtoResponse<PautaDtoResponse>> cadastrarPauta(PautaDtoRequest request) {
        log.info("Início do método, m= {}", "cadastrarPauta");

        pautaRestInPort.cadastrarPauta(mapper.toDomain(request));

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setBaseUrl("/v1/pautas");
        baseDtoResponse.setData(new DadosVotacaoDtoResponse(MensagenInfraestruturaEnum.SUCESSO.getMensagem(), HttpStatus.CREATED));

        return ResponseEntity.status(HttpStatus.CREATED).body(baseDtoResponse);
    }

    @Override
    public ResponseEntity<PautaDtoResponse> consultarPauta(Long pautaId) {
        PautaDomain domain = pautaRestInPort.consultarPauta(pautaId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDto(domain));
    }

}
