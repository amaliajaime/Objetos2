package model;

public class Propiedad {
	private String descripcion;
	private String direccion;
	private double valorFizcal ;
	
	public Propiedad (String descripcion, String direccion, double valorFizcal) {
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.valorFizcal = valorFizcal;
	}
	
	public Double getValorFizcal () {
		return this.valorFizcal;
	}
}
