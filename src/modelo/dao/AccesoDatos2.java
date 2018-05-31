package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AccesoDatos2 {
	
	public Connection crearConexionMySQL(String dominio, String bd, String usr, String clave) {
		Connection connect = null;
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
	
	public void obtainTotal(){
		HashMap<Double, ArrayList<Float>> totalOrders = new HashMap<Double, ArrayList<Float>>();
		Connection conn = crearConexionMySQL("localhost", "shop", "root", "");
		try {
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery("select order_id from orders");
			while(rs.next()) {
				totalOrders.put(rs.getDouble(1), new ArrayList<Float>());
			}
			rs.close();
			ResultSet rs2 = smt.executeQuery("select * from order_details order by price");
			Set<Double> ids = totalOrders.keySet();
			while(rs2.next()) {
				for(Double id: ids) {
					if(id == rs2.getDouble(7)) {
						totalOrders.get(rs2.getDouble(7)).add(rs2.getFloat(6)*rs2.getInt(5));
					};
				}
			}
			Float totalTabla = 0f;
			for(Double id: ids) {
				ArrayList<Float> precios = totalOrders.get(id);
				System.out.println("Id_Orders: " + id);
				Float total = 0f;
				for(int i = 0; i < precios.size();i++) {
					System.out.println(precios.get(i));
					total += precios.get(i);
				}
				totalTabla += total;
				System.out.println("Total " + id + " : " + total);
			}
			System.out.println("Total facturas : " + totalTabla);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
