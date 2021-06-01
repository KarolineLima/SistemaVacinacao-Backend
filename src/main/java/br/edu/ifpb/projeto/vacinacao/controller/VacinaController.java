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

import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.service.VacinaService;

@CrossOrigin
@RestController
@RequestMapping({"/vacinas"})
public class VacinaController {

	@Autowired
	private VacinaService vacinaService;

	
	@GetMapping
	public ResponseEntity<List<Vacina>> findAllVacina(){
		 List<Vacina> vacinas = vacinaService.findAll();
		 return ResponseEntity.ok(vacinas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vacina> findByIdVacina(@PathVariable("id") long id) {
		Vacina vacinas = vacinaService.findById(id);
		return ResponseEntity.ok(vacinas);
	
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Vacina> deleteVacina(@PathVariable("id") long id) {
		
		 if(vacinaService.findById(id) == null){
			 return ResponseEntity.notFound().build();	 
		 }
		 vacinaService.deleteById(id);
		 return ResponseEntity.noContent().build();
		 
	
	}



	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Vacina> createVacina(/*@Valid*/ @RequestBody Vacina vacinas) {
		 vacinaService.saveVacina(vacinas);
		return ResponseEntity.ok(vacinas);
	}
	
	@PostMapping("editar/{id}")
	public ResponseEntity<Vacina> updateVacina(/*@Valid*/ @PathVariable("id") long id, @RequestBody Vacina vacinaRequest) {
		
		 Optional<Vacina> vacinaUp = vacinaService.updateVacina(id, vacinaRequest);
		 if(vacinaUp.isPresent()) {
			 Vacina vacina = vacinaUp.get();
			 return ResponseEntity.ok(vacina);
		 }
		
		 return ResponseEntity.notFound().build();
		 
		 
	}

}
