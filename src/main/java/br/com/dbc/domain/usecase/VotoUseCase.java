package br.com.dbc.domain.usecase;

import br.com.dbc.domain.exception.CpfInvalidoException;
import br.com.dbc.domain.exception.ValidationException;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.model.enumerators.StatusEnum;
import br.com.dbc.domain.model.enumerators.VotoEnum;
import br.com.dbc.domain.port.input.ValidacoesInput;
import br.com.dbc.domain.port.input.VotoInPort;
import br.com.dbc.domain.port.output.ConsultaExternaOutPort;
import br.com.dbc.domain.port.output.SessaoOutPort;
import br.com.dbc.domain.port.output.VotoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VotoUseCase implements VotoInPort {

    @Autowired
    private SessaoOutPort sessaoOutPort;

    @Autowired
    private VotoOutPort votoOutPort;

    @Autowired
    private ValidacoesInput validations;

    @Autowired
    private ConsultaExternaOutPort consultaExternaOutPort;

    @Override
    public VotoDomain registrarVoto(Long pautaId, Long sessaoId, VotoDomain votoDomain) {
        Optional<StringBuilder> result = validations.execute(votoDomain);
        if(result.isPresent()) {
            throw new ValidationException("DBC100", result.get().toString());
        }

        String status = consultaExternaOutPort.consultaCpf(votoDomain.getCpf());
        if(StatusEnum.UNABLE_TO_VOTE == StatusEnum.obterStatus(status.toUpperCase())) {
            throw new CpfInvalidoException(
                    MensagensNegociosEnum.CPF_INVALIDO.getCodigo(),
                    MensagensNegociosEnum.CPF_INVALIDO.getMensagem()
            );
        }

        SessaoDomain sessaoDomain = sessaoOutPort.consultarSessao(sessaoId, pautaId);

        votoDomain.changeVoto(VotoEnum.obterOpcao(votoDomain.getOpcao().toUpperCase()));
        votoDomain.changeSessao(sessaoDomain);

        return votoOutPort.salvarVoto(votoDomain);
    }
}
