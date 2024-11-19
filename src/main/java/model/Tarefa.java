package model;

import java.time.LocalDate;

public class Tarefa {

	private int id;
    private String descricao;
    private int prioridadeId;
    private String prioridadeNome;  
    private LocalDate prazo;
    private int tempoEstimadoId;    
    private String tempoEstimadoNome; 
    private String assunto;
    private Boolean feito;
    private Boolean delegado;

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPrioridadeId() {
		return prioridadeId;
	}

	public void setPrioridadeId(int prioridadeId) {
		this.prioridadeId = prioridadeId;
	}

	public String getPrioridadeNome() {
		return prioridadeNome;
	}

	public void setPrioridadeNome(String prioridadeNome) {
		this.prioridadeNome = prioridadeNome;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public int getTempoEstimadoId() {
		return tempoEstimadoId;
	}

	public void setTempoEstimadoId(int tempoEstimadoId) {
		this.tempoEstimadoId = tempoEstimadoId;
	}

	public String getTempoEstimadoNome() {
		return tempoEstimadoNome;
	}

	public void setTempoEstimadoNome(String tempoEstimadoNome) {
		this.tempoEstimadoNome = tempoEstimadoNome;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Boolean getFeito() {
		return feito;
	}

	public void setFeito(Boolean feito) {
		this.feito = feito;
	}

	public Boolean getDelegado() {
		return delegado;
	}

	public void setDelegado(Boolean delegado) {
		this.delegado = delegado;
	}
}
