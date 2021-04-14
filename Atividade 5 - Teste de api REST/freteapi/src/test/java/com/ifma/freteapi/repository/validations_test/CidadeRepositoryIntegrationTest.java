package com.ifma.freteapi.repository.validations_test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.ifma.freteapi.builder.CidadeBuilder;
import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.repository.CidadeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CidadeRepositoryIntegrationTest {
    @Autowired
    private CidadeRepository cidadeRepository;

    private CidadeBuilder cidadeBuilder;

    @BeforeEach
    public void start() {
        cidadeBuilder = CidadeBuilder.umaCidade();
    }

    @Test
    public void saveComNomeNuloLancarException() throws Exception {
        Cidade cidade = cidadeBuilder.comNomeNulo().constroi();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
				() -> {  
			       	     cidadeRepository.save(cidade);
					  },
				"Deveria lançar um ConstraintViolationException");
		Assertions.assertTrue(exception.getMessage().contains("Nome da cidade não deve ser nulo"));
    }

    @Test
	public void deveSalvarUmaNovaCidade() {

		cidadeRepository.save(cidadeBuilder.constroi());

		List<Cidade> contatos = cidadeRepository.findAll();
		Assertions.assertEquals(1, contatos.size() );

		cidadeRepository.deleteAll();
	}
   
}
