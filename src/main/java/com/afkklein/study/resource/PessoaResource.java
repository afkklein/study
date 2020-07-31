package com.afkklein.study.resource;

import com.afkklein.study.model.Pessoa;
import com.afkklein.study.repository.PessoaRepository;
import com.afkklein.study.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getPessoas() {
        return this.pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPeloId(id);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> newPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaRepository.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePessoa(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

}
