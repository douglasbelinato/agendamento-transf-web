package br.com.cvc.agendamento.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendamentoDTO implements Serializable {

	private static final long serialVersionUID = -8432047026593989800L;

	@NotNull(message = "Conta origem é obrigatório")
    private Integer contaOrigem;

	@NotNull(message = "Conta destino é obrigatório")
    private Integer contaDestino;

	@NumberFormat
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que R$ 0,01")
	@DecimalMax(value= "9999999.99", message = "O valor da transferência deve ser menor que R$ 999.999,99")
    private Double valor;

	@NotNull(message = "Data da transferência é obrigatório")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataTransferencia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAgendamento;

    private Long idUsuario;

    public Integer getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Integer contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
