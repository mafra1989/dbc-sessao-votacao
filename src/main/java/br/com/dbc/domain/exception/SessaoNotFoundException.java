package br.com.dbc.domain.exception;

public class SessaoNotFoundException extends BusinessException {

    public SessaoNotFoundException(String code, String message) {
        super(code, message);
    }

}