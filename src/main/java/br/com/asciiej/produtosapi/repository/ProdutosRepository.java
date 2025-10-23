package br.com.asciiej.produtosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.asciiej.produtosapi.domain.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {}
