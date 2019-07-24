package br.com.cvc.agendamento.service.impl;

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
import br.com.cvc.agendamento.dto.ResponseErroDTO;
import br.com.cvc.agendamento.enums.TipoErroEnum;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.exception.TechnicalException;
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

	/**
     * Lista todos os agendamentos de transferências cadastrados.
     * 
     */
    @Override
    public ConsultaAgendamentosDTO listar() throws OAuthSystemException, OAuthProblemException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(host + serverPort + resourceAgendamentos);
        
        OAuthResourceResponse oAuthResourceResponse = restClientUtils.get(uriBuilder);
        
        if (oAuthResourceResponse.getResponseCode() >= 200 && oAuthResourceResponse.getResponseCode() < 300) {
        	return gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), ConsultaAgendamentosDTO.class);
        } else {
        	ResponseErroDTO responseErroDTO = gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), ResponseErroDTO.class);
        	
        	if (responseErroDTO.getTipoErro().equalsIgnoreCase(TipoErroEnum.NEGOCIO.getDescricao())) {
        		throw new BusinessException(responseErroDTO.getMensagem());
        	} else {
        		throw new TechnicalException(responseErroDTO.getMensagem());
        	}
        }
    }

    
    /**
     * Grava um novo agendamento de transferência na base de dados.
     * 
     */
    @Override
    public NovoAgendamentoDTO inserir(AgendamentoDTO dto) throws OAuthSystemException, OAuthProblemException {
    	UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(host + serverPort + resourceAgendamentos);
    	
    	OAuthResourceResponse oAuthResourceResponse = restClientUtils.post(uriBuilder, gsonBuilder.create().toJson(dto));

    	if (oAuthResourceResponse.getResponseCode() >= 200 && oAuthResourceResponse.getResponseCode() < 300) {
        	return gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), NovoAgendamentoDTO.class);
    	} else {
        	ResponseErroDTO responseErroDTO = gsonBuilder.create().fromJson(oAuthResourceResponse.getBody(), ResponseErroDTO.class);
        	
        	if (responseErroDTO.getTipoErro().equalsIgnoreCase(TipoErroEnum.NEGOCIO.getDescricao())) {
        		throw new BusinessException(responseErroDTO.getMensagem());
        	} else {
        		throw new TechnicalException(responseErroDTO.getMensagem());
        	}
        }
    }
}
