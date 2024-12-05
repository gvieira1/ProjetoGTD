package model.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.entity.Projeto;
import utils.DBConnection;

public class ProjetoDAO extends GenericDAO<Projeto> {

	public ProjetoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}

	public void insertProjeto(HttpServletRequest request, Projeto projeto) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario_id") == null) {
			throw new RuntimeException("Usuário não autenticado.");
		}

		int categoriaId = 3;
		String sql = "INSERT INTO projeto (id_usuario, descricao, categoria_id) VALUES (?, ?, ?)";
		super.insert(sql, projeto.getId_usuario(),projeto.getDescricao(), categoriaId);
	}
	
	public List<Projeto> findProjetoByUsuarioId(int usuarioId) {
	    String sql = "SELECT p.id, p.descricao, us.id AS id_usuario, "
	                + "p.feito, p.categoria_id, ca.descricao AS categoria_nome "
	                + "FROM projeto p "
	                + "LEFT JOIN categoria ca ON p.categoria_id = ca.id "
	                + "LEFT JOIN usuario us ON p.id_usuario = us.id "
	                + "WHERE p.id_usuario = ? AND p.categoria_id = 3"; 

	    return super.findAll(sql, new ProjetoRowMapper(), usuarioId);
	}
}
