package rw.solutions.api.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rw.solutions.api.security.model.Usuario;
import rw.solutions.api.security.service.TokenService;
import rw.solutions.api.service.UsuarioService;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String tokenJWT = recuperarTokenJWT(request);
		
		if(null != tokenJWT) {			
			String subject = this.tokenService.getSubject(tokenJWT);
			Usuario usuario = this.usuarioService.getUsuarioByUsername(subject);
			
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}
		
		filterChain.doFilter(request, response);
	}

	private String recuperarTokenJWT(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if(null != authorization) {
			return authorization.replace("Bearer ", "");
		}
		return null;
	}

}
