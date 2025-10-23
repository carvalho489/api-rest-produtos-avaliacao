package br.com.asciiej.produtosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.asciiej.produtosapi.domain.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long> {}
