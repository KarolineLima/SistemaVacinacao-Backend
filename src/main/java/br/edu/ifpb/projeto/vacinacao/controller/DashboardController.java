package br.edu.ifpb.projeto.vacinacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.model.Vacinacao;
import br.edu.ifpb.projeto.vacinacao.service.UsuarioService;
import br.edu.ifpb.projeto.vacinacao.service.VacinaService;
import br.edu.ifpb.projeto.vacinacao.service.VacinacaoService;

@RestController
@RequestMapping({"/dashboard"})
public class DashboardController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VacinaService vacinaService;
	
	@Autowired
	private VacinacaoService vacinacaoService;
	
	@GetMapping("/usuarios")
	public int totalUsuarios() {
		List<Usuario> usuarios =  usuarioService.findAll();
		return usuarios.size();
	}
	
	@GetMapping("/vacinas")
	public int totalVacinas() {
		List<Vacina> vacinas =  vacinaService.findAll();
		return vacinas.size();
	}
	
	@GetMapping("/vacinacoes")
	public int totalVacinacoes() {
		List<Vacinacao> vacinacoes =  vacinacaoService.findAll();
		return vacinacoes.size();
	}

}
