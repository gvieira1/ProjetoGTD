package model.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.entity.Tarefa;
import utils.DBConnection;

public class TarefaDAO extends GenericDAO<Tarefa> {

	public TarefaDAO(DBConnection dbConnection) {
		super(dbConnection);
	}

	public Tarefa findTarefaById(int tarefaId) {
	    String sql = "SELECT t.id, t.descricao, t.prioridade, t.prazo, t.tempo_estimado_id, te.tempo AS tempo_estimado_nome, "
	                 + "t.assunto, t.feito, t.delegado, t.usuario_id, t.categoria_id, ca.descricao AS categoria_nome "
	                 + "FROM tarefa t "
	                 + "LEFT JOIN categoria ca ON t.categoria_id = ca.id "
	                 + "LEFT JOIN tempo_estimado te ON t.tempo_estimado_id = te.id "
	                 + "WHERE t.id = ?";  
	    
	    return super.findOne(sql, new TarefaRowMapper(), tarefaId);
	}

	
	public List<Tarefa> findTarefaByUsuarioId(int usuarioId) {
	    String sql = "SELECT t.id, t.descricao, t.prioridade, t.prazo, t.tempo_estimado_id, te.tempo AS tempo_estimado_nome, "
	                + "t.assunto, t.feito, t.delegado, t.usuario_id, t.categoria_id, ca.descricao AS categoria_nome "
	                + "FROM tarefa t "
	                + "LEFT JOIN categoria ca ON t.categoria_id = ca.id "
	                + "LEFT JOIN tempo_estimado te ON t.tempo_estimado_id = te.id "
	                + "WHERE t.usuario_id = ? AND t.categoria_id = 6"; 

	    return super.findAll(sql, new TarefaRowMapper(), usuarioId);
	}

	public List<Tarefa> findTarefaECategoria(int usuarioId) {
	    String sql = "SELECT t.id, t.descricao, c.descricao AS categoria"
	               + " FROM tarefa t"
	               + " JOIN categoria c ON t.categoria_id = c.id"
	               + " WHERE t.usuario_id = ?"
	               + " ORDER BY c.descricao;";
	    return super.findAll(sql, new TarefaRowMapper()::mapTarefaECategoria, usuarioId);
	}

	
	public void insertTarefaDescricao(HttpServletRequest request, Tarefa tarefa) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario_id") == null) {
			throw new RuntimeException("Usuário não autenticado.");
		}

		Integer usuarioId = (Integer) session.getAttribute("usuario_id");

		int categoriaId = 6;
		String sql = "INSERT INTO tarefa (descricao, usuario_id, categoria_id) VALUES (?, ?, ?)";

		super.insert(sql, tarefa.getDescricao(), usuarioId, categoriaId);
	}
	
	
	public boolean updateTarefa(Tarefa tarefa) {
		String sql = "UPDATE tarefa SET descricao = ?, prioridade = ?, prazo = ?, tempo_estimado_id = ?, assunto = ?, feito = ?, delegado = ?, categoria_id = ? WHERE id = ?";

		int rowsUpdated = super.updateAndReturnAffectedRows(sql, 
				tarefa.getDescricao(), 
				tarefa.getPrioridade() ? 1 : 0, 
				tarefa.getPrazo(), 
				tarefa.getTempoEstimadoId(), 
				tarefa.getAssunto(),
				(tarefa.getFeito() != null && tarefa.getFeito()) ? 1 : 0, 
				tarefa.getDelegado() ? 1 : 0, 
				tarefa.getCategoriaId(), 
				tarefa.getId());
		return rowsUpdated > 0;
	}
	
	public boolean updateFeito(Tarefa tarefa) {
	    String sql = "UPDATE tarefa SET feito = ?, categoria_id = 7 WHERE id = ?";

	    int rowsUpdated = super.updateAndReturnAffectedRows(sql, 
	            (tarefa.getFeito() != null && tarefa.getFeito()) ? 1 : 0,  
	            tarefa.getId());
	    return rowsUpdated > 0;
	}

	public boolean deleteTarefa(Tarefa tarefa) {
		
		String sql = "DELETE FROM tarefa WHERE id = ?";

		boolean rowsUpdated = super.delete(sql, tarefa.getId());
		return rowsUpdated;
	}
	
	

}
