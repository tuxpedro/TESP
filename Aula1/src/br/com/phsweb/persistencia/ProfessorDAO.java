package br.com.phsweb.persistencia;

import java.util.ArrayList;
import java.util.List;
import br.com.phsweb.entidades.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessorDAO implements DAO<Professor, Long> {

	@Override
	public Professor find(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_professor WHERE id = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setLong(1, id);
			ResultSet row = ps.executeQuery();
			if (row.next()) {
				return new Professor(row.getLong("id"), row.getString("nome"),
						row.getString("cpf"), row.getBigDecimal("salario"));
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
	public boolean insert(Professor t) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tb_professor (nome, cpf, salario) VALUES(?, ?, ?)";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getCPF());
			ps.setBigDecimal(3, t.getSalario());
			ps.execute();
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void update(Professor t) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tb_professor set nome = ?, cpf = ?, salario = ? WHERE  id = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getCPF());
			ps.setBigDecimal(3, t.getSalario());
			ps.setLong(4, t.getId());
			ps.execute();
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Professor t) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tb_professor WHERE id = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setLong(1, t.getId());
			ps.execute();
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<Professor> findAll() {
		// TODO Auto-generated method stub
		List<Professor> professores = new ArrayList<Professor>();
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					"SELECT * FROM tb_professor");
			ResultSet row = ps.executeQuery();
			while (row.next()) {
				Professor professor = new Professor(row.getLong("id"),
						row.getString("nome"), row.getString("cpf"),
						row.getBigDecimal("salario"));
				professores.add(professor);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professores;
	}

}