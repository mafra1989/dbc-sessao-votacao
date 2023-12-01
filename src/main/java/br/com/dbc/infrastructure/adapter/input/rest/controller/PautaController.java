package br.com.dbc.infrastructure.adapter.input.rest.controller;


import br.com.dbc.infrastructure.adapter.input.rest.dto.BaseDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.request.PautaDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.PautaDtoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Tag(name = "Gestão de Pautas")
@RequestMapping("/v1/pautas")
public interface PautaController {

    @GetMapping
    ResponseEntity<List<PautaDtoResponse>> listarPautas();

    @PostMapping
    ResponseEntity<BaseDtoResponse<PautaDtoResponse>> cadastrarPauta(
            @RequestBody PautaDtoRequest request);

    @GetMapping("/{pautaId}")
    ResponseEntity<PautaDtoResponse> consultarPauta(@PathVariable(name = "pautaId") Long pautaId);

}
