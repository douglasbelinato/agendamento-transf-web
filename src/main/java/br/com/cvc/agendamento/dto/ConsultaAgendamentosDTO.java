package br.com.cvc.agendamento.dto;

import br.com.cvc.agendamento.model.Agendamento;

import java.io.Serializable;
import java.util.List;

public class ConsultaAgendamentosDTO implements Serializable {

    private List<Agendamento> agendamentos;
    private Integer pagina;
    private Integer qtdRegistrosPagina;
    private Integer colunaOrdenacao;

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getQtdRegistrosPagina() {
        return qtdRegistrosPagina;
    }

    public void setQtdRegistrosPagina(Integer qtdRegistrosPagina) {
        this.qtdRegistrosPagina = qtdRegistrosPagina;
    }

    public Integer getColunaOrdenacao() {
        return colunaOrdenacao;
    }

    public void setColunaOrdenacao(Integer colunaOrdenacao) {
        this.colunaOrdenacao = colunaOrdenacao;
    }

    @Override
    public String toString() {
        return "ConsultaAgendamentosDTO{" +
                "agendamentos=" + agendamentos +
                ", pagina=" + pagina +
                ", qtdRegistrosPagina=" + qtdRegistrosPagina +
                ", colunaOrdenacao=" + colunaOrdenacao +
                '}';
    }
}
