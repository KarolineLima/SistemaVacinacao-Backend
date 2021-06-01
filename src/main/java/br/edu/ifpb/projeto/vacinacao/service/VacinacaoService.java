package br.edu.ifpb.projeto.vacinacao.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.projeto.vacinacao.email.NotificationService;
import br.edu.ifpb.projeto.vacinacao.model.CalendarioVacinacao;
import br.edu.ifpb.projeto.vacinacao.model.Usuario;
import br.edu.ifpb.projeto.vacinacao.model.Vacina;
import br.edu.ifpb.projeto.vacinacao.model.Vacinacao;
import br.edu.ifpb.projeto.vacinacao.repository.UsuarioRepository;
import br.edu.ifpb.projeto.vacinacao.repository.VacinacaoRepository;

@Service
public class VacinacaoService {

	@Autowired
	private VacinacaoRepository vacinacaoRepository;

	@Autowired
	private VacinaService vacinaService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private NotificationService notificationService;
	

	@Autowired
	public VacinacaoService(VacinacaoRepository vacinacaoRepository) {
		super();
		this.vacinacaoRepository = vacinacaoRepository;
	}

	@Transactional
	public void saveVacinacao(CalendarioVacinacao calendario) {
		
		List<Vacina> vacinas = vacinaService.findAll();
		List<Usuario> usuarios = usuarioService.findAll();
		int somaTotalDoses = vacinaService.somaTotalDoses();
			
		if(usuarios.size() <= somaTotalDoses ) {
		
			LocalDate dataInicial = calendario.getDataInicio();
			LocalDate dataFinal = calendario.getDataFinal();
			int FaixaEtariaInicial = calendario.getFaixaEtariaInicial();
			int FaixaEtariaFinal = calendario.getFaixaEtariaFinal();

			long periodoCalendario = ChronoUnit.DAYS.between(dataFinal, dataInicial);
			
			for (int v = 0; v < vacinas.size() ; v++) {
			
				Vacina vacina = vacinas.get(v);
				int totalDoses = vacina.getTotalDoses();
				long mediaDosesDiarias = totalDoses/periodoCalendario;
				
				
				
				for (int u = 0; u < totalDoses && usuarios.size() > u; u++) {
					Usuario usuario = usuarios.get(u);
					if ( usuario.getIdade() <= FaixaEtariaInicial && usuario.getIdade() >= FaixaEtariaFinal) {
						
						if(usuario.getSenhaVacina() == null) {
							
							Vacinacao vacinacao = new Vacinacao();
						
							String senhaVacina = Long.toString(calendario.getIdCalendarioVacinacao()) +
												 Long.toString(vacina.getIdVacina())+
												 Long.toString(usuario.getIdUsuario());
							
							
							usuario.setSenhaVacina(senhaVacina);
							vacinacao.setSenhaVacina(senhaVacina);
							
							vacinacao.setUsuario(usuario.getIdUsuario());
							vacinacao.setVacina(vacina.getIdVacina());
				
							
							calcularDoses(vacinacao, vacina.getIntervalo(), dataInicial, dataFinal,mediaDosesDiarias);
							
							notificationService.sendNotification(usuario, vacinacao);
							//usuarioService.saveUsuario(usuario);
							vacinacaoRepository.saveAndFlush(vacinacao);
							
							//Enviar e-mail
						}
					}
				}
	
			}
		}
		return;

	}

	public Vacinacao calcularDoses(Vacinacao vacinacao, Long intervaloVacina, LocalDate dataInicial,LocalDate dataFinal, long mediaDosesDiarias ) {
	
		
		LocalDate verificaDia = ControleDiarioDoses(mediaDosesDiarias, dataInicial, dataFinal);
		
		vacinacao.setPrimeiraDose(verificaDia);
		
		LocalDate data = dataInicial.plusDays(intervaloVacina);
		vacinacao.setSegundaDose(data);


		return vacinacao;
	}

	public LocalDate ControleDiarioDoses(long mediaDosesDiarias, LocalDate dataInicial, LocalDate dataFinal) { 
		
		long dosesDia  = vacinacaoRepository.totalDosesDia(dataInicial);
		
		if(dosesDia <= mediaDosesDiarias) {				
			
			while(dosesDia <= mediaDosesDiarias) {
				int contador = 1;
				LocalDate dia =  dataInicial.plusDays(contador);

				dosesDia  = vacinacaoRepository.totalDosesDia(dia);
				
				if (dia.isBefore(dataInicial) || dia.isAfter(dataFinal)) {		
					return dia;
				}
				
			}
			
		}
		
		return dataInicial;

	}
	public List<Vacinacao> relatorioParaVacinar(Date dataVacinar) {

		LocalDate data = dataVacinar.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
		LocalDate dataFimDaSemana = data.plusDays(7);
	
		List<Vacinacao> vacinacoes = vacinacaoRepository.findAll();
		
		List<Vacinacao> relatorio = new ArrayList<Vacinacao>();
		
		for( int i = 1; i > vacinacoes.size(); i++) {
			
			Vacinacao vacinacao = vacinacoes.get(i);
			
			LocalDate vacinacaoPrimeiraDose = vacinacao.getPrimeiraDose();
			LocalDate vacinacaoSegundaDose = vacinacao.getSegundaDose();
			
			
			if (vacinacaoPrimeiraDose.isBefore(data) || vacinacaoPrimeiraDose.isAfter(dataFimDaSemana)
				|| vacinacaoSegundaDose.isBefore(data) || vacinacaoSegundaDose.isAfter(dataFimDaSemana) ) {		
			
				relatorio.add(vacinacao);
			}
			
		}

		return relatorio;
	}
	
	public List<Vacinacao> relatorioVacinados(Date dataVacinados) {

		LocalDate data = dataVacinados.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
	
		LocalDate dataFimDaSemana = data.plusDays(7);
	
		List<Vacinacao> vacinacoes = vacinacaoRepository.findAll();
		
		List<Vacinacao> relatorio = new ArrayList<Vacinacao>();
		
		for( int i = 1; i > vacinacoes.size(); i++) {
			
			Vacinacao vacinacao = vacinacoes.get(i);
			
			LocalDate vacinacaoPrimeiraDose = vacinacao.getPrimeiraDose();
			LocalDate vacinacaoSegundaDose = vacinacao.getSegundaDose();
			
			
			if (vacinacaoPrimeiraDose.isBefore(data) || vacinacaoPrimeiraDose.isAfter(dataFimDaSemana)
				|| vacinacaoSegundaDose.isBefore(data) || vacinacaoSegundaDose.isAfter(dataFimDaSemana) ) {		
			
				if(vacinacao.getVacinadoPrimeiraDose() != null || vacinacao.getVacinadoPrimeiraDose() != null) {
					relatorio.add(vacinacao);
				}

			}
			
		}

		return relatorio;
	}
	public Vacinacao findById(Long id) {
		Optional<Vacinacao> opVacinacao = vacinacaoRepository.findById(id);
		return opVacinacao.isPresent() ? opVacinacao.get() : null;
	}

	@Transactional
	public void deleteById(Long id) {
		vacinacaoRepository.deleteById(id);
	}

	public List<Vacinacao> findAll() {
		return vacinacaoRepository.findAll();
	}
	
	@Transactional
	public Optional<Vacinacao> updateVacina(Long id , Vacinacao vacinacao) {
	
		return vacinacaoRepository.findById(id)
				.map(record ->{
					record.setVacinadoPrimeiraDose(vacinacao.getVacinadoPrimeiraDose());
					record.setVacinadoSegundaDose(vacinacao.getVacinadoSegundaDose());
				
					return vacinacaoRepository.save(record);
				});
			
	}
}
