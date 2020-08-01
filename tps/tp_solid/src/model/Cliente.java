package model;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private String direccion;
	private Integer edad;
	private double sueldoNetoMensual; 
	
	
	public Cliente (String nonbre, String apellido, String direccion, Integer edad, double sueldoNetoMensual) {
		this.nombre = nombre; 
		this.apellido = apellido;
		this.direccion = direccion;
		this.edad = edad;
		this.sueldoNetoMensual = sueldoNetoMensual;
	}
	
	public double getSueldoMensual (){
		return this.sueldoNetoMensual;
	}

	public double getSueldoAnual () {
		return this.sueldoNetoMensual * 12;
	}
}
