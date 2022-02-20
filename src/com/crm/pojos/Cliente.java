package com.crm.pojos;

import java.sql.Timestamp;
public class Cliente {

     private String dni_nie;
     private String nombre;
     private String ape1;
     private  String ape2;
     private Timestamp fechaNacimiento;
     private boolean fijo;
     private boolean temporal;
     private boolean autonomo;
     private boolean otros;
     private String profesion;
     private String domicilio;
     private String poblacion;
     private String codigoPostal;
     
     
	public void Cliente(Cliente cli) {}


	public Cliente(String dni_nie, String nombre, String ape1, String ape2, Timestamp fechaNacimiento, boolean fijo,
			boolean temporal, boolean autonomo, boolean otros, String profesion, String domicilio, String poblacion,
			String codigoPostal) {
		super();
		this.dni_nie = dni_nie;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fechaNacimiento = fechaNacimiento;
		this.fijo = fijo;
		this.temporal = temporal;
		this.autonomo = autonomo;
		this.otros = otros;
		this.profesion = profesion;
		this.domicilio = domicilio;
		this.poblacion = poblacion;
		this.codigoPostal = codigoPostal;
	}


	public String getDni_nie() {
		return dni_nie;
	}


	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApe1() {
		return ape1;
	}


	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}


	public String getApe2() {
		return ape2;
	}


	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}


	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public boolean isFijo() {
		return fijo;
	}


	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}


	public boolean isTemporal() {
		return temporal;
	}


	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}


	public boolean isAutonomo() {
		return autonomo;
	}


	public void setAutonomo(boolean autonomo) {
		this.autonomo = autonomo;
	}


	public boolean isOtros() {
		return otros;
	}


	public void setOtros(boolean otros) {
		this.otros = otros;
	}


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getPoblacion() {
		return poblacion;
	}


	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	@Override
	public String toString() {
		return "Cliente [dni_nie=" + dni_nie + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2
				+ ", fechaNacimiento=" + fechaNacimiento + ", fijo=" + fijo + ", temporal=" + temporal + ", autonomo="
				+ autonomo + ", otros=" + otros + ", profesion=" + profesion + ", domicilio=" + domicilio
				+ ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal + "]";
	}


	
}
