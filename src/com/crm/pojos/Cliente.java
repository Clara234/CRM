package com.crm.pojos;

import java.sql.Timestamp;
public class Cliente {
	
     private String dni_nie;
     private String correoe;
     private  String ciudad;
     private String ubicacion;
     private String notas;
     private int telefono;
     private Timestamp fecha_alta;
     private boolean autorizado;
     private boolean cliente;
     private boolean adjunto;
     
     
	public void Cliente(Cliente cli) {}


	public Cliente(String dni_nie, String correoe, String ciudad, String ubicacion, String notas, int telefono,
			Timestamp fecha_alta, boolean autorizado, boolean cliente, boolean adjunto) {
	
		this.dni_nie = dni_nie;
		this.correoe = correoe;
		this.ciudad = ciudad;
		this.ubicacion = ubicacion;
		this.notas = notas;
		this.telefono = telefono;
		this.fecha_alta = fecha_alta;
		this.autorizado = autorizado;
		this.cliente = cliente;
		this.adjunto = adjunto;
	}


	public String getDni_nie() {
		return dni_nie;
	}


	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}


	public String getCorreoe() {
		return correoe;
	}


	public void setCorreoe(String correoe) {
		this.correoe = correoe;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public String getNotas() {
		return notas;
	}


	public void setNotas(String notas) {
		this.notas = notas;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public Timestamp getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}


	public boolean isAutorizado() {
		return autorizado;
	}


	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}


	public boolean isCliente() {
		return cliente;
	}


	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}


	public boolean isAdjunto() {
		return adjunto;
	}


	public void setAdjunto(boolean adjunto) {
		this.adjunto = adjunto;
	}
	
	
	
	
	
     
     
}
