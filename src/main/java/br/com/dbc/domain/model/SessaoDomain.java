package br.com.dbc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

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

    public void changeTotalVotos(Long quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }

    public void changeTotalVotosSim(Long votosSim) {
        sim = votosSim;
    }

    public void changeTotalVotosNao(Long votosNao) {
        nao = votosNao;
    }

    public Boolean verficaTerminoVotocao() {
        long diff = Duration.between(LocalDateTime.now(), terminoVotacao).getSeconds();
        return diff > 0;
    }

}
