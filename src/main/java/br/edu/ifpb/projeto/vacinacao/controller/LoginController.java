package br.edu.ifpb.projeto.vacinacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.service.LoginService;

@RestController
@RequestMapping({"/login"})
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		Usuario user = loginService.findByLogin(usuario);
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.noContent().build();
	}
}
