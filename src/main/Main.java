package main;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.dao.AccesoDatos;
import modelo.dao.AccesoDatos2;

public class Main {
	
	public static void main(String[] args) {
		AccesoDatos mySQL = new AccesoDatos();
//		ArrayList<HashMap<String, Object>> consulta = mySQL.getAllRecords("localhost", "personas", "root", "", "persona");
//		ArrayList<ArrayList<Object>> consulta2 = mySQL.getAllRecords2("localhost", "empresa", "root", "", "empleados");
//		mySQL.getAllRecordsSQLite("C:\\sqlite\\", "test.db", "root", "", "test2");
//		String sql = "INSERT INTO persona (fechaNacimiento) values (\"2005-03-12\")";
//		mySQL.updateRecords("localhost", "personas", "root", "", sql);
		ArrayList<HashMap<String, Object>> consultaPostgre = mySQL.getAllRecordsPostgreSQL("localhost:5433", "Banco", "postgres", "postgresql", "cajas");
//		System.out.println("fin");
		//mySQL.obtenerCCAA("localhost", "paro", "root", "");
		int[][] matriz = mySQL.ejercicio1(5, 7, 4, 15);
		AccesoDatos2 ac2 = new AccesoDatos2();
		ac2.obtainTotal();
	}
}
