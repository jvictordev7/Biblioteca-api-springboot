package com.example.demo.controller;

import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroRepository repo;

    public LivroController(LivroRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        return ResponseEntity.ok(repo.save(livro));
    }

    @GetMapping
    public List<Livro> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro dados) {
        return repo.findById(id).map(existente -> {
            existente.setTitulo(dados.getTitulo());
            existente.setAutor(dados.getAutor());
            existente.setIsbn(dados.getIsbn());
            existente.setAnoPublicacao(dados.getAnoPublicacao());
            return ResponseEntity.ok(repo.save(existente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
