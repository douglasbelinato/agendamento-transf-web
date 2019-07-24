package br.com.cvc.agendamento.dto;

import java.io.Serializable;
import java.util.List;

import br.com.cvc.agendamento.model.Agendamento;

public class ConsultaAgendamentosDTO implements Serializable {

	private static final long serialVersionUID = -3217586797484474902L;
	
	private List<Agendamento> agendamentos;

	public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
