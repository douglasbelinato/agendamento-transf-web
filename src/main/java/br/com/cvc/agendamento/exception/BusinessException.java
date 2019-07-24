package br.com.cvc.agendamento.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 8808771801086888893L;

	public BusinessException(String msg) {
		super(msg);
	}

}
