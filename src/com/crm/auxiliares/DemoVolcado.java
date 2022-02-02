package com.crm.auxiliares;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemoVolcado {

	public static void main(String[] args)  throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicioregiones?useSSL=false&serverTimezone=UTC","root","root");
		PreparedStatement ps = con.prepareStatement("SELECT idCliente from departamentos");

		
		ResultSet rs = ps.executeQuery();
		ArrayList<Integer> lista = new ArrayList<Integer>();
		while(rs.next()) {
			lista.add(rs.getInt("idCliente"));
		}
		
		ps = con.prepareStatement("UPDATE empleados set cliente=true where id=?");
   		for(int j=0; j<lista.size();j++) {
   			ps.setInt(1, lista.get(j));
   			ps.executeUpdate();
		
	}
   		ps = con.prepareStatement("UPDATE empleados set cliente=false where jefe is null");
        ps.execute();

}
	
}