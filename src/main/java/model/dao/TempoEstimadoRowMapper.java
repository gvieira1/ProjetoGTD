package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.entity.TempoEstimado;

public class TempoEstimadoRowMapper implements RowMapper<TempoEstimado> {

	@Override
	public TempoEstimado mapRow(ResultSet rs) throws SQLException {
		TempoEstimado tempoEstimado = new TempoEstimado();
        tempoEstimado.setId(rs.getInt("id"));
        tempoEstimado.setTempo(rs.getString("tempo"));
		return tempoEstimado;
	}

	
}
