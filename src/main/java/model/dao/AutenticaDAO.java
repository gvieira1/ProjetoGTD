package model.dao;

import java.util.List;

import model.entity.Usuario;
import utils.DBConnection;

public class AutenticaDAO extends GenericDAO<Usuario> {

    public AutenticaDAO(DBConnection dbConnection) {
        super(dbConnection);
    }

    public Usuario validarLogin(String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuario WHERE email = ? AND senha = ?";
        
        List<Usuario> usuarios = super.findAll(sql, new AutenticaRowMapper(), email, senha);
        
        if (usuarios.isEmpty()) {
            return null; 
        } else {
            return usuarios.get(0); 
        }
    }
}
