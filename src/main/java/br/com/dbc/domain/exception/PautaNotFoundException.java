package br.com.dbc.domain.exception;

public class PautaNotFoundException extends BusinessException {

    public PautaNotFoundException(String code, String message) {
        super(code, message);
    }

}