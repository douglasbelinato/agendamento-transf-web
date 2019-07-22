package br.com.cvc.agendamento.dto;

import java.io.Serializable;

public class FiltroConsultaAgendamentoDTO implements Serializable {

    private Long idUsuario;
    private Integer pagina;
    private Integer qtdRegistrosPagina;
    private Integer colunaOrdenacao;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
}
