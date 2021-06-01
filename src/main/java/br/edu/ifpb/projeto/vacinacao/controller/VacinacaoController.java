package br.edu.ifpb.projeto.vacinacao.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.model.Vacinacao;
import br.edu.ifpb.projeto.vacinacao.service.VacinacaoService;

@RestController
@RequestMapping({"/vacinacao"})
public class VacinacaoController {
	@Autowired
	private VacinacaoService vacinacaoService;

	
	@GetMapping
	public ResponseEntity<List<Vacinacao>> findAllVacinacoes(){
		 List<Vacinacao> vacinacoes = vacinacaoService.findAll();
		 return ResponseEntity.ok(vacinacoes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vacinacao> findByIdVacinacao(@PathVariable("id") long id) {
		Vacinacao vacinacao = vacinacaoService.findById(id);
		return ResponseEntity.ok(vacinacao);
	
	}

	
	@GetMapping("/relatorioParaVacinar")
	public ResponseEntity<List<Vacinacao>> relatorioParaVacinar(@RequestBody Date dataVacinar){
		 List<Vacinacao> vacinacoes = vacinacaoService.relatorioParaVacinar(dataVacinar);
		 return ResponseEntity.ok(vacinacoes);
	}
	@GetMapping("/relatorioVacinados")
	public ResponseEntity<List<Vacinacao>> relatorioVacinados(@RequestBody Date dataVacinados){
		 List<Vacinacao> vacinacoes = vacinacaoService.relatorioVacinados(dataVacinados);
		 return ResponseEntity.ok(vacinacoes);
	}
	
	@PostMapping("editar/{id}")
	public ResponseEntity<Vacinacao> updateVacinacao(/*@Valid*/ @PathVariable("id") long id, @RequestBody Vacinacao vacinacaoRequest) {
		
		 Optional<Vacinacao> vacinacaoUp = vacinacaoService.updateVacina(id, vacinacaoRequest);
		 if(vacinacaoUp.isPresent()) {
			 Vacinacao vacinacao = vacinacaoUp.get();
			 return ResponseEntity.ok(vacinacao);
		 }
		
		 return ResponseEntity.notFound().build();
		 
		 
	}

}
