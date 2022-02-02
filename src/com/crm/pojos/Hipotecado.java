package com.crm.pojos;

import java.sql.Timestamp;

public class Hipotecado {
	private String actividad, antiguedad, ape1, ape2, codigoPostal, comentarios, contactoEmpresa, direccion,
			direccionEmpresa, dninie, domicilio, finalidad, nombre, nombreEmpresa, otros, otrosBienes, poblacion,
			profesion, vinculacion;
	private boolean alquiler, autonomo, contratoPrivado, editar, escritura, fijo, otrasCosas, otrosGastos, padres,
			propiedad, temporal;
	private int cargas, cargasVivienda, gastosAlquiler, gastosHipoteca, id, ingresosFijos, ingresosVariables, plazo,
			puesto, valor, valorAdquisicion, valorImporte;
	private Timestamp fechaNacimiento;
	
	
	public Hipotecado(String actividad, String antiguedad, String ape1, String ape2, String codigoPostal,
			String comentarios, String contactoEmpresa, String direccion, String direccionEmpresa, String dninie,
			String domicilio, String finalidad, String nombre, String nombreEmpresa, String otros, String otrosBienes,
			String poblacion, String profesion, String vinculacion, boolean alquiler, boolean autonomo,
			boolean contratoPrivado, boolean editar, boolean escritura, boolean fijo, boolean otrasCosas,
			boolean otrosGastos, boolean padres, boolean propiedad, boolean temporal, int cargas, int cargasVivienda,
			int gastosAlquiler, int gastosHipoteca, int id, int ingresosFijos, int ingresosVariables, int plazo,
			int puesto, int valor, int valorAdquisicion, int valorImporte, Timestamp fechaNacimiento) {
		
		this.actividad = actividad;
		this.antiguedad = antiguedad;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.codigoPostal = codigoPostal;
		this.comentarios = comentarios;
		this.contactoEmpresa = contactoEmpresa;
		this.direccion = direccion;
		this.direccionEmpresa = direccionEmpresa;
		this.dninie = dninie;
		this.domicilio = domicilio;
		this.finalidad = finalidad;
		this.nombre = nombre;
		this.nombreEmpresa = nombreEmpresa;
		this.otros = otros;
		this.otrosBienes = otrosBienes;
		this.poblacion = poblacion;
		this.profesion = profesion;
		this.vinculacion = vinculacion;
		this.alquiler = alquiler;
		this.autonomo = autonomo;
		this.contratoPrivado = contratoPrivado;
		this.editar = editar;
		this.escritura = escritura;
		this.fijo = fijo;
		this.otrasCosas = otrasCosas;
		this.otrosGastos = otrosGastos;
		this.padres = padres;
		this.propiedad = propiedad;
		this.temporal = temporal;
		this.cargas = cargas;
		this.cargasVivienda = cargasVivienda;
		this.gastosAlquiler = gastosAlquiler;
		this.gastosHipoteca = gastosHipoteca;
		this.id = id;
		this.ingresosFijos = ingresosFijos;
		this.ingresosVariables = ingresosVariables;
		this.plazo = plazo;
		this.puesto = puesto;
		this.valor = valor;
		this.valorAdquisicion = valorAdquisicion;
		this.valorImporte = valorImporte;
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getActividad() {
		return actividad;
	}


	public void setActividad(String actividad) {
		this.actividad = actividad;
	}


	public String getAntiguedad() {
		return antiguedad;
	}


	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
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


	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	public String getContactoEmpresa() {
		return contactoEmpresa;
	}


	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}


	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}


	public String getDninie() {
		return dninie;
	}


	public void setDninie(String dninie) {
		this.dninie = dninie;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getFinalidad() {
		return finalidad;
	}


	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getOtros() {
		return otros;
	}


	public void setOtros(String otros) {
		this.otros = otros;
	}


	public String getOtrosBienes() {
		return otrosBienes;
	}


	public void setOtrosBienes(String otrosBienes) {
		this.otrosBienes = otrosBienes;
	}


	public String getPoblacion() {
		return poblacion;
	}


	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}


	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


	public String getVinculacion() {
		return vinculacion;
	}


	public void setVinculacion(String vinculacion) {
		this.vinculacion = vinculacion;
	}


	public boolean isAlquiler() {
		return alquiler;
	}


	public void setAlquiler(boolean alquiler) {
		this.alquiler = alquiler;
	}


	public boolean isAutonomo() {
		return autonomo;
	}


	public void setAutonomo(boolean autonomo) {
		this.autonomo = autonomo;
	}


	public boolean isContratoPrivado() {
		return contratoPrivado;
	}


	public void setContratoPrivado(boolean contratoPrivado) {
		this.contratoPrivado = contratoPrivado;
	}


	public boolean isEditar() {
		return editar;
	}


	public void setEditar(boolean editar) {
		this.editar = editar;
	}


	public boolean isEscritura() {
		return escritura;
	}


	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}


	public boolean isFijo() {
		return fijo;
	}


	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}


	public boolean isOtrasCosas() {
		return otrasCosas;
	}


	public void setOtrasCosas(boolean otrasCosas) {
		this.otrasCosas = otrasCosas;
	}


	public boolean isOtrosGastos() {
		return otrosGastos;
	}


	public void setOtrosGastos(boolean otrosGastos) {
		this.otrosGastos = otrosGastos;
	}


	public boolean isPadres() {
		return padres;
	}


	public void setPadres(boolean padres) {
		this.padres = padres;
	}


	public boolean isPropiedad() {
		return propiedad;
	}


	public void setPropiedad(boolean propiedad) {
		this.propiedad = propiedad;
	}


	public boolean isTemporal() {
		return temporal;
	}


	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}


	public int getCargas() {
		return cargas;
	}


	public void setCargas(int cargas) {
		this.cargas = cargas;
	}


	public int getCargasVivienda() {
		return cargasVivienda;
	}


	public void setCargasVivienda(int cargasVivienda) {
		this.cargasVivienda = cargasVivienda;
	}


	public int getGastosAlquiler() {
		return gastosAlquiler;
	}


	public void setGastosAlquiler(int gastosAlquiler) {
		this.gastosAlquiler = gastosAlquiler;
	}


	public int getGastosHipoteca() {
		return gastosHipoteca;
	}


	public void setGastosHipoteca(int gastosHipoteca) {
		this.gastosHipoteca = gastosHipoteca;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIngresosFijos() {
		return ingresosFijos;
	}


	public void setIngresosFijos(int ingresosFijos) {
		this.ingresosFijos = ingresosFijos;
	}


	public int getIngresosVariables() {
		return ingresosVariables;
	}


	public void setIngresosVariables(int ingresosVariables) {
		this.ingresosVariables = ingresosVariables;
	}


	public int getPlazo() {
		return plazo;
	}


	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}


	public int getPuesto() {
		return puesto;
	}


	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}


	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public int getValorAdquisicion() {
		return valorAdquisicion;
	}


	public void setValorAdquisicion(int valorAdquisicion) {
		this.valorAdquisicion = valorAdquisicion;
	}


	public int getValorImporte() {
		return valorImporte;
	}


	public void setValorImporte(int valorImporte) {
		this.valorImporte = valorImporte;
	}


	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
}
