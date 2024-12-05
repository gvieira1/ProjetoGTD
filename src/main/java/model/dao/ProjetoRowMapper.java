package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Projeto;

public class ProjetoRowMapper implements RowMapper<Projeto> {

	@Override
    public Projeto mapRow(ResultSet rs) throws SQLException {
		Projeto projeto = new Projeto();
		projeto.setId(rs.getInt("id"));
		projeto.setId_usuario(rs.getInt("id_usuario"));
		projeto.setDescricao(rs.getString("descricao"));
		projeto.setFeito_p(rs.getBoolean("feito"));
		projeto.setCategoria_id(rs.getInt("categoria_id"));
		projeto.setCategoria_nome(rs.getString("categoria_nome"));
		return projeto;
    }
	
}
