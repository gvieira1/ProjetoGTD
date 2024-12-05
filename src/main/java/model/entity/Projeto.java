package model.entity;

public class Projeto {
	
	private int id;
	private int id_usuario;
	private String descricao;
	private Boolean feito_p;
	private int categoria_id;
	private String categoria_nome;
	
	public String getCategoria_nome() {
		return categoria_nome;
	}
	public void setCategoria_nome(String categoria_nome) {
		this.categoria_nome = categoria_nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getFeito_p() {
		return feito_p;
	}
	public void setFeito_p(Boolean feito_p) {
		this.feito_p = feito_p;
	}
	public int getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	

}
