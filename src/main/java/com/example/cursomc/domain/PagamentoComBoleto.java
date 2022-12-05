package com.example.cursomc.domain;

import com.example.cursomc.domain.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento{
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataPagamento;

    public PagamentoComBoleto() {

    }

    public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
