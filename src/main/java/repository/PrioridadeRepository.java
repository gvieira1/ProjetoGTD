package repository;

import model.Prioridade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrioridadeRepository {
    private DBConnection dbConnection;

    public PrioridadeRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Método para buscar todas as prioridades
    public List<Prioridade> findAll() {
        List<Prioridade> prioridades = new ArrayList<>();
        String sql = "SELECT id, nome FROM prioridade"; 

        try (Connection conn = dbConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Prioridade prioridade = new Prioridade();
                prioridade.setId(rs.getInt("id"));
                prioridade.setNome(rs.getString("nome"));
                prioridades.add(prioridade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prioridades;
    }
}
