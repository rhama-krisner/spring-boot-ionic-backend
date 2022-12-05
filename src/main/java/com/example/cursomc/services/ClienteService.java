package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Long id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
    }
}
