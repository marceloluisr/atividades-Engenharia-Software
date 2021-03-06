package com.ifma.freteapi.controller;

import java.math.BigDecimal;

import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.model.Frete;
import com.ifma.freteapi.repository.filter.FreteFiltro;
import com.ifma.freteapi.service.FreteService;
import com.ifma.freteapi.service.exceptions.CidadeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fretes")
public class FreteController {

    //@Value("${paginacao.qtd_por_pagina}")
   // private Integer quantidadePorPagina;

    private final FreteService freteService;

    @Autowired
    public FreteController(FreteService freteService) {
        this.freteService = freteService;
    }


    @GetMapping
    public Page<Frete> busca(FreteFiltro filtro /*, Pageable page*/  ) {
         return freteService.busca(filtro /*, page*/ );

    }

    public void inserirFrete(String descricao,
    BigDecimal peso,Cidade cidade, Cliente cliente, BigDecimal valor ) throws CidadeException {
        Frete frete = new Frete(descricao, peso, valor, cliente, cidade);
        freteService.salva(frete);

    }

    


}
