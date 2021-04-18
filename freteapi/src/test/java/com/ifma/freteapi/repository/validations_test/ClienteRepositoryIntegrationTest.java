package com.ifma.freteapi.repository.validations_test;

import javax.validation.ConstraintViolationException;

import com.ifma.freteapi.builder.ClienteBuilder;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryIntegrationTest {
    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteBuilder clienteBuilder;

    @BeforeEach
    public void start() {
        clienteBuilder = ClienteBuilder.umCliente();
    }
    @Test
    public void saveComNomeNuloLancarException() throws Exception {
        Cliente cliente = clienteBuilder.comNomeNulo().constroi();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
				() -> {  
			       	     clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");
		  Assertions.assertTrue(exception.getMessage().contains("Nome não deve ser nulo"));
    }

    @Test
    public void saveComTelefoneInvalidoLancarException() throws Exception {
        Cliente cliente = clienteBuilder.comTelefoneCurto().constroi();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
				() -> {  
			       	     clienteRepository.save(cliente);
					  },
				"Deveria lançar um ConstraintViolationException");
		    Assertions.assertTrue(exception.getMessage().contains("Deve possuir 8 dígitos no mínimo"));
    }



    
}
