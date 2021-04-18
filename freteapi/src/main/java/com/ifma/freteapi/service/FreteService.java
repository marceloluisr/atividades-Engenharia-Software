package com.ifma.freteapi.service;

import java.util.List;
import java.util.Optional;

import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.model.Cliente;
import com.ifma.freteapi.model.Frete;
import com.ifma.freteapi.repository.CidadeRepository;
import com.ifma.freteapi.repository.ClienteRepository;
import com.ifma.freteapi.repository.FreteRepository;
import com.ifma.freteapi.repository.filter.FreteFiltro;
import com.ifma.freteapi.service.exceptions.CidadeException;
import com.ifma.freteapi.service.exceptions.CidadeRuntimeException;
import com.ifma.freteapi.service.exceptions.ClienteRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FreteService {

    private final FreteRepository freteRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }

    @Transactional
    public Frete salva(Frete frete) {
        this.validarNulidadeDeCamposClienteECidadeDeFrete(frete);
                // clienteRepository.findByNomeContaining(frete.getCliente().getNome());
        //Optional<Cidade> cidade = cidadeRepository.findById(frete.getCidade().getId());
        List<Cidade> cidades = cidadeRepository.findByNomeContaining(frete.getCidade().getNome());
        List<Cliente> clientes = clienteRepository.findByNomeContaining(frete.getCliente().getNome());
        
        
        if (cidades.size() == 0) {
            throw new CidadeRuntimeException("Cidade não cadastrada");
        } else if(clientes.size() == 0) {
            throw new ClienteRuntimeException("Cliente não cadastrada");
        }
        return freteRepository.save(frete);
         
        // clienteRepository.findByNomeContaining(frete.getCliente().getNome());


    }

    @Transactional(readOnly = true)
    public Page<Frete> busca(FreteFiltro filtro /*, Pageable page*/) {
        return freteRepository.filtra(filtro /*, page*/ );
    }


    private void validarNulidadeDeCamposClienteECidadeDeFrete(Frete frete) {
        if (frete.getCliente() == null && frete.getCidade() == null) {
            throw new IllegalArgumentException("Cidade ou Cliente não podem ser nulos");
        } 
        else if ( frete.getCliente() == null ) {
            throw new ClienteRuntimeException("Cliente não pode ser nulo");
        } 
        else if (  frete.getCidade() == null ){
            throw new CidadeRuntimeException("Cidade não pode ser nulo");
        }
    }
}
