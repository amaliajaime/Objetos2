package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductoTestCase {

	@Test
	public void testConstructor() {
		Producto producto = new Producto(2.0f,"Monitor");
		assertTrue(producto.precio()==2.0);
		assertTrue(producto.getNombre().equals("Monitor"));
	}

}
