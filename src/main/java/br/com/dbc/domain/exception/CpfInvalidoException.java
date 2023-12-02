package br.com.dbc.domain.exception;

public class CpfInvalidoException extends BusinessException {

    public CpfInvalidoException(String code, String message) {
        super(code, message);
    }

}