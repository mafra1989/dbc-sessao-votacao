package br.com.dbc.domain.model;

import br.com.dbc.domain.model.enumerators.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessaoDomain {

    private Long id;
    private Integer tempoVotacao;
    private LocalDateTime terminoVotacao;
    private Long quantidadeVotos;
    private Long sim;
    private Long nao;
    private PautaDomain pautaDomain;

    public void changeTempoVotacao() {
        if(tempoVotacao == null) {
            tempoVotacao = 1;
        }

        terminoVotacao = LocalDateTime.now().plusMinutes(tempoVotacao);
    }

    public void changePauta(PautaDomain pautaDomain) {
        this.pautaDomain = pautaDomain;
    }

    public Boolean verficaTerminoVotocao() {
        long diff = Duration.between(LocalDateTime.now(), terminoVotacao).getSeconds();
        return diff > 0;
    }

    public void contabilizarVotos(List<VotoDomain> votos) {
        quantidadeVotos = votos.stream().count();
        sim = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.SIM.getOpcao())).count();
        nao = votos.stream().filter(v -> v.getOpcao().equals(VotoEnum.NAO.getOpcao())).count();
    }

}
