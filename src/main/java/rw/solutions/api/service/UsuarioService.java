package rw.solutions.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solutions.api.security.model.Usuario;
import rw.solutions.api.security.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario getUsuarioByUsername(String username) {
		return this.repository.findByUsername(username);
	}

}
