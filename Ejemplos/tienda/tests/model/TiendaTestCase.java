package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TiendaTestCase {
	
	@Test
	public void testConstructor() {
		//"instanciar una tienda nueva"
		Tienda miTienda = new Tienda();
		//"consultar su capital"
		float result = miTienda.calcularCapital();
		//asegurarmet que el capital sea cero"
		assertTrue(result==0.0);
		
	}
	
	@Test
	public void agregarProductoATiendaVacia() {
		/**
		 * Si tengo una Tienda que no tiene productos
		 * y le agrego un producto con precio igual a 1
		 * cuando le envie el mensaje calcularCapital a la tienda
		 * Debe retornar 1.
		 */
		
		Tienda miTienda = new Tienda();
		Producto producto = new Producto(1,"Teclado");
		miTienda.agregar(producto);
		float result = miTienda.calcularCapital();
		assertTrue(result==1);
		
	}
	
	@Test
	public void testAgregarProductoATiendaConProductos() {
		Tienda miTienda = new Tienda();
		Producto teclado = new Producto(1,"Teclado");
		Producto monitor = new Producto(3, "Monitor");
		Producto pad = new Producto(2, "pad");
		miTienda.agregar(teclado);
		miTienda.agregar(monitor);
		miTienda.agregar(pad);
		float result = miTienda.calcularCapital();
		assertTrue(result==6.0);
	}
	
	


}
