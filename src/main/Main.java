package main;

import modelo.dao.AccesoDatos;

public class Main {
	
	public static void main(String[] args) {
		AccesoDatos mySQL = new AccesoDatos();
//		ArrayList<HashMap<String, Object>> consulta = mySQL.getAllRecords("localhost", "personas", "root", "", "persona");
//		ArrayList<ArrayList<Object>> consulta2 = mySQL.getAllRecords2("localhost", "empresa", "root", "", "empleados");
//		mySQL.getAllRecordsSQLite("C:\\sqlite\\", "test.db", "root", "", "test2");
//		String sql = "INSERT INTO persona (fechaNacimiento) values (\"2005-03-12\")";
//		mySQL.updateRecords("localhost", "personas", "root", "", sql);
//		ArrayList<HashMap<String, Object>> consultaPostgre = mySQL.getAllRecordsPostgreSQL("localhost:5433", "Banco", "postgres", "postgresql", "cajas");
//		System.out.println("fin");
		mySQL.obtenerCCAA("localhost", "paro", "root", "");
	}
}
