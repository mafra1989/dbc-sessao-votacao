package br.com.dbc.domain.usecase;

import br.com.dbc.domain.exception.ValidationException;
import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.port.input.PautaRestInPort;
import br.com.dbc.domain.port.input.ValidacoesInput;
import br.com.dbc.domain.port.output.PautaPersistenceOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PautaRestUseCase implements PautaRestInPort {

    @Autowired
    private PautaPersistenceOutPort pautaPersistenceOutPort;

    @Autowired
    private ValidacoesInput validations;

    @Override
    public List<PautaDomain> listarPautas() {
        return pautaPersistenceOutPort.listarPautas();
    }

    @Override
    public PautaDomain cadastrarPauta(PautaDomain pautaDomain) {
        Optional<StringBuilder> result = validations.execute(pautaDomain);
        if(result.isPresent()) {
            throw new ValidationException("DBC100", result.get().toString());
        }

        return pautaPersistenceOutPort.salvarPauta(pautaDomain);
    }

    @Override
    public PautaDomain consultarPauta(Long pautaId) {
        return pautaPersistenceOutPort.consultarPauta(pautaId);
    }

}
