package br.com.dbc.infrastructure.adapter.input.rest.controller.impl;


import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.port.input.SessaoRestInPort;
import br.com.dbc.infrastructure.adapter.input.rest.controller.SessaoController;
import br.com.dbc.infrastructure.adapter.input.rest.dto.BaseDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.SessaoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.DadosVotacaoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.SessaoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.dbc.infrastructure.adapter.input.rest.mapper.SessaoInputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SessaoControllerImpl implements SessaoController {

    @Autowired
    private SessaoRestInPort sessaoRestInPort;

    @Autowired
    private SessaoInputMapper mapper;

    @Override
    public ResponseEntity<List<SessaoDtoResponse>> listarSessoes(Long pautaId) {
        List<SessaoDomain> domains = sessaoRestInPort.listarSessoes(pautaId);

        List<SessaoDtoResponse> sessoes = new ArrayList<SessaoDtoResponse>();
        if(domains.size() > 0) {
            sessoes = domains.stream().map(e -> mapper.toResponseDto(e)).collect(Collectors.toList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(sessoes);
    }

    @Override
    public ResponseEntity<SessaoDtoResponse> criarSessao(Long sessaoId, SessaoDtoRequest request) {
        SessaoDomain sessaoDomain = sessaoRestInPort.criarSessao(sessaoId, mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(sessaoDomain));
    }

    @Override
    public ResponseEntity<SessaoDtoResponse> consultarSessao(Long sessaoId, Long pautaId) {
        SessaoDomain sessaoDomain = sessaoRestInPort.consultarSessao(sessaoId, pautaId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDto(sessaoDomain));
    }

    @Override
    public ResponseEntity<BaseDtoResponse<PautaDtoResponse>> enviarResultado(Long sessaoId, Long pautaId) {
        log.info("Início do método, m= {}", "enviarResultado");

        sessaoRestInPort.enviarResultado(sessaoId, pautaId);

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setBaseUrl(MessageFormat.format("/v1/pautas/{0}/sessoes/{1}/send", pautaId, sessaoId));
        baseDtoResponse.setData(new DadosVotacaoDtoResponse(MensagenInfraestruturaEnum.SUCESSO.getMensagem(), HttpStatus.OK));

        return ResponseEntity.status(HttpStatus.OK).body(baseDtoResponse);
    }

}
