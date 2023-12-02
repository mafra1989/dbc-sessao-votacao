package br.com.dbc.domain.usecase;

import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.model.enumerators.VotoEnum;
import br.com.dbc.domain.port.input.SessaoInPort;
import br.com.dbc.domain.port.output.PautaOutPort;
import br.com.dbc.domain.port.output.SessaoOutPort;
import br.com.dbc.domain.port.output.VotoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessaoUseCase implements SessaoInPort {

    @Autowired
    private PautaOutPort pautaOutPort;

    @Autowired
    private SessaoOutPort sessaoOutPort;

    @Autowired
    private VotoOutPort votoOutPort;

    @Override
    public List<SessaoDomain> listarSessoes(Long pautaId) {
        pautaOutPort.consultarPauta(pautaId);

        return sessaoOutPort.listarSessoes(pautaId);
    }

    @Override
    public SessaoDomain criarSessao(Long pautaId, SessaoDomain sessaoDomain) {
        PautaDomain pautaDomain = pautaOutPort.consultarPauta(pautaId);

        sessaoDomain.changeTempoVotacao();
        sessaoDomain.changePauta(pautaDomain);

        return sessaoOutPort.salvarSessao(sessaoDomain);
    }

    @Override
    public SessaoDomain consultarSessao(Long sessaoId, Long pautaId) {
        pautaOutPort.consultarPauta(pautaId);

        SessaoDomain sessaoDomain = sessaoOutPort.consultarSessao(sessaoId, pautaId);

        List<VotoDomain> votos = votoOutPort.listarVotosPorSessao(sessaoId);

        long quantidadeVotos = votos.stream().count();
        sessaoDomain.changeTotalVotos(quantidadeVotos);

        long votosSim = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.SIM.getOpcao())).count();
        sessaoDomain.changeTotalVotosSim(votosSim);

        long votosNao = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.NAO.getOpcao())).count();
        sessaoDomain.changeTotalVotosNao(votosNao);

        return sessaoDomain;
    }

}
