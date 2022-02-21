package com.crm.graficos;

import java.util.List;
import java.sql.SQLException;

import com.crm.pojos.Usuario;

public interface Servicios2 {
	
	 public void addUsuario(Usuario usuario)throws SQLException;//Post 
			public List<Usuario> getAllUsuarios()throws SQLException;
		    public Usuario getbyId(int id)throws SQLException;
		    public Usuario updateUsuario(Usuario usuario)throws SQLException;
		    public void deleteUsuario(int id)throws SQLException;
}
