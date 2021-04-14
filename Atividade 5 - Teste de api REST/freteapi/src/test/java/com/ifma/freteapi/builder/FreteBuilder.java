package com.ifma.freteapi.builder;

import java.math.BigDecimal;

import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.model.Frete;

public class FreteBuilder {
    private Frete frete;
   
    
    public FreteBuilder() {}

    public Frete constroi() {
        return this.frete;
    }

    public static FreteBuilder umFrete() {
        FreteBuilder freteBuilder = new FreteBuilder();
        freteBuilder.frete = new Frete();
        freteBuilder.frete.setDescricao("Frete do Fulano");
        freteBuilder.frete.setPeso(new BigDecimal("10.0"));
        freteBuilder.frete.setCidade(CidadeBuilder.umaCidade().constroi());
        freteBuilder.frete.setCliente(ClienteBuilder.umCliente().constroi());

        return freteBuilder;
    }
    
}
