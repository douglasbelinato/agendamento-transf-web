package br.com.cvc.agendamento.dto;

import java.io.Serializable;

public class NovoAgendamentoDTO implements Serializable {

    private Long id;
    private Double taxa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return "NovoAgendamentoDTO{" +
                "id=" + id +
                ", taxa=" + taxa +
                '}';
    }
}
