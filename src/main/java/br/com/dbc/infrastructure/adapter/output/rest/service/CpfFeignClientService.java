package br.com.dbc.infrastructure.adapter.output.rest.service;

import br.com.dbc.infrastructure.adapter.output.rest.entity.CpfResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${uriRunMockyCpf}", name = "apicpf")
public interface CpfFeignClientService {

    @GetMapping("/{cpf}")
    ResponseEntity<CpfResponseEntity> consultaCpf(@PathVariable("cpf") String cpf);

}
