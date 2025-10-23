package br.com.asciiej.produtosapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.asciiej.produtosapi.domain.Produto;
import br.com.asciiej.produtosapi.repository.ProdutosRepository;
import br.com.asciiej.produtosapi.services.exceptions.ProdutoNaoEncontradoException;

@Service
public class ProdutosService {

	@Autowired
	private ProdutosRepository produtosRepository;

	public List<Produto> listar() {
		return produtosRepository.findAll();
	}

	public Produto buscar(Long id) {
		Produto produto = produtosRepository.findById(id).orElse(null);

		if (produto == null) {
			throw new ProdutoNaoEncontradoException("O produto não pôde ser encontrado.");
		}
		
		return produto;
	}
	
	public Produto salvar(Produto produto) {
		produto.setId(null);
		return produtosRepository.save(produto);
	}
	
	public void deletar(Long id) {
		try {
			produtosRepository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException("O produto não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Produto produto) {
		verificarExistencia(produto);
		produtosRepository.save(produto);
	}
	
	private void verificarExistencia(Produto produto) {
		buscar(produto.getId());
	}
}






