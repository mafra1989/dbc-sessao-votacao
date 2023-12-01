package br.com.dbc.domain.model;

import br.com.dbc.domain.model.enumerators.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoDomain {

    private Long id;

    @NotBlank(message = "{campo.opcao.notblank}")
    private String opcao;

    @NotBlank(message = "{campo.cpf.notblank}")
    private String cpf;

    private SessaoDomain sessaoDomain;

    public void changeVoto(VotoEnum votoEnum) {
        opcao = votoEnum.getOpcao();
    }

    public void changeSessao(SessaoDomain sessaoDomain) {
        this.sessaoDomain = sessaoDomain;
    }

}
