package br.gov.sp.fatec.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.app.entity.Trabalho;
import br.gov.sp.fatec.app.repository.TrabalhoRepository;

@Service
public class TrabalhoService {

    @Autowired
    private TrabalhoRepository trabalhoRepository;

    public Trabalho novoTrabalho(Trabalho trabalho) {
            if(trabalho == null ||
                    trabalho.getTitulo() == null ||
                    trabalho.getTitulo().isBlank() ||
                    trabalho.getDataHoraEntrega() == null ||
                    trabalho.getGrupo() == null || 
                    trabalho.getGrupo().isBlank()) {
                throw new IllegalArgumentException("Dados inválidos!");
            }

            try {
                trabalho = trabalhoRepository.save(trabalho);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao inserir trabalho!");
            }

            return trabalho;
    }

    public List<Trabalho> buscarTodosTrabalhos() {
        try {
            return trabalhoRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao listar os trabalhos!");
        }
    }

    public Trabalho buscarTrabalhoPorId(Long id) {     
        Optional<Trabalho> trabalhoOp = trabalhoRepository.findById(id);
        if (trabalhoOp.isEmpty()) {
            throw new IllegalArgumentException("Trabalho não encontrado.");
        }
        return trabalhoOp.get();
    }

    public Trabalho buscarTrabalhoPorNomeNota(String titulo, Integer nota) {
        if (titulo == "" || titulo.isEmpty()) {
            throw new IllegalArgumentException("Dados inválidos!");
        }
        Optional<Trabalho> trabalhoOp = trabalhoRepository.findByTituloAndNotaGreaterThan(titulo, nota);
        if (trabalhoOp.isEmpty()) {
            throw new IllegalArgumentException("Trabalho não encontrado.");
        } 
        return trabalhoOp.get();
    }

}
