package br.edu.ifpb.projeto.vacinacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.service.UsuarioService;


@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAllUsuarios(){
		 List<Usuario> usuarios = usuarioService.findAll();
		 return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable("id") long id) {
		Usuario usuario = usuarioService.findById(id);
		return ResponseEntity.ok(usuario);
	
	}
	
	@PostMapping("delete/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") long id) {
		
		if(usuarioService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> createUsuario(/*@Valid*/ @RequestBody Usuario usuario) {
			usuarioService.saveUsuario(usuario);
			return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("editar/{id}")
	public ResponseEntity<Usuario> updateUsuario(/*@Valid*/ @PathVariable("id") long id, @RequestBody Usuario usuarioRequest) {
		Optional<Usuario> usuarioUp = usuarioService.updateUsuario(id, usuarioRequest);
		 if(usuarioUp.isPresent()) {
			 Usuario usuario = usuarioUp.get();
			 return ResponseEntity.ok(usuario);
		 }
		
		 return ResponseEntity.notFound().build();
	}
	
}
