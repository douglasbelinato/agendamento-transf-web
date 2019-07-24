package br.com.cvc.agendamento.dto;

import java.io.Serializable;

public class ResponseErroDTO implements Serializable {

	private static final long serialVersionUID = 1097320553086552130L;

	private int httpStatusCode;

	private String httpStatusMessage;

	private String mensagem;

	private String tipoErro;

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpStatusMessage() {
		return httpStatusMessage;
	}

	public void setHttpStatusMessage(String httpStatusMessage) {
		this.httpStatusMessage = httpStatusMessage;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTipoErro() {
		return tipoErro;
	}

	public void setTipoErro(String tipoErro) {
		this.tipoErro = tipoErro;
	}

}
