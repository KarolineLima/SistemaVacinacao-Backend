package br.edu.ifpb.projeto.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.repository.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	public Usuario findByLogin(Usuario usuario) {
		Usuario user = usuarioRepository.findByLogin(usuario.getEmail());
		
		if(user != null) {
			if(usuario.getSenha().equals(user.getSenha())) {
				return user;
			}
		}
		
		return null;

	}
}
