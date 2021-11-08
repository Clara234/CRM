package com.crm.graficos;

import java.util.List;

import com.crm.pojos.Cliente;
import com.crm.pojos.Empleado;

public interface Servicios {
	
	public void addLibro(Empleado empleado);
	public Empleado getLibroById(int id);
	public List<Empleado> getAll();
	public void updateLibro(Empleado Empleado);
	public void borrarLibro(int id);
	
	public void addLibro(Cliente Cliente);
	public Cliente getLibroById1(int id);
    public List<Cliente> getAll1();
    public void updateLibro(Cliente Cliente);
    public void borrarLibro1(int id);
    
}
