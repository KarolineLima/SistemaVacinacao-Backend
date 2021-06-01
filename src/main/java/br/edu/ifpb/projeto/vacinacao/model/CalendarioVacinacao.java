package br.edu.ifpb.projeto.vacinacao.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="calendarioVacinacao")
public class CalendarioVacinacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCalendarioVacinacao;
	
	LocalDate dataInicio;
	LocalDate dataFinal;
	int faixaEtariaInicial;
	int faixaEtariaFinal;
	private boolean obsoleto;

	public CalendarioVacinacao() {
		
	}

	public Long getIdCalendarioVacinacao() {
		return idCalendarioVacinacao;
	}

	public void setIdCalendarioVacinacao(Long idCalendarioVacinacao) {
		this.idCalendarioVacinacao = idCalendarioVacinacao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getFaixaEtariaInicial() {
		return faixaEtariaInicial;
	}

	public void setFaixaEtariaInicial(int faixaEtariaInicial) {
		this.faixaEtariaInicial = faixaEtariaInicial;
	}

	public int getFaixaEtariaFinal() {
		return faixaEtariaFinal;
	}

	public void setFaixaEtariaFinal(int faixaEtariaFinal) {
		this.faixaEtariaFinal = faixaEtariaFinal;
	}

	public boolean isObsoleto() {
		return obsoleto;
	}

	public void setObsoleto(boolean obsoleto) {
		this.obsoleto = obsoleto;
	}

	

}
