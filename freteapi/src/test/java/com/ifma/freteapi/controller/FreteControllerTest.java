package com.ifma.freteapi.controller;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import com.ifma.freteapi.builder.FreteBuilder;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.repository.FreteRepository;
import com.ifma.freteapi.service.exceptions.CidadeException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FreteControllerTest {
    @MockBean
    private FreteRepository freteRepository;

    @Autowired
    private FreteController freteController;


    @Test
    public void deveLancarCidadeExceptionQuandoCadastrarComCidadeNula() throws CidadeException {
        Mockito.when(freteRepository.save(Mockito.any()))
        .thenThrow(new ConstraintViolationException("A Cidade deve ser preenchida", null));


        CidadeException exception = Assertions.assertThrows(CidadeException.class
        , () -> {
            freteController.inserirFrete("Frete Sem Cidade", new BigDecimal("10.99"), null,
             new Cliente("Cliente do Frete Sem Cidade", 
             "Rua do Cliente do Frete Sem Cidade", 
             "00000000000000"), new BigDecimal("1000.0"));
        }, "Deveria lançar uma CidadeException");

    }

    
}
