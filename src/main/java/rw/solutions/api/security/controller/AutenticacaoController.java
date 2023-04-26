package rw.solutions.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import rw.solutions.api.security.model.Usuario;
import rw.solutions.api.security.model.record.DadosLogin;
import rw.solutions.api.security.model.record.DadosTokenJWT;
import rw.solutions.api.security.service.TokenService;

@Tag(name = "Autenticação", description = "Método para se autenticar na API")
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("login")
	public ResponseEntity login(@RequestBody @Valid DadosLogin login) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
		Authentication authenticate = manager.authenticate(authenticationToken);
		
		String token = this.tokenService.gerarToken((Usuario) authenticate.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(token, "Bearer"));
	}

}
