package br.com.cvc.agendamento.exception;

public class TechnicalException extends RuntimeException {
	
	private static final long serialVersionUID = 9000197458765452792L;

	public TechnicalException(String msg) {
		super(msg);
	}

}
