package br.gov.sp.fatec.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.app.entity.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    public Optional<Trabalho> findByTituloAndNotaGreaterThan(String titulo, Integer nota);

}
