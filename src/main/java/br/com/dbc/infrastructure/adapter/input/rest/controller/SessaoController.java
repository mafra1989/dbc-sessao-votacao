package br.com.dbc.infrastructure.adapter.input.rest.controller;


import br.com.dbc.infrastructure.adapter.input.rest.dto.BaseDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.SessaoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.SessaoDtoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Tag(name = "Gestão de Sessões")
@RequestMapping("/v1/pautas/{pautaId}/sessoes")
public interface SessaoController {

    @GetMapping
    ResponseEntity<List<SessaoDtoResponse>> listarSessoes(@PathVariable(name = "pautaId") Long pautaId);

    @PostMapping
    ResponseEntity<SessaoDtoResponse> criarSessao(
            @PathVariable(name = "pautaId") Long pautaId,
            @RequestBody SessaoDtoRequest request);

    @GetMapping("/{sessaoId}")
    ResponseEntity<SessaoDtoResponse> consultarSessao(
            @PathVariable(name = "sessaoId") Long sessaoId,
            @PathVariable(name = "pautaId") Long pautaId);

    @PostMapping("/{sessaoId}/send")
    ResponseEntity<BaseDtoResponse<PautaDtoResponse>> enviarResultado(
            @PathVariable(name = "sessaoId") Long sessaoId,
            @PathVariable(name = "pautaId") Long pautaId);

}
