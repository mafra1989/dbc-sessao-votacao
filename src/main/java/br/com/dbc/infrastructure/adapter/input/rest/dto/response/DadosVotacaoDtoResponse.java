package br.com.dbc.infrastructure.adapter.input.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadosVotacaoDtoResponse {

    @Schema(name = "message", example = "Processamento realizado com sucesso.")
    private String message;

    @Schema(name = "status", example = "OK")
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

    public DadosVotacaoDtoResponse(String message, HttpStatus status) {
        super();
        this.message = message;
        this.status = status;
    }

}