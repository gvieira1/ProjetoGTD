package model.dao;

import java.util.List;
import model.entity.TempoEstimado;
import utils.DBConnection;

public class TempoEstimadoDAO extends GenericDAO<TempoEstimado> {

	public TempoEstimadoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public List<TempoEstimado> findTempoEstimado() {
		String sql = "SELECT id, tempo FROM tempo_estimado"; 
		return super.findAll(sql, new TempoEstimadoRowMapper());
	}

}
