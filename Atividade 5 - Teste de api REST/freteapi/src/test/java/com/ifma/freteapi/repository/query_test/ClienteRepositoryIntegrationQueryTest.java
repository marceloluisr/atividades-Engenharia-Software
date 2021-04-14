package com.ifma.freteapi.repository.query_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.ifma.freteapi.builder.ClienteBuilder;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.repository.ClienteRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryIntegrationQueryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteBuilder ClienteBuilder;

    @BeforeEach
    public void before() {
        Cliente cliente1 = ClienteBuilder
                                    .umCliente()
                                    .comNome("Cliente1")
                                    .comTelefone("111111111")
                                    .comEndereco("Endereco de Cliente1")
                                    .constroi();
        clienteRepository.save(cliente1);
        Cliente cliente2 = ClienteBuilder
                                    .umCliente()
                                    .comNome("Cliente2")
                                    .comTelefone("222222222")
                                    .comEndereco("Endereco de Cliente2")
                                    .constroi();
        clienteRepository.save(cliente2);
        Cliente cliente3 = ClienteBuilder
                                    .umCliente()
                                    .comNome("Cliente3")
                                    .comTelefone("333333333")
                                    .comEndereco("Endereco de Cliente3")
                                    .constroi();
        clienteRepository.save(cliente3);
        Cliente cliente4 = ClienteBuilder
                                    .umCliente()
                                    .comNome("Cliente4")
                                    .comTelefone("444444444")
                                    .comEndereco("Endereco de Cliente4")
                                    .constroi();
        clienteRepository.save(cliente4);
        
    }

    @AfterEach
    public void after() {
        clienteRepository.deleteAll();
    }

    @Test
    public void deveBuscarClientesPorNome() {
        List<Cliente> clientes = clienteRepository.findByNomeContaining("Cliente");
        
        assertEquals(clientes.size(), 4);
        
    }
    
}
