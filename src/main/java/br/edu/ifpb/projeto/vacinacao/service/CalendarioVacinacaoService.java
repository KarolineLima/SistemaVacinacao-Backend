package br.edu.ifpb.projeto.vacinacao.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.projeto.vacinacao.model.CalendarioVacinacao;
import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.repository.CalendarioVacinacaoRepository;

@Service
public class CalendarioVacinacaoService {
	
	@Autowired
	private CalendarioVacinacaoRepository calendarioRepository;

	@Autowired
	public CalendarioVacinacaoService(CalendarioVacinacaoRepository calendarioRepository) {
		super();
		this.calendarioRepository = calendarioRepository;
	}
	
	@Transactional
	public void saveCalendarioVacinacao(CalendarioVacinacao calendarioVacinacao) {
		calendarioRepository.saveAndFlush(calendarioVacinacao);
	}
	
	public CalendarioVacinacao findById(Long id) {
		Optional<CalendarioVacinacao> opCalendario = calendarioRepository.findById(id);
		return opCalendario.isPresent() ? opCalendario.get() : null; 
	}
	
	@Transactional
	public void deleteById(Long id) {
		calendarioRepository.deleteById(id);
	}
	
	public List<CalendarioVacinacao> findAll(){
		return calendarioRepository.findAll();
	}
	
	@Transactional
	public Optional<CalendarioVacinacao> updateCalendarioVacinacao(Long id , CalendarioVacinacao calendarioVacinacao) {
		
		return calendarioRepository.findById(id)
				.map(record ->{
					record.setDataInicio(calendarioVacinacao.getDataInicio());
					record.setDataFinal(calendarioVacinacao.getDataFinal());
					record.setFaixaEtariaInicial(calendarioVacinacao.getFaixaEtariaInicial());
					record.setFaixaEtariaFinal(calendarioVacinacao.getFaixaEtariaFinal());
					record.setObsoleto(calendarioVacinacao.isObsoleto());
				
					return calendarioRepository.save(record);
				});
			
	}

	public Long findByMaxId() {
		return calendarioRepository.findByMaxId();
	}
}
