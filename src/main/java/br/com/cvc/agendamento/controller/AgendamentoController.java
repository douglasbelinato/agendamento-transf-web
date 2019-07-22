package br.com.cvc.agendamento.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.service.AgendamentoService;

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
    
    @GetMapping(value = "/agendamentos")
    public ModelAndView listar() {
    	ModelAndView mv = new ModelAndView("agendamentos/lista-agendamentos");
    	
    	FiltroConsultaAgendamentoDTO dto = new FiltroConsultaAgendamentoDTO();
        dto.setIdUsuario(1L);
        dto.setPagina(1);
        dto.setColunaOrdenacao(1);
        dto.setQtdRegistrosPagina(1);

        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar(dto);
    	mv.addObject("consultaAgendamentosDTO", consultaAgendamentosDTO);
        
    	return mv;
    }

    @GetMapping(value = "/agendamentos/novo")
    public ModelAndView novo(AgendamentoDTO dto) {
    	return new ModelAndView("agendamentos/cadastro-agendamento");
    }
    
    @PostMapping(value = "/agendamentos/novo")
    public ModelAndView salvar(@Valid AgendamentoDTO dto, BindingResult result, RedirectAttributes redirectAttributes) {
    	if (result.hasErrors()) {
    		return novo(dto);
    	}
    	
    	try {
    		agendamentoService.inserir(dto);
		} catch(Exception e) {
			result.rejectValue(e.getMessage(), e.getMessage());
			return novo(dto);
		}    	
    	
    	redirectAttributes.addFlashAttribute("mensagem", "Agendamento de transferência salvo com sucesso!");
		
		return new ModelAndView("redirect:/agendamentos/novo");    	
    }

}
