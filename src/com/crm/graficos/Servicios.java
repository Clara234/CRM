package com.crm.graficos;

import java.sql.SQLException;
import java.util.List;

import com.crm.pojos.Cliente;
import com.crm.pojos.Empleado;

public interface Servicios {
	   public void addEmpleado(Empleado emp)throws SQLException;//Post 
		public List<Empleado> getAllEmpleados()throws SQLException;//GetAll
	    public Empleado getbyId(int id)throws SQLException;//get uno
	    public Empleado updateEmp(Empleado emp)throws SQLException;//PUT(actualizado)
	    public void deleteEmp(int id)throws SQLException;// delete

    
    
}
