package com.ifma.freteapi.builder;

import com.ifma.freteapi.model.Cliente;

public class ClienteBuilder {
    private Cliente cliente;

    private ClienteBuilder() {};

    public Cliente constroi() {
        return this.cliente;
    }

    public static ClienteBuilder umCliente() {
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        clienteBuilder.cliente = new Cliente();
        clienteBuilder.cliente.setNome("Fulano");
        clienteBuilder.cliente.setEndereco("Rua doFulano");
        clienteBuilder.cliente.setTelefone("99999999999");

        return clienteBuilder;
    }

    public ClienteBuilder comNomeNulo() {
        this.cliente.setNome(null);
        return this;
    }

    public ClienteBuilder comTelefoneCurto() {
        this.cliente.setTelefone("999");
        return this;
    }

    public ClienteBuilder comNome(String nome) {
        this.cliente.setNome(nome);
        return this;
    }

    public ClienteBuilder comTelefone(String tel) {
        this.cliente.setTelefone(tel);
        return this;
    }

    public ClienteBuilder comEndereco(String endereco) {
        this.cliente.setEndereco(endereco);
        return this;
    }

    public ClienteBuilder naoCadastrada() {
        this.cliente.setEndereco("Rua Cliente não Cadastrado");
        this.cliente.setNome("Nome Cliente não Cadastrado");
        this.cliente.setTelefone("000000000000010");
        return this;
    }



    
    
}
