package com.ifma.freteapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message="Nome da cidade não deve ser nulo") @NotEmpty
    private String nome;
    @NotNull(message="UF da cidade não deve ser nulo") @NotEmpty
    private String uf;
    @NotNull(message="Taxa de frete da cidade não deve ser nulo") 
    private BigDecimal taxa;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return this.uf;
    }


    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getTaxa() {
        return this.taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Cidade(@NotNull(message = "Nome da cidade não deve ser nulo") @NotEmpty String nome,
            @NotNull(message = "UF da cidade não deve ser nulo") @NotEmpty String uf,
            @NotNull(message = "Taxa de frete da cidade não deve ser nulo") @NotEmpty BigDecimal taxa) {
        this.nome = nome;
        this.uf = uf;
        this.taxa = taxa;
    }

    

    public Cidade() {
    }
}