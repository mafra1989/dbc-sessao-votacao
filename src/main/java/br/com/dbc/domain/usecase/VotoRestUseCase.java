package br.com.dbc.domain.usecase;

import br.com.dbc.domain.exception.CpfInvalidoException;
import br.com.dbc.domain.exception.TempoVotacaoExcedidoException;
import br.com.dbc.domain.exception.ValidationException;
import br.com.dbc.domain.exception.VotoCpfExistenteNaPautaException;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.model.enumerators.StatusEnum;
import br.com.dbc.domain.model.enumerators.VotoEnum;
import br.com.dbc.domain.port.input.ValidacoesInput;
import br.com.dbc.domain.port.input.VotoRestInPort;
import br.com.dbc.domain.port.output.PautaPersistenceOutPort;
import br.com.dbc.domain.port.output.ConsultaExternaOutPort;
import br.com.dbc.domain.port.output.SessaoPersistenceOutPort;
import br.com.dbc.domain.port.output.VotoPersistenceOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VotoRestUseCase implements VotoRestInPort {

    @Autowired
    private PautaPersistenceOutPort pautaPersistenceOutPort;

    @Autowired
    private SessaoPersistenceOutPort sessaoPersistenceOutPort;

    @Autowired
    private VotoPersistenceOutPort votoPersistenceOutPort;

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

        pautaPersistenceOutPort.consultarPauta(pautaId);

        SessaoDomain sessaoDomain = sessaoPersistenceOutPort.consultarSessao(sessaoId, pautaId);

        Boolean sessaoAtiva = sessaoDomain.verficaTerminoVotocao();
        if(!sessaoAtiva) {
            throw new TempoVotacaoExcedidoException(
                    MensagensNegociosEnum.TEMPO_VOTACAO_EXCEDIDO.getCodigo(),
                    MensagensNegociosEnum.TEMPO_VOTACAO_EXCEDIDO.getMensagem());
        }

        String status = consultaExternaOutPort.consultaCpf(votoDomain.getCpf());
        if(StatusEnum.UNABLE_TO_VOTE == StatusEnum.obterStatus(status.toUpperCase())) {
            throw new CpfInvalidoException(
                    MensagensNegociosEnum.CPF_INVALIDO.getCodigo(),
                    MensagensNegociosEnum.CPF_INVALIDO.getMensagem()
            );
        }

        Optional<VotoDomain> votoPauta = votoPersistenceOutPort.consultarVotoPorPauta(votoDomain.getCpf(), pautaId);
        if(votoPauta.isPresent()) {
            throw new VotoCpfExistenteNaPautaException(
                    MensagensNegociosEnum.VOTO_CPF_EXISTENTE_NA_PAUTA.getCodigo(),
                    MensagensNegociosEnum.VOTO_CPF_EXISTENTE_NA_PAUTA.getMensagem());
        }

        votoDomain.changeSessao(sessaoDomain);
        votoDomain.changeVoto(VotoEnum.obterOpcao(votoDomain.getOpcao().toUpperCase()));

        return votoPersistenceOutPort.salvarVoto(votoDomain);
    }
}
