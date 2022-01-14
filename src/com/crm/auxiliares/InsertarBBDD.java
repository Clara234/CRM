package com.crm.auxiliares;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;
import com.crm.pojos.HipotecaDatos;

public class InsertarBBDD {

	HipotecaDatos hipotecaDatos = null;
	MisConexiones c = null;
	String box;

	public InsertarBBDD() {
		int resp = JOptionPane.showConfirmDialog(null, "Usted insertara", "¿Esdta seguro?", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);

		if (resp == JOptionPane.YES_OPTION) {
			try {
				c = new MisConexiones();
				
			}catch(InstantiationException e1) {
				
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PreparedStatement ps = null;
			
			try {
				ps = c.getPS(ConfigDir.getInstance().getProperty("query5"));
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.setString(1, hipotecaDatos.getFinalidad());
	            ps.setString(2, hipotecaDatos.getValorAdquisicion());
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.executeUpdate();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			
			
          
		}

	}

}
