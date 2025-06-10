package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.services.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService PessoaService) {
        this.pessoaService = PessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("Iniciando listagem de pessoas");
        var pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoas(@PathVariable Long id) {
        logger.info("Iniciando busca de pessoa pelo id");
        var pessoa = this.pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoas(@RequestBody Pessoa pessoa) {
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) {
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id) {
        this.pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}