package br.com.phsweb.persistencia;

import java.sql.PreparedStatement;

public class JDBCTestes {
	public static void limpaBanco(String t) {
		// remove todos os dados da tabela
		try {
			PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(
					"TRUNCATE" + t);
			ps.execute();
			JDBCUtil.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Não pode trucar a tabela");
		}
	}
}
