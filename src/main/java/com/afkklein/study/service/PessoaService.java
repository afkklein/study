package com.afkklein.study.service;

import com.afkklein.study.model.Pessoa;
import com.afkklein.study.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloId(id);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        return pessoaRepository.save(pessoaSalva);
    }

    public ResponseEntity<?> deletar(Long id) {
        Pessoa pessoaSalva = buscarPessoaPeloId(id);
        pessoaRepository.delete(pessoaSalva);
        return ResponseEntity.ok().build();
    }

    public Pessoa buscarPessoaPeloId(Long id) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
        if (pessoaSalva.isEmpty())
            throw new EmptyResultDataAccessException(1);
        return pessoaSalva.get();
    }

}
