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
	
	public Connection crearConexion(String dominio, String bd, String usr, String clave) {
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
	
	public ArrayList<HashMap<String, Object>> getAllRecords(String dominio, String bd, String usr, String clave, String tabla) {
		//Conexión a la base de datos
		try {
			ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();
			Connection connection = this.crearConexion(dominio, bd, usr, clave);
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
			return resultados;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
