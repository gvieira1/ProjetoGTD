package model.entity;

import java.time.LocalDate;

public class Tarefa {

	private int id;
	private String descricao;
    private Boolean prioridade; 
    private LocalDate prazo;
    private int tempoEstimadoId;    
    private String tempoEstimadoNome; 
    private String assunto;
    private Boolean feito;
    private Boolean delegado;
    private int usuarioId;
    private int categoriaId;
    private String categoriaNome;
    
    public void setId(int id) {
		this.id = id;
	}

	public Boolean getPrioridade() {
		if (prioridade == null) {
			return false;
		}
		return prioridade;
	}

	public void setPrioridade(Boolean prioridade) {
		this.prioridade = prioridade;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		if (delegado == null) {
			return false;
		}
		return delegado;
	}

	public void setDelegado(Boolean delegado) {
		this.delegado = delegado;
	}
}
