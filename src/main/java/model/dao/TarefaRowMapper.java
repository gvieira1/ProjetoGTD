package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Tarefa;

public class TarefaRowMapper implements RowMapper<Tarefa> {

	@Override
    public Tarefa mapRow(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(rs.getInt("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setPrioridade(rs.getBoolean("prioridade"));
		tarefa.setPrazo(rs.getDate("prazo") != null ? rs.getDate("prazo").toLocalDate() : null);
		tarefa.setTempoEstimadoId(rs.getInt("tempo_estimado_id"));
		tarefa.setTempoEstimadoNome(rs.getString("tempo_estimado_nome"));
		tarefa.setAssunto(rs.getString("assunto"));
		tarefa.setFeito(rs.getBoolean("feito"));
		tarefa.setDelegado(rs.getBoolean("delegado"));
		tarefa.setUsuarioId(rs.getInt("usuario_id"));
		tarefa.setCategoriaId(rs.getInt("categoria_id"));
		tarefa.setCategoriaNome(rs.getString("categoria_nome"));
		return tarefa;
    }
	
	public Tarefa mapTarefaECategoria(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(rs.getInt("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setCategoriaNome(rs.getString("categoria"));
		return tarefa;
	}
	
}
