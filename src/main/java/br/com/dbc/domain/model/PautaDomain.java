package br.com.dbc.domain.model;

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
public class PautaDomain {

    private Long id;

    @NotBlank(message = "{campo.tema.notblank}")
    private String tema;

    public void changeTema(String gênerosMusicais) {
        tema = gênerosMusicais;
    }

}
