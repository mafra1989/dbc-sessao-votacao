package br.com.dbc.domain.exception;

public class OpcaoInvalidaException extends BusinessException {

    public OpcaoInvalidaException(String code, String message) {
        super(code, message);
    }

}