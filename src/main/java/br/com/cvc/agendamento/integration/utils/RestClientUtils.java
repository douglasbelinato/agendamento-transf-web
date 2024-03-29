package br.com.cvc.agendamento.integration.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Componente utilitário para centralizar a responsabilidade
 * de chamadas REST.
 *
 */
@Component
public class RestClientUtils {
	
	private Logger log = LoggerFactory.getLogger(RestClientUtils.class);
	
	@Value("${agendamento-transf-api.oauth2.clientId}")
	private String clientId;
	
	@Value("${agendamento-transf-api.oauth2.clientSecret}")
	private String clientSecret;
	
	@Value("${agendamento-transf-api.host}")
	private String host;
	
	@Value("${agendamento-transf-api.host.port}")
	private String serverPort;
	
	@Value("${agendamento-transf-api.oauth2.tokenUri}")
	private String tokenUrl;
	
	private static final String APPLICATION_JSON = "application/json";
	
	public OAuthResourceResponse get(UriComponentsBuilder uriBuilder) throws OAuthSystemException, OAuthProblemException {
		String uri = uriBuilder.toUriString();
		log.info("Realizando chamada GET para url: {}", uri);
		
		String token = getAccessToken();
    	OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(uri)
    	         .setAccessToken(token).buildQueryMessage();

    	OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    	return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
	}
	
	public OAuthResourceResponse post(UriComponentsBuilder uriBuilder, String jsonBody) throws OAuthSystemException, OAuthProblemException {
		String uri = uriBuilder.toUriString();
		log.info("Realizando chamada POST para url: {}", uri);
		String token = getAccessToken();
    	
    	OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    	OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(uri)
    			.setAccessToken(token).buildQueryMessage();
    	
    	bearerClientRequest.setBody(jsonBody);
    	bearerClientRequest.setHeader("Content-Type", APPLICATION_JSON);
    	
	   	return oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
	}
	
	private String getAccessToken() throws OAuthSystemException, OAuthProblemException {
		log.info("Solicitando o access token do oAuth2");
		
		OAuthClientRequest request = OAuthClientRequest
	            .tokenLocation(host + serverPort + tokenUrl)
	            .setGrantType(GrantType.CLIENT_CREDENTIALS)
	            .buildQueryMessage();
		
		request.addHeader("Accept", APPLICATION_JSON);
        request.addHeader("Content-Type", APPLICATION_JSON);
        request.addHeader("Authorization", gerarBasicAuthorizationHeader());
        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
    	
    	return oAuthClient.accessToken(request, OAuthJSONAccessTokenResponse.class).getAccessToken();
	}
	
	private String gerarBasicAuthorizationHeader() {
		log.info("Iniciando a geraçao do Basic Authorization Header");
		byte[] bytes = (clientId).concat(":").concat(clientSecret).getBytes(StandardCharsets.UTF_8);
		return "Basic ".concat(Base64.getEncoder().encodeToString(bytes));
	}

}
