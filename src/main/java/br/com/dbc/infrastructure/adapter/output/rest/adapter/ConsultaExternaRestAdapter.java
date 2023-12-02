package br.com.dbc.infrastructure.adapter.output.rest.adapter;

import br.com.dbc.domain.config.ProfileConfiguration;
import br.com.dbc.domain.exception.CpfFeignException;
import br.com.dbc.domain.model.enumerators.MensagensNegociosEnum;
import br.com.dbc.domain.port.output.ConsultaExternaOutPort;
import br.com.dbc.infrastructure.adapter.output.rest.entity.CpfResponseEntity;
import br.com.dbc.infrastructure.adapter.output.rest.service.CpfFeignClientService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsultaExternaRestAdapter implements ConsultaExternaOutPort {

    @Autowired
    private CpfFeignClientService cpfFeignClientService;

    @Autowired
    private ProfileConfiguration profile;

    static final String LOG_URL_GET = "Chamada GET para a URL [{0}]";

    @Override
    public String consultaCpf(String cpf) {
        ResponseEntity<CpfResponseEntity> cpfResponseEntity = null;

        try {
            log.info(LOG_URL_GET.replace("{0}", profile.getUriRunMockyCpf()));
            cpfResponseEntity = cpfFeignClientService.consultaCpf(cpf);
        } catch (FeignException ex) {
            throw new CpfFeignException(MensagensNegociosEnum.ERRO_SERVICO_EXTERNO.getCodigo(),
                    MensagensNegociosEnum.ERRO_SERVICO_EXTERNO.getMensagem());
        }

        return cpfResponseEntity.getBody().getStatus();
    }

}