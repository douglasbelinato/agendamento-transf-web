package br.com.cvc.agendamento.enums;

public enum TipoErroEnum {

	NEGOCIO(1, "NEGOCIO"), TECNICO(2, "TECNICO");

	private int codigo;
	private String descricao;
	
	private TipoErroEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
