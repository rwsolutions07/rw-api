package rw.solutions.api.util.execption;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionsUtil {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity error404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosErrosValidacao>> error400(MethodArgumentNotValidException ex) {
		List<FieldError> erros = ex.getFieldErrors();	
		List<DadosErrosValidacao> response = erros.stream().map(DadosErrosValidacao::new).toList();
		return ResponseEntity.badRequest().body(response);
	}
	
	private record DadosErrosValidacao(String campo, String mensagem) {
		public DadosErrosValidacao(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
	

}
