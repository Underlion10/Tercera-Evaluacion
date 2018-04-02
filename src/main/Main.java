package main;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.dao.AccesoDatos;

public class Main {
	
	public static void main(String[] args) {
		AccesoDatos mySQL = new AccesoDatos();
		ArrayList<HashMap<String, Object>> consulta = mySQL.getAllRecords("localhost", "empresa", "root", "", "empleados");
	}
}
