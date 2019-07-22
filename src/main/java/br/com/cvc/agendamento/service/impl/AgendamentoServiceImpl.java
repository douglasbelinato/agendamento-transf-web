package br.com.cvc.agendamento.service.impl;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.service.AgendamentoService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Override
    public ConsultaAgendamentosDTO listar(FiltroConsultaAgendamentoDTO dto) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/agendamentos")
                .queryParam("idUsuario", dto.getIdUsuario())
                .queryParam("pagina", dto.getPagina())
                .queryParam("qtdRegistrosPagina", dto.getQtdRegistrosPagina())
                .queryParam("colunaOrdenacao", dto.getColunaOrdenacao());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ConsultaAgendamentosDTO> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                ConsultaAgendamentosDTO.class);

        return responseEntity.getBody();
    }

    @Override
    public NovoAgendamentoDTO inserir(AgendamentoDTO dto) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NovoAgendamentoDTO> responseEntity = restTemplate.postForEntity("http://localhost:8080/agendamentos", dto, NovoAgendamentoDTO.class);

        return responseEntity.getBody();
    }
}
