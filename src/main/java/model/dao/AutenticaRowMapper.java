package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.entity.Usuario;

public class AutenticaRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        return usuario;
    }
}
