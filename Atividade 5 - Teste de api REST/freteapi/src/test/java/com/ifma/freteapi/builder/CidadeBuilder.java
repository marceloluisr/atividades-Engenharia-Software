package com.ifma.freteapi.builder;

import java.math.BigDecimal;

import com.ifma.freteapi.model.Cidade;

public class CidadeBuilder {
    private Cidade cidade;

    public CidadeBuilder() {}
    
    public Cidade constroi() {
        return this.cidade;
    }

    public static CidadeBuilder umaCidade() {
        CidadeBuilder cidadeBuilder = new CidadeBuilder();
        cidadeBuilder.cidade = new Cidade();
        cidadeBuilder.cidade.setNome("Cidade Tester");
        cidadeBuilder.cidade.setTaxa(new BigDecimal("10.90"));
        cidadeBuilder.cidade.setUf("MA");

        return cidadeBuilder;
    }

    public CidadeBuilder comNomeNulo() {
        this.cidade.setNome(null);
        return this;
    }



}
