package br.edu.ifpb.projeto.vacinacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.CalendarioVacinacao;
import br.edu.ifpb.projeto.vacinacao.service.CalendarioVacinacaoService;
import br.edu.ifpb.projeto.vacinacao.service.VacinacaoService;

@RestController
@RequestMapping({"/calendario-vacinacao"})
public class CalendarioVacinacaoController {

	@Autowired
	private CalendarioVacinacaoService calendarioService;
	
	@Autowired
	private VacinacaoService vacinacaoService;

	
	@GetMapping
	public ResponseEntity<List<CalendarioVacinacao>> findAllCalendarios(){
		 List<CalendarioVacinacao> calendarioVacinacao = calendarioService.findAll();
		 return ResponseEntity.ok(calendarioVacinacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CalendarioVacinacao> findByCalendarioVacinacao(@PathVariable("id") long id) {
		CalendarioVacinacao calendarioVacinacao = calendarioService.findById(id);
		return ResponseEntity.ok(calendarioVacinacao);
	
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<CalendarioVacinacao> deleteCalendarioVacinacao(@PathVariable("id") long id) {
		if(calendarioService.findById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		calendarioService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CalendarioVacinacao> createCalendarioVacinacao(/*@Valid*/ @RequestBody CalendarioVacinacao calendarioVacinacao) {
			calendarioService.saveCalendarioVacinacao(calendarioVacinacao);
			
			Long idCalendario = calendarioService.findByMaxId();
			CalendarioVacinacao calendario = calendarioService.findById(idCalendario);
			vacinacaoService.saveVacinacao(calendario);
			
			return ResponseEntity.ok(calendarioVacinacao);
	}
	
	@PostMapping("editar/{id}")
	public ResponseEntity<CalendarioVacinacao> updateCalendarioVacinacao(/*@Valid*/ @PathVariable("id") long id, @RequestBody CalendarioVacinacao calendarioVacinacaoRequest) {
		Optional<CalendarioVacinacao> calendarioVacinacaoUp = calendarioService.updateCalendarioVacinacao(id, calendarioVacinacaoRequest);
		 if(calendarioVacinacaoUp.isPresent()) {
			 CalendarioVacinacao calendario = calendarioVacinacaoUp.get();
			 return ResponseEntity.ok(calendario);
		 }
		
		 return ResponseEntity.notFound().build();
	}

}
