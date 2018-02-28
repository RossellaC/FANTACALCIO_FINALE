package persistence.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {
	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private String readSqlFile(String file) {
		InputStream is = UtilDao.class.getClassLoader().getResourceAsStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		try {
			String s = br.readLine();
			while(s != null) {
				sb.append(s);
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String delete = sb.toString();
		return delete;
	}
	
	public void dropDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = readSqlFile("persistence/dropschema.sql");
//					""
//					+ "drop table if exists public.gioca;" 
//					+ "drop table if exists public.partita;"
//					+ "drop table if exists public.giornata;" 
//					+ "drop table if exists public.composta;"
//					+ "drop table if exists public.squadra;"
//					+ " drop table if exists public.invito;"
//					+ " drop table if exists public.lega;" 
//					+ " drop table if exists public.utente;"
//					+ "drop table if exists public.calciatore;";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed drop database");

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createDatabase() {

		Connection connection = dataSource.getConnection();
		try {
//			InputStream is = UtilDao.class.getClassLoader().getResourceAsStream("persistence/schema.sql");
//			if(is == null) { return; }
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			StringBuilder sb = new StringBuilder();
//			try {
//				String s = br.readLine();
//				while(s != null) {
//					sb.append(s);
//					s = br.readLine();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			String delete = readSqlFile("persistence/schema.sql"); 
//					sb.toString();

			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed create database");

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void resetDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM calciatore";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.executeUpdate();

			String delete1 = "delete FROM utente";
			statement = connection.prepareStatement(delete1);
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}
}
