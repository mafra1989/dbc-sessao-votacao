package br.com.dbc.domain.exception;

public class CpfFeignException extends BusinessException {

    public CpfFeignException(String code, String message) {
        super(code, message);
    }

}