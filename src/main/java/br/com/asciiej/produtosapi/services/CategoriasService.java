package br.com.asciiej.produtosapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asciiej.produtosapi.domain.Categoria;
import br.com.asciiej.produtosapi.repository.CategoriasRepository;
import br.com.asciiej.produtosapi.services.exceptions.CategoriaExistenteException;
import br.com.asciiej.produtosapi.services.exceptions.CategoriaNaoEncontradaException;

@Service
public class CategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepository;

	public List<Categoria> listar() {
		return categoriasRepository.findAll();

	}
	
	public Categoria salvar(Categoria categoria) {
		if (categoria.getId() != null) {
			Categoria a = categoriasRepository.findById(categoria.getId()).orElse(null);
			
			if (a != null) {
				throw new CategoriaExistenteException("Categoria já existe.");
			}
		}
		
		return categoriasRepository.save(categoria);
	}
	
	public Categoria buscar(Long id) {
		Categoria categoria = categoriasRepository.findById(id).orElse(null);
		
		if (categoria == null) {
			throw new CategoriaNaoEncontradaException("Categoria não encontrada.");
		}
		
		return categoria;
	}
}
