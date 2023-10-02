package br.gov.sp.fatec.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.app.entity.Trabalho;
import br.gov.sp.fatec.app.service.TrabalhoService;

@RestController
@RequestMapping(value = "/trabalho")
@CrossOrigin
public class TrabalhoController {

    @Autowired
    private TrabalhoService service;
    
    @GetMapping
    public List<Trabalho> todosTrabalhos() {
        return service.buscarTodosTrabalhos();
    }

    @PostMapping
    public Trabalho novoTrabalho(@RequestBody Trabalho usuario) {
        return service.novoTrabalho(usuario);
    }

    @GetMapping(value = "/{id}")
    public Trabalho buscarPorId(@PathVariable("id") Long id) {
        return service.buscarTrabalhoPorId(id);
    }

    @GetMapping(value= "/{titulo}/{nota}")
    public Trabalho buscarPorTituloNota(@PathVariable("titulo") String titulo, @PathVariable("nota") Integer nota) {
        return service.buscarTrabalhoPorNomeNota(titulo, nota);
    }
    
}