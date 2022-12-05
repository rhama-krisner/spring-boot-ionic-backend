package com.example.cursomc.resources;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;
    /*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Cliente obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

}
