package com.crm.pojos;

public class HipotecaDatos {
	private String valorAdquisicion;
	private String finalidad;

	public HipotecaDatos( String finalidad, String valorAdquisicion) {
	
		this.finalidad = finalidad;
		this.valorAdquisicion = valorAdquisicion;
	}

	
	

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public String getValorAdquisicion() {
		return valorAdquisicion;
	}

	public void setValorAdquisicion(String valorAdquisicion) {
		this.valorAdquisicion = valorAdquisicion;
	}





}
