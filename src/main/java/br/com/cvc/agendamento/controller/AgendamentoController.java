package br.com.cvc.agendamento.controller;

import javax.validation.Valid;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.exception.TechnicalException;
import br.com.cvc.agendamento.service.AgendamentoService;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;
    
    @GetMapping(value = "/paginaNaoEncontrada")
    public ModelAndView paginaNaoEncontrada() {
    	return new ModelAndView("agendamentos/pagina-nao-encontrada");
    }
    
    @GetMapping(value = "/agendamentos")
    public ModelAndView listar() throws OAuthSystemException, OAuthProblemException {
        ModelAndView mv = new ModelAndView("agendamentos/lista-agendamentos");

        try {
	        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar();
	        mv.addObject("consultaAgendamentosDTO", consultaAgendamentosDTO);
        } catch(BusinessException | TechnicalException e) {
    		mv.addObject("mensagemIntegracao", e.getMessage());
    	}
        
        return mv;
    }
    
    @GetMapping(value = "/agendamentos/novo")
    public ModelAndView novo(AgendamentoDTO dto) throws Exception {
    	return new ModelAndView("agendamentos/cadastro-agendamento");
    }
    
    @PostMapping(value = "/agendamentos/novo")
    public ModelAndView salvar(@Valid AgendamentoDTO dto, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
    	if (result.hasErrors()) {
    		return novo(dto);
    	}
    	
    	try {
    		agendamentoService.inserir(dto);
    	} catch(BusinessException | TechnicalException e) {
    		ModelAndView mv = new ModelAndView("agendamentos/cadastro-agendamento");
    		mv.addObject("agendamentoDTO", dto);
    		mv.addObject("mensagemIntegracao", e.getMessage());
    		return mv;
    	}
    	
    	redirectAttributes.addFlashAttribute("mensagem", "Agendamento de transferência salvo com sucesso!");
		return new ModelAndView("redirect:/agendamentos/novo");    	
    }

}
