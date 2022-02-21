package com.crm.pojos;

import java.sql.Timestamp;

public class Encargo {
private String asunto,email,receptor,cliente,dniNie,cliente2,dniNie2,domicilio;
private Timestamp fecha;

public Encargo() {}

public Encargo(String asunto, String email, String receptor, String cliente, String dniNie, String cliente2,
		String dniNie2, String domicilio, Timestamp fecha) {
	super();
	this.asunto = asunto;
	this.email = email;
	this.receptor = receptor;
	this.cliente = cliente;
	this.dniNie = dniNie;
	this.cliente2 = cliente2;
	this.dniNie2 = dniNie2;
	this.domicilio = domicilio;
	this.fecha = fecha;
}

public String getAsunto() {
	return asunto;
}

public void setAsunto(String asunto) {
	this.asunto = asunto;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getReceptor() {
	return receptor;
}

public void setReceptor(String receptor) {
	this.receptor = receptor;
}

public String getCliente() {
	return cliente;
}

public void setCliente(String cliente) {
	this.cliente = cliente;
}

public String getDniNie() {
	return dniNie;
}

public void setDniNie(String dniNie) {
	this.dniNie = dniNie;
}

public String getCliente2() {
	return cliente2;
}

public void setCliente2(String cliente2) {
	this.cliente2 = cliente2;
}

public String getDniNie2() {
	return dniNie2;
}

public void setDniNie2(String dniNie2) {
	this.dniNie2 = dniNie2;
}

public String getDomicilio() {
	return domicilio;
}

public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
}

public Timestamp getFecha() {
	return fecha;
}

public void setFecha(Timestamp fecha) {
	this.fecha = fecha;
}

@Override
public String toString() {
	return "Encargo [asunto=" + asunto + ", email=" + email + ", receptor=" + receptor + ", cliente=" + cliente
			+ ", dniNie=" + dniNie + ", cliente2=" + cliente2 + ", dniNie2=" + dniNie2 + ", domicilio=" + domicilio
			+ ", fecha=" + fecha + "]";
}




}
