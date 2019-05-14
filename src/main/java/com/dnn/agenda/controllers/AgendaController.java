package com.dnn.agenda.controllers;

import com.dnn.agenda.entidades.Agenda;
import com.dnn.agenda.repositories.AgendaRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    private AgendaRepository repository;

    public AgendaController(AgendaRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public List listarTodos() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> pegarPorId(@PathVariable long id) {
        return this.repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agenda criar(@RequestBody Agenda agenda) {
        return this.repository.save(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizarPorId(@PathVariable long id,
            @RequestBody Agenda agenda) {
        return this.repository.findById(id)
                .map(record -> {record.setDescricao(agenda.getDescricao());
                    Agenda updated = this.repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable long id) {
        return this.repository.findById(id)
                .map(record -> { this.repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
