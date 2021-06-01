package br.edu.ifpb.projeto.vacinacao.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="vacinacao")
public class Vacinacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacinacao;
	
	
	private Long vacina;
	
	private Long usuario;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate primeiraDose;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate segundaDose;
	private String localVacinacao;
	private String senhaVacina;
	private Boolean vacinadoPrimeiraDose;
	private Boolean vacinadoSegundaDose;
	
	public Vacinacao() {
	
	}


	public Long getIdVacinacao() {
		return idVacinacao;
	}


	public void setIdVacinacao(Long idVacinacao) {
		this.idVacinacao = idVacinacao;
	}


	public Long getVacina() {
		return vacina;
	}


	public void setVacina(Long vacina) {
		this.vacina = vacina;
	}


	public Long getUsuario() {
		return usuario;
	}


	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}


	public LocalDate getPrimeiraDose() {
		return primeiraDose;
	}


	public void setPrimeiraDose(LocalDate primeiraDose) {
		this.primeiraDose = primeiraDose;
	}


	public LocalDate getSegundaDose() {
		return segundaDose;
	}


	public void setSegundaDose(LocalDate segundaDose) {
		this.segundaDose = segundaDose;
	}


	public String getLocalVacinacao() {
		return localVacinacao;
	}


	public void setLocalVacinacao(String localVacinacao) {
		this.localVacinacao = localVacinacao;
	}


	public String getSenhaVacina() {
		return senhaVacina;
	}


	public void setSenhaVacina(String senhaVacina) {
		this.senhaVacina = senhaVacina;
	}


	public Boolean getVacinadoPrimeiraDose() {
		return vacinadoPrimeiraDose;
	}


	public void setVacinadoPrimeiraDose(Boolean vacinadoPrimeiraDose) {
		this.vacinadoPrimeiraDose = vacinadoPrimeiraDose;
	}


	public Boolean getVacinadoSegundaDose() {
		return vacinadoSegundaDose;
	}


	public void setVacinadoSegundaDose(Boolean vacinadoSegundaDose) {
		this.vacinadoSegundaDose = vacinadoSegundaDose;
	}

	

}
