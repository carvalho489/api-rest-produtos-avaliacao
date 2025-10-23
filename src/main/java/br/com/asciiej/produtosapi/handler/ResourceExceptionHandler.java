package br.com.asciiej.produtosapi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.asciiej.produtosapi.domain.DetalhesErro;
import br.com.asciiej.produtosapi.services.exceptions.CategoriaExistenteException;
import br.com.asciiej.produtosapi.services.exceptions.CategoriaNaoEncontradaException;
import br.com.asciiej.produtosapi.services.exceptions.ProdutoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ProdutoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleProdutoNaoEncontradoException(
			ProdutoNaoEncontradoException e,
			HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Produto não encontrado");
		erro.setMensagemDesenvolvedor("http://erros.produtosapi.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(CategoriaExistenteException.class)
	public ResponseEntity<DetalhesErro> handleCategoriaExistenteException(
			CategoriaExistenteException e,
			HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Categoria já existente!");
		erro.setMensagemDesenvolvedor("http://erros.produtosapi.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleCategoriaNaoEncontradoException
							(CategoriaNaoEncontradaException e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Categoria não encontrada!");
		erro.setMensagemDesenvolvedor("http://erros.produtosapi.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida devido violação de integridade referencial");
		erro.setMensagemDesenvolvedor("http://erros.produtosapi.com/400");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
