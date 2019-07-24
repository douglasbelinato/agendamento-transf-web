package br.com.cvc.agendamento.service.impl;

import java.time.LocalDate;

import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.GsonBuilder;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.integration.utils.RestClientUtils;
import br.com.cvc.agendamento.service.AgendamentoService;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {
	
	@Value("${agendamento-transf-api.host}")
	private String host;
	
	@Value("${agendamento-transf-api.host.port}")
	private String serverPort;
	
	@Value("${agendamento-transf-api.resource.agendamentos}")
	private String resourceAgendamentos;
	
	@Autowired
	private RestClientUtils restClientUtils;
	
	@Autowired
	private GsonBuilder gsonBuilder;

    @Override
    public ConsultaAgendamentosDTO listar() throws OAuthSystemException, OAuthProblemException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(host + serverPort + resourceAgendamentos);
        
        OAuthResourceResponse oAuthResourceResponse = restClientUtils.get(uriBuilder);
        
        if (oAuthResourceResponse.getResponseCode() == 200) {
        	return gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), ConsultaAgendamentosDTO.class);
        }
        
        // TODO 
        return null;
    }

    @Override
    public NovoAgendamentoDTO inserir(AgendamentoDTO dto) throws OAuthSystemException, OAuthProblemException {
    	dto.setDataAgendamento(LocalDate.now());
    	
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(host + serverPort + resourceAgendamentos);
    	
    	OAuthResourceResponse oAuthResourceResponse = restClientUtils.post(uriBuilder, gsonBuilder.create().toJson(dto));

    	if (oAuthResourceResponse.getResponseCode() == 200) {
        	return gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), NovoAgendamentoDTO.class);
        }
        
        // TODO 
        return null;
        
    }
    
}
