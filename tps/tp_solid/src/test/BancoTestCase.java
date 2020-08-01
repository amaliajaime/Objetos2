package test;

import static org.junit.Assert.*;
import model.Banco;
import model.Cliente;
import model.Propiedad;
import model.TipoDeSolicitudDeCredito;

import org.junit.Before;
import org.junit.Test;

public class BancoTestCase {
	private Banco banco;
	private Cliente cliente ;
	private Cliente clienteB;
	private Propiedad propiedad;
	private TipoDeSolicitudDeCredito solicitud;

	@Before
	public void setUp() {
		banco=new Banco(); 
		cliente=new Cliente("Alan", "Cortez", "Armenia 234", 25, 15000d);
		clienteB= new Cliente("Javier", "Lopez", "Toay 5637", 28, 28000d);
		banco.agregarCliente(cliente);
		propiedad = new Propiedad ("Casa Antigua", "Av.Mitre 330", 1569800d); 
		solicitud = new TipoDeSolicitudDeCredito (cliente, 1500, 12);
	}
	
	
	@Test
	public void testConstructor () {
		assertTrue(solicitud.esAceptable);
		//Comentario test
	}


}
