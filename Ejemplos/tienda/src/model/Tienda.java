package model;

import java.util.ArrayList;
/**
 * Representa al comercio que administra los productos electrónicos
 * @author dtorres
 *
 */
public class Tienda {
	
	private ArrayList<Producto> inventario; 
	
	public Tienda() {
		this.inventario= new ArrayList<Producto>();
	}
/**
 * Retorna el capital de los productos que contiene la tienda.
 * @return un numero que expresa el valor monetario
 */
	public float calcularCapital() {
		float resultado = 0;
		for (Producto producto : inventario) {
			resultado+= producto.precio();
		}
		
		return resultado;
		
	}

	public void agregar(Producto producto) {
		this.inventario.add(producto);
		
	}
	
	

}
