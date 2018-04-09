package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class AccesoDatos {
	
	private Connection connect = null;
	
	public Connection crearConexionMySQL(String dominio, String bd, String usr, String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + dominio + "/"+ bd;
			connect = DriverManager
			          .getConnection(url, usr, clave);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public Connection crearConexionSQLite(String dominio, String bd, String usr, String clave) {
		Connection connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite://" + dominio + "/"+ bd;
			connect = DriverManager
			          .getConnection(url, usr, clave);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public Connection crearConexionPostgreSQL(String dominio, String bd, String usr, String clave) {
		Connection connect = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + dominio + "/"+ bd;
			connect = DriverManager
			          .getConnection(url, usr, clave);
			System.out.println("He llegado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public ArrayList<HashMap<String, Object>> getAllRecords(String dominio, String bd, String usr, String clave, String tabla) {
		//Conexión a la base de datos
		ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();
		try {
			Connection connection = this.crearConexionMySQL(dominio, bd, usr, clave);
			String sql = "SELECT * FROM " + tabla + " WHERE 1 = 2";
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			rs.last();
			if(rs.getRow() < 1) {
				System.out.println("La tabla está vacia");
				rs.close();
				stm.close();
				return null;
			}
			rs.beforeFirst();
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> filas = new HashMap<String, Object>();
					for(int i = 1; i <= metaData.getColumnCount(); i++) {
						filas.put(metaData.getColumnName(i), rs.getObject(i));
					}
					resultados.add(filas);
			}
			System.out.println(resultados);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;
	}
	
	public ArrayList<ArrayList<Object>> getAllRecords2(String dominio, String bd, String usr, String clave, String tabla) {
		//Conexión a la base de datos
		ArrayList<ArrayList<Object>> resultados = new ArrayList<ArrayList<Object>>();
		try {
			Connection connection = this.crearConexionMySQL(dominio, bd, usr, clave);
			String sql = "SELECT * FROM " + tabla;
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			ArrayList<Object> columnNames = new ArrayList<Object>();
			for(int j = 1; j <= metaData.getColumnCount(); j++)
				columnNames.add(metaData.getColumnName(j));
			resultados.add(columnNames);
			while(rs.next()) {
				ArrayList<Object> filas = new ArrayList<Object>();
				for(int i = 1; i <= metaData.getColumnCount(); i++) {
					filas.add(rs.getObject(i));
				}
				resultados.add(filas);
			}
			System.out.println(resultados);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;
	}
	
	public ArrayList<HashMap<String, Object>> getAllRecordsSQLite(String dominio, String bd, String usr, String clave, String tabla) {
		//Conexión a la base de datos
		ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();
		try {
			Connection connection = this.crearConexionSQLite(dominio, bd, usr, clave);
			String sql = "SELECT * FROM " + tabla;
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> filas = new HashMap<String, Object>();
					for(int i = 1; i <= metaData.getColumnCount(); i++) {
						filas.put(metaData.getColumnName(i), rs.getObject(i));
					}
					resultados.add(filas);
			}
			System.out.println(resultados);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;
	}
	
	public ArrayList<HashMap<String, Object>> getAllRecordsPostgreSQL(String dominio, String bd, String usr, String clave, String tabla) {
		//Conexión a la base de datos
		ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();
		try {
			Connection connection = this.crearConexionPostgreSQL(dominio, bd, usr, clave);
			String sql = "SELECT * FROM " + tabla;
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> filas = new HashMap<String, Object>();
					for(int i = 1; i <= metaData.getColumnCount(); i++) {
						filas.put(metaData.getColumnName(i), rs.getObject(i));
					}
					resultados.add(filas);
			}
			System.out.println(resultados);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;
	}
	
	public void updateRecords(String dominio, String bd, String usr, String clave, String sql) {
		Connection connection = this.crearConexionSQLite(dominio, bd, usr, clave);
		Statement stm;
		try {
			stm = connection.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
