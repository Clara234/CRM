package com.crm.graficos;

import java.util.List;

import com.crm.pojos.Empleado;

public interface Servicios {
	
	public void addLibro(Empleado empleado);
	public Empleado getLibroById(int id);
	public List<Empleado> getAll();
	public void updateLibro(Empleado Empleado);
	public void borrarLibro(int id);

}
