package br.com.dbc.infrastructure.adapter.input.rest.controller;


import br.com.dbc.infrastructure.adapter.input.rest.dto.request.VotoDtoRequest;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.VotoDtoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Gestão de Votos")
@RequestMapping("/v1/pautas/{pautaId}/sessoes/{sessaoId}/votos")
public interface VotoController {

    @PostMapping
    ResponseEntity<VotoDtoResponse> registrarVoto(
            @PathVariable(name = "pautaId") Long pautaId,
            @PathVariable(name = "sessaoId") Long sessaoId,
            @RequestBody VotoDtoRequest request);

}
