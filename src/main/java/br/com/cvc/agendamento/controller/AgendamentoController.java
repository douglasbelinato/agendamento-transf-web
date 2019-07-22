package br.com.cvc.agendamento.controller;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
//@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping(value = "inserir")
    public String inserirTeste() {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setContaOrigem(123);
        dto.setContaDestino(123);
        dto.setDataAgendamento(LocalDate.now());
        dto.setDataTransferencia(LocalDate.now());
        dto.setValor(100d);
        dto.setIdUsuario(1L);
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        return novoAgendamentoDTO.toString();
    }

    @GetMapping(value = "consultar")
    public String consultarTeste() {
        FiltroConsultaAgendamentoDTO dto = new FiltroConsultaAgendamentoDTO();
        dto.setIdUsuario(1L);
        dto.setPagina(1);
        dto.setColunaOrdenacao(1);
        dto.setQtdRegistrosPagina(1);

        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar(dto);

        return consultaAgendamentosDTO.toString();
    }

    @GetMapping(value = "/agendamentos/novo")
    public String novo() {
        return "agendamentos/cadastro-agendamento";
    }

}
