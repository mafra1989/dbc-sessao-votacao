package br.com.dbc.domain.usecase;

import br.com.dbc.domain.exception.TempoVotacaoExcedidoException;
import br.com.dbc.domain.exception.ValidationException;
import br.com.dbc.domain.exception.VotoCpfExistenteNaPautaException;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.model.enumerators.VotoEnum;
import br.com.dbc.domain.port.input.ValidacoesInput;
import br.com.dbc.domain.port.input.VotoInPort;
import br.com.dbc.domain.port.output.PautaOutPort;
import br.com.dbc.domain.port.output.SessaoOutPort;
import br.com.dbc.domain.port.output.VotoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VotoUseCase implements VotoInPort {

    @Autowired
    private PautaOutPort pautaOutPort;

    @Autowired
    private SessaoOutPort sessaoOutPort;

    @Autowired
    private VotoOutPort votoOutPort;

    @Autowired
    private ValidacoesInput validations;

    @Override
    public VotoDomain registrarVoto(Long pautaId, Long sessaoId, VotoDomain votoDomain) {
        Optional<StringBuilder> result = validations.execute(votoDomain);
        if(result.isPresent()) {
            throw new ValidationException("DBC100", result.get().toString());
        }

        pautaOutPort.consultarPauta(pautaId);

        SessaoDomain sessaoDomain = sessaoOutPort.consultarSessao(sessaoId, pautaId);

        Boolean sessaoAtiva = sessaoDomain.verficaTerminoVotocao();
        if(!sessaoAtiva) {
            throw new TempoVotacaoExcedidoException(
                    MensagensNegociosEnum.TEMPO_VOTACAO_EXCEDIDO.getCodigo(),
                    MensagensNegociosEnum.TEMPO_VOTACAO_EXCEDIDO.getMensagem());
        }

        Optional<VotoDomain> voto = votoOutPort.consultarVotoPorPauta(votoDomain.getCpf(), pautaId);
        if(voto.isPresent()) {
            throw new VotoCpfExistenteNaPautaException(
                    MensagensNegociosEnum.VOTO_CPF_EXISTENTE_NA_PAUTA.getCodigo(),
                    MensagensNegociosEnum.VOTO_CPF_EXISTENTE_NA_PAUTA.getMensagem());
        }

        votoDomain.changeSessao(sessaoDomain);
        votoDomain.changeVoto(VotoEnum.obterOpcao(votoDomain.getOpcao().toUpperCase()));

        return votoOutPort.salvarVoto(votoDomain);
    }
}
