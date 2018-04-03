package main;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.dao.AccesoDatos;

public class Main {
	
	public static void main(String[] args) {
		AccesoDatos mySQL = new AccesoDatos();
//		//ArrayList<HashMap<String, Object>> consulta = mySQL.getAllRecords("localhost", "empresa", "root", "", "empleados");
//		ArrayList<ArrayList<Object>> consulta2 = mySQL.getAllRecords2("localhost", "empresa", "root", "", "empleados");
		mySQL.getAllRecordsSQLite("C:\\sqlite\\", "test.db", "root", "", "test2");
		System.out.println("fin");
	}
}
