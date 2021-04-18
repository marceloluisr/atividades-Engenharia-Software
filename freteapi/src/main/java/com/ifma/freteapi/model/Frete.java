package com.ifma.freteapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@Entity
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private BigDecimal peso;
    private BigDecimal valor;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne 
    private Cidade cidade;

    public Frete() {
    }

    public Frete(String descricao, BigDecimal peso, BigDecimal valor,
            @NotEmpty(message = "O Cliente deve ser preenchido") Cliente cliente,
            @NotEmpty(message = "A Cidade deve ser preenchida") Cidade cidade) {
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
        this.cliente = cliente;
        this.cidade = cidade;
    }


    


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return this.descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPeso() {
        return this.peso;
    }


    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public BigDecimal calcularFrete() {
        // R$10,00 é o valor fixo para o cálculo
        this.valor = new BigDecimal(this.peso.doubleValue() * 10.0).add(this.cidade.getTaxa() );
        return this.getValor();

    }

}