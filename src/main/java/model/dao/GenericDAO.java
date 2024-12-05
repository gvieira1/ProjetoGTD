package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

public abstract class GenericDAO<T> {
	
    private DBConnection dbConnection;

    public GenericDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    protected List<T> findAll(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> resultList = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultList.add(rowMapper.mapRow(rs)); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    @SuppressWarnings("hiding")
	protected <T> T findOne(String sql, RowMapper<T> rowMapper, Object... params) {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rowMapper.mapRow(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }


	protected void insert(String sql, Object... params) {

		try (Connection conn = dbConnection.getConnection(); 
			
			PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    

	protected int updateAndReturnAffectedRows(String sql, Object... params) {
		try (Connection conn = dbConnection.getConnection(); 
			
			PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}

			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	protected boolean delete(String sql, Object... params) {
        try (Connection conn = dbConnection.getConnection();
            
        	PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

