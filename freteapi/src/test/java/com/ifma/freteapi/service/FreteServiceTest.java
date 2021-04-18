package com.ifma.freteapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import com.ifma.freteapi.builder.CidadeBuilder;
import com.ifma.freteapi.builder.ClienteBuilder;
import com.ifma.freteapi.builder.FreteBuilder;
import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.model.Frete;
import com.ifma.freteapi.repository.FreteRepository;
import com.ifma.freteapi.service.exceptions.CidadeException;
import com.ifma.freteapi.service.exceptions.CidadeRuntimeException;
import com.ifma.freteapi.service.exceptions.ClienteRuntimeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FreteServiceTest {
    @Autowired
    private FreteService freteService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ClienteService clienteService;

    @Autowired 
    private FreteRepository freteRepository;

    private FreteBuilder freteBuilder;
    private CidadeBuilder cidadeBuilder;
    private ClienteBuilder clienteBuilder;


    @BeforeEach
    public void before() {
        cidadeBuilder = CidadeBuilder.umaCidade();
        clienteBuilder = ClienteBuilder.umCliente();
        freteBuilder = FreteBuilder.umFrete();

    }

    @Test
    public void inserirComCidadeNaoCadastradaDeveLancarExcecao() {
        // Cenário
        Cidade cidade = cidadeBuilder.constroi();
        Cliente cliente = clienteBuilder.constroi();
        
        Assertions.assertNotNull(cidadeService.salva(cidade));
        Assertions.assertNotNull(cidadeService.buscaPor(cidade.getNome()));
        
        Assertions.assertNotNull(clienteService.salva(cliente));
        Assertions.assertNotNull(clienteService.buscaPor(cliente.getNome()));
        
        Frete frete = freteBuilder.constroi();
        // Execução e Teste
        Cidade cidadeNaoCadastrada = cidadeBuilder.naoCadastrada().constroi();
        
        frete.setCidade(cidadeNaoCadastrada);
        frete.setCliente(cliente);

        CidadeRuntimeException e = Assertions.assertThrows( CidadeRuntimeException.class, 
                                 () -> freteService.salva(frete), 
                                 "Deveria lançar um CidadeRuntimeException");
        assertTrue(e.getMessage().contains("Cidade não cadastrada"));
    }

    @Test
    public void inserirComClienteNaoCadastradoDeveLancarExcecao() {
        // Cenário
        Cidade cidade = cidadeBuilder.constroi();
        Cliente cliente = clienteBuilder.constroi();
        
        Assertions.assertNotNull(cidadeService.salva(cidade));
        Assertions.assertNotNull(cidadeService.buscaPor(cidade.getNome()));
        
        Assertions.assertNotNull(clienteService.salva(cliente));
        Assertions.assertNotNull(clienteService.buscaPor(cliente.getNome()));
        
        Frete frete = freteBuilder.constroi();
        
        Cliente clienteNaoCadastrado = clienteBuilder.naoCadastrada().constroi();
        
        frete.setCidade(cidade);
        frete.setCliente(clienteNaoCadastrado);
        // Execução e Teste
        ClienteRuntimeException e = Assertions.assertThrows( ClienteRuntimeException.class, 
                                 () -> freteService.salva(frete), 
                                 "Deveria lançar um ClienteRuntimeException");
        assertTrue(e.getMessage().contains("Cliente não cadastrada"));
    }

    @Test
    public void deveLancarExcecaoDeCidadeEClienteNaoCadastrados() {
        // Cenário
        Cidade cidade = cidadeBuilder.constroi();
        Cliente cliente = clienteBuilder.constroi();
        
        Assertions.assertNotNull(cidadeService.salva(cidade));
        Assertions.assertNotNull(cidadeService.buscaPor(cidade.getNome()));
        
        Assertions.assertNotNull(clienteService.salva(cliente));
        Assertions.assertNotNull(clienteService.buscaPor(cliente.getNome()));
        
        // Execução 1º
        Frete frete = freteBuilder.constroi();
       
        frete.setCidade(cidade);
        frete.setCliente(cliente);
        
        // Teste
        Assertions.assertDoesNotThrow( () -> freteService.salva(frete));
        
        // Execução 2º - Cidade não cadastrada
        frete.setCidade(null);
        frete.setCliente(cliente);
        Assertions.assertThrows( CidadeRuntimeException.class, 
                                 () -> freteService.salva(frete), 
                                 "Deveria lançar um CidadeRuntimeException");
        
        // TODO: Assert Equals a mensagem da exceção
        //        assertTrue(exception.getMessage().contains("A Cidade deve ser preenchida"));

        // Execução 4º - Cliente não cadastrado
        frete.setCidade(cidade);
        frete.setCliente(null);
        Assertions.assertThrows( ClienteRuntimeException.class, 
                                 () -> freteService.salva(frete),
                                 "Deveria lançar um ClienteRuntimeException");
        
        // Execução 3º - Cliente e Cidade não cadastrados
        frete.setCidade(null);
        frete.setCliente(null);
        Assertions.assertThrows( IllegalArgumentException.class, 
                                 () -> freteService.salva(frete),
                                 "Deveria lançar um IlegalArgumentException");
    } 

    




}
