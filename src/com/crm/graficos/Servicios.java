package com.crm.graficos;

import java.sql.SQLException;

import java.util.List;

import com.crm.pojos.Cliente;


public interface Servicios {
	  

	    public void addCliente(Cliente cliente)throws SQLException;//Post 
		public List<Cliente> getAllClientes()throws SQLException;//GetAll
	    public Cliente getbyDni_nie(String dni_nie)throws SQLException;//get uno
	    public Cliente updateCliente(Cliente cliente)throws SQLException;//PUT(actualizado)
	    public void deleteCliente(String dni_nie)throws SQLException;// delete
    
}
