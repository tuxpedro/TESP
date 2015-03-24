package br.com.phsweb.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	public Aluno findByCpf(String cpf) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_aluno WHERE cpf = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setString(1, cpf);
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
		String sql = "INSERT INTO tb_aluno (nome, cpf, matricula, data_nascimento)"
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);

			ps.setString(1, t.getNome());
			ps.setString(2, t.getCPF());
			ps.setLong(3, t.getMatricula());

			if (t.getDataAniversario() == null) {
				ps.setNull(4, Types.NULL);
			} else {
				ps.setString(4, df.format(t.getDataAniversario()));
			}

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
	public void update(Aluno t) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tb_aluno set nome = ?, cpf = ?, matricula = ?, "
				+ "data_nascimento = ? WHERE id = ?";

		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getCPF());
			ps.setLong(3, t.getMatricula());

			if (t.getDataAniversario() == null) {
				ps.setNull(4, Types.NULL);
			} else {
				ps.setString(4, df.format(t.getDataAniversario()));
			}

			ps.setLong(5, t.getId());

			ps.execute();
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Aluno t) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tb_aluno WHERE cpf = ?";
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql);
			ps.setString(1, t.getCPF());
			ps.execute();
			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Aluno> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tb_aluno";
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					sql);
			ResultSet row = ps.executeQuery();
			while (row.next()) {
				Aluno aluno = new Aluno(row.getLong("id"),
						row.getLong("matricula"), row.getString("nome"),
						row.getString("cpf"),
						row.getString("data_nascimento") == null ? null : df
								.parse(row.getString("data_nascimento")));
				alunos.add(aluno);
			}

			ps.close();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alunos;
	}

}