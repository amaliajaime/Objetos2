package model;

public class Producto {
	
	private float precio;
	private String nombre;
	
	public Producto(float precio, String nombre) {
		this.precio = precio;
		this.nombre=nombre;
	}
	
	public float precio() {
		return this.precio;
	}
	
	public String getNombre() {
		return this.nombre;
	}

}
