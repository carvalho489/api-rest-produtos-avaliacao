package br.com.asciiej.produtosapi.services.exceptions;

public class CategoriaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
