package br.edu.ifpb.projeto.vacinacao.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.repository.VacinaRepository;

@Service
public class VacinaService {

		@Autowired
		private VacinaRepository vacinaRepository;

		@Autowired
		public VacinaService(VacinaRepository vacinaRepository) {
			super();
			this.vacinaRepository = vacinaRepository;
		}
		
		
		@Transactional
		public void saveVacina(Vacina vacina) {
			vacinaRepository.saveAndFlush(vacina);
		}
		
		public Vacina findById(Long id) {
			Optional<Vacina> opVacina = vacinaRepository.findById(id);
			return opVacina.isPresent() ? opVacina.get() : null; 
		}
		
		@Transactional
		public void deleteById(Long id) {
			vacinaRepository.deleteById(id);
		}
		
		public List<Vacina> findAll(){
			return vacinaRepository.findAll();
		}
		
		@Transactional
		public Optional<Vacina> updateVacina(Long id , Vacina vacina) {
		
			return vacinaRepository.findById(id)
					.map(record ->{
						record.setNomeVacina(vacina.getNomeVacina());
						record.setTotalDoses(vacina.getTotalDoses());
						record.setIntervalo(vacina.getIntervalo());
					
						return vacinaRepository.save(record);
					});
				
		}
		
		public int somaTotalDoses() {
			return vacinaRepository.somaTotalDoses();
		}
		
}
