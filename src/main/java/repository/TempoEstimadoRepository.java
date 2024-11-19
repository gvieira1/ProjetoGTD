package repository;

import model.TempoEstimado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TempoEstimadoRepository {
    private DBConnection dbConnection;

    public TempoEstimadoRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<TempoEstimado> findAll() {
        List<TempoEstimado> temposEstimados = new ArrayList<>();
        String sql = "SELECT id, tempo FROM tempo_estimado"; 

        try (Connection conn = dbConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TempoEstimado tempoEstimado = new TempoEstimado();
                tempoEstimado.setId(rs.getInt("id"));
                tempoEstimado.setTempo(rs.getString("tempo"));
                temposEstimados.add(tempoEstimado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temposEstimados;
    }
}
