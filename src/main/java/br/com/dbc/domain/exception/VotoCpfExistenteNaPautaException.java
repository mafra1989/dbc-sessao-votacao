package br.com.dbc.domain.exception;

public class VotoCpfExistenteNaPautaException extends BusinessException {

    public VotoCpfExistenteNaPautaException(String code, String message) {
        super(code, message);
    }

}