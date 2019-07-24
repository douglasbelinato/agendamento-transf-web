package br.com.cvc.agendamento.service;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;

public interface AgendamentoService {

    ConsultaAgendamentosDTO listar() throws OAuthSystemException, OAuthProblemException;

    NovoAgendamentoDTO inserir(AgendamentoDTO dto) throws OAuthSystemException, OAuthProblemException;
}
