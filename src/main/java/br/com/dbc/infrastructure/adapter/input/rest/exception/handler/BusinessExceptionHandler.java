package br.com.dbc.infrastructure.adapter.input.rest.exception.handler;

import br.com.dbc.domain.exception.OpcaoInvalidaException;
import br.com.dbc.domain.exception.PautaNotFoundException;
import br.com.dbc.domain.exception.SessaoNotFoundException;
import br.com.dbc.domain.exception.TempoVotacaoExcedidoException;
import br.com.dbc.domain.exception.ValidationException;
import br.com.dbc.domain.exception.VotoCpfExistenteNaPautaException;
import br.com.dbc.infrastructure.adapter.input.rest.dto.BaseDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.dto.response.DadosVotacaoDtoResponse;
import br.com.dbc.infrastructure.adapter.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.dbc.infrastructure.adapter.input.rest.exception.erro.ApiErroResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseDtoResponse handleValidationException(ValidationException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseDtoResponse handleInvalidFormatException(InvalidFormatException  ex) {
        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);

        error.addErroNegocio("40", MensagenInfraestruturaEnum.VALOR_INVALIDO.getMensagem());

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(PautaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseDtoResponse handlePautaNotFoundException(PautaNotFoundException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.NOT_FOUND, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(SessaoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseDtoResponse handleSessaoNotFoundException(SessaoNotFoundException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.NOT_FOUND, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(OpcaoInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseDtoResponse handleOpcaoInvalidaException(OpcaoInvalidaException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(VotoCpfExistenteNaPautaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseDtoResponse handleVotoCpfExistenteNaPautaException(VotoCpfExistenteNaPautaException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

    @ExceptionHandler(TempoVotacaoExcedidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseDtoResponse handleTempoVotacaoExcedidoException(TempoVotacaoExcedidoException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        BaseDtoResponse baseDtoResponse = new BaseDtoResponse<DadosVotacaoDtoResponse>();
        baseDtoResponse.setApiVersion("v1");
        baseDtoResponse.setData(error);

        return baseDtoResponse;
    }

}
