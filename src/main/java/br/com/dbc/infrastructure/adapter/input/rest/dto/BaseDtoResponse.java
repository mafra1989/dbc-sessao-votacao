package br.com.dbc.infrastructure.adapter.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"apiVersion", "traceId", "baseUrl", "data" })
@JsonInclude(JsonInclude.Include. NON_NULL)
public class BaseDtoResponse<T> {

    @JsonProperty("apiVersion")
    @Schema(description = "Versão atual da API", example = "v1", name = "apiVersion")
    private String apiVersion = null;

    /*
    @JsonProperty("traceId")
    @Schema(description = "Id da transação indiciada pela requisição",  example = "4o2rNVq6fkggpWURx6OaitJ7r0OxbRbT", name = "traceId")
    private String traceId = null;
    */

    @JsonProperty("baseUrl")
    @Schema(description = "Base da operação do serviço.",  example = "/api/...", name = "baseUrl")
    private String baseUrl = null;

    @JsonProperty("data")
    private T data = null;

}