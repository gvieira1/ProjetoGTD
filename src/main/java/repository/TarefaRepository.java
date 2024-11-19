package repository;

import model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

    private DBConnection dbConnection;

    // Construtor
    public TarefaRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Método para inserir uma nova tarefa
    public void insertParcial(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (descricao, prioridade_id, prazo, tempo_estimado_id, assunto, feito, delegado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        
        try (Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setInt(2, tarefa.getPrioridadeId());  
            stmt.setDate(3, tarefa.getPrazo() != null ? Date.valueOf(tarefa.getPrazo()) : null);
            stmt.setInt(4, tarefa.getTempoEstimadoId()); 
            stmt.setString(5, tarefa.getAssunto());
            stmt.setBoolean(6, tarefa.getFeito());
            stmt.setBoolean(7, tarefa.getDelegado());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
	
	// Método para buscar todas as tarefas 
	public List<Tarefa> findAll() {
		List<Tarefa> tarefas = new ArrayList<>();
		String sql = "SELECT t.descricao, t.prioridade_id, p.nome AS prioridade_nome, t.prazo, t.tempo_estimado_id, te.tempo AS tempo_estimado_nome, t.assunto, t.feito, t.delegado "
				+ "FROM tarefa t " 
				+ "LEFT JOIN prioridade p ON t.prioridade_id = p.id "
				+ "LEFT JOIN tempo_estimado te ON t.tempo_estimado_id = te.id";

		try (Connection conn = dbConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setPrioridadeId(rs.getInt("prioridade_id"));
				tarefa.setPrioridadeNome(rs.getString("prioridade_nome"));
				tarefa.setPrazo(rs.getDate("prazo") != null ? rs.getDate("prazo").toLocalDate() : null);
				tarefa.setTempoEstimadoId(rs.getInt("tempo_estimado_id"));
				tarefa.setTempoEstimadoNome(rs.getString("tempo_estimado_nome"));
				tarefa.setAssunto(rs.getString("assunto"));
				tarefa.setFeito(rs.getBoolean("feito"));
				tarefa.setDelegado(rs.getBoolean("delegado"));
				tarefas.add(tarefa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tarefas;
	}

    // Método para atualizar o status de uma tarefa
    public void update(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET descricao = ?, prioridade_id = ?, prazo = ?, tempo_estimado_id = ?, assunto = ?, feito = ? WHERE id = ?";
        
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tarefa.getDescricao());
            stmt.setInt(2, tarefa.getPrioridadeId());  
            stmt.setDate(3, Date.valueOf(tarefa.getPrazo()));
            stmt.setInt(4, tarefa.getTempoEstimadoId()); 
            stmt.setString(5, tarefa.getAssunto());
            stmt.setBoolean(6, tarefa.getFeito());
            stmt.setInt(7, tarefa.getId()); 
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma tarefa
    public void delete(int id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);  // Usando o ID da tarefa
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
