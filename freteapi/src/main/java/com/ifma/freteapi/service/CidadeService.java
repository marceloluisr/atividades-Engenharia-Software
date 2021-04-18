package com.ifma.freteapi.service;


import com.ifma.freteapi.model.Cidade;
import com.ifma.freteapi.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> todos() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscaPor(Integer id) {
        return cidadeRepository.findById(id);
    }

    public List<Cidade> buscaPor(String nome) {
        return cidadeRepository.findByNomeContaining(nome);
    }

    @Transactional
    public Cidade salva(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void removePelo(Integer id) {
        cidadeRepository.deleteById(id);
    }

}
