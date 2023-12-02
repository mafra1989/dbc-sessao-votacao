package br.com.dbc.domain.usecase;

import br.com.dbc.domain.model.PautaDomain;
import br.com.dbc.domain.model.SessaoDomain;
import br.com.dbc.domain.model.VotoDomain;
import br.com.dbc.domain.model.enumerators.VotoEnum;
import br.com.dbc.domain.port.input.SessaoRestInPort;
import br.com.dbc.domain.port.output.PautaPersistenceOutPort;
import br.com.dbc.domain.port.output.SessaoVotacaoKafkaOutPort;
import br.com.dbc.domain.port.output.SessaoPersistenceOutPort;
import br.com.dbc.domain.port.output.VotoPersistenceOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessaoRestUseCase implements SessaoRestInPort {

    @Autowired
    private PautaPersistenceOutPort pautaPersistenceOutPort;

    @Autowired
    private SessaoPersistenceOutPort sessaoPersistenceOutPort;

    @Autowired
    private VotoPersistenceOutPort votoPersistenceOutPort;

    @Autowired
    private SessaoVotacaoKafkaOutPort sessaoVotacaoKafkaOutPort;

    @Override
    public List<SessaoDomain> listarSessoes(Long pautaId) {
        pautaPersistenceOutPort.consultarPauta(pautaId);

        return sessaoPersistenceOutPort.listarSessoes(pautaId);
    }

    @Override
    public SessaoDomain criarSessao(Long pautaId, SessaoDomain sessaoDomain) {
        PautaDomain pautaDomain = pautaPersistenceOutPort.consultarPauta(pautaId);

        sessaoDomain.changePauta(pautaDomain);
        sessaoDomain.changeTempoVotacao();

        return sessaoPersistenceOutPort.salvarSessao(sessaoDomain);
    }

    @Override
    public SessaoDomain consultarSessao(Long sessaoId, Long pautaId) {
        pautaPersistenceOutPort.consultarPauta(pautaId);

        SessaoDomain sessaoDomain = sessaoPersistenceOutPort.consultarSessao(sessaoId, pautaId);

        List<VotoDomain> votos = votoPersistenceOutPort.listarVotosPorSessao(sessaoId);

        long quantidadeVotos = votos.stream().count();
        sessaoDomain.changeTotalVotos(quantidadeVotos);

        long votosSim = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.SIM.getOpcao())).count();
        sessaoDomain.changeTotalVotosSim(votosSim);

        long votosNao = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.NAO.getOpcao())).count();
        sessaoDomain.changeTotalVotosNao(votosNao);

        return sessaoDomain;
    }

    @Override
    public void enviarResultado(Long sessaoId, Long pautaId) {
        pautaPersistenceOutPort.consultarPauta(pautaId);

        SessaoDomain sessaoDomain = sessaoPersistenceOutPort.consultarSessao(sessaoId, pautaId);

        sessaoVotacaoKafkaOutPort.enviarResultado(sessaoDomain);
    }

}
