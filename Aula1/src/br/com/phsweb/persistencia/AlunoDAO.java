package br.com.phsweb.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import br.com.phsweb.entidades.Aluno;

public class AlunoDAO implements DAO<Aluno, Long> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Aluno find(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_aluno WHERE id = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setLong(1, id);
			ResultSet row = ps.executeQuery();
			if (row.next()) {
				return new Aluno(row.getLong("id"), row.getLong("matricula"),
						row.getString("nome"), row.getString("cpf"),
						row.getString("data_nascimento") == null ? null : df
								.parse(row.getString("data_nascimento")));
			}
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean insert(Aluno t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Aluno t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Aluno t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Aluno> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}