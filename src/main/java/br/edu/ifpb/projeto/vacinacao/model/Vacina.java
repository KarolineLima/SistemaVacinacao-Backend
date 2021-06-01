package br.edu.ifpb.projeto.vacinacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vacina")
public class Vacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacina;
	private String nomeVacina;
	private int totalDoses;
	private Long intervalo;

	
	public Vacina() {
		
	}
	
	public String getNomeVacina() {
		return nomeVacina;
	}
	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
	public int getTotalDoses() {
		return totalDoses;
	}
	public void setTotalDoses(int totalDoses) {
		this.totalDoses = totalDoses;
	}

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public Long getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Long intervalo) {
		this.intervalo = intervalo;
	}
	

	
}
