package br.com.asciiej.produtosapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.asciiej.produtosapi.domain.Categoria;
import br.com.asciiej.produtosapi.services.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasResources {

	@Autowired
	private CategoriasService categoriasService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(categoriasService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Categoria categoria) {
		categoria = categoriasService.salvar(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
		
		Categoria categoria = categoriasService.buscar(id);

		return ResponseEntity.status(HttpStatus.OK).body(categoria);
	}
}
