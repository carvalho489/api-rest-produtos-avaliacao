package br.com.asciiej.produtosapi.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.asciiej.produtosapi.domain.Produto;
import br.com.asciiej.produtosapi.services.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosResources {

	@Autowired
	private ProdutosService produtosService;

	@CrossOrigin
	@RequestMapping(
			method = RequestMethod.GET, 
			produces = {
					MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Produto>> listar() {
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produtosService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Produto produto) {
		
		produto = produtosService.salvar(produto);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		
		Produto produto = produtosService.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(80, TimeUnit.SECONDS);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(produto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

		produtosService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(
			@RequestBody Produto produto, 
			@PathVariable("id") Long id) {

		produto.setId(id);
		produtosService.atualizar(produto);

		return ResponseEntity.noContent().build();
	}
}














