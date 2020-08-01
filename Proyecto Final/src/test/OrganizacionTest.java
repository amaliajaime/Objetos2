package test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import src.Foto;
import src.FuncionImprimirReporte;
import src.FuncionLanzarAlerta;
import src.FuncionalidadExterna;
import src.Muestra;
import src.ObservadorMuestra;
import src.ObservadorZonaDeCobertura;
import src.Organizacion;
import src.Ubicacion;
import src.Verificacion;
import src.ZonaDeCobertura;
	

public class OrganizacionTest {

	private Organizacion organizacion;
	private Ubicacion ubicacion;
	private ZonaDeCobertura zona1;
	private Ubicacion epicentro;
	private Double radio;
	private Muestra muestra;
	private Muestra otraMuestra;
	private List<Muestra>muestras;

	private Foto foto;
	private Verificacion verificacion1;
	private Muestra muestra1;
	ObservadorZonaDeCobertura observadorZona;
	ObservadorMuestra observadorMuestra;
	
	@Before
	public void setUp() {
	 
	  this.ubicacion = new Ubicacion(0.1,0.2);
	  this.organizacion = new Organizacion(ubicacion,"Ambiental",2);	 
	  this.zona1 = new ZonaDeCobertura("verde", epicentro, radio);

	  LocalDate fecha = LocalDate.of(2018, 1, 1);
	  ubicacion = mock(Ubicacion.class);
	  foto = mock(Foto.class);
	  verificacion1 = mock(Verificacion.class);
	  muestra1= new Muestra( fecha, ubicacion, foto, verificacion1);
	  observadorMuestra= zona1;
	  observadorZona=organizacion;
	} 
	
	@Test
	public void testOrganizacionSabeCualEsSuTipo() {
		
		assertTrue(this.organizacion.getTipoDeOrganizacion().equals("Ambiental"));
		assertFalse(this.organizacion.getTipoDeOrganizacion().equals("Umbrella"));
	}
	
	@Test
	public void testOrganizacionConoceLaCantidadDeEmPleadosQueTiene() {
		
		int cantEmpleados= (organizacion.getCantidadDeEmpleados());
		
		assertTrue(cantEmpleados == 2);
	}
	
	@Test
	public void testOrganizacionConoceSuUbicacion() {
		
		assertTrue(this.organizacion.getUbicacion().equals(this.ubicacion));
	}
	
	@Test 
	public void testOrganizacionTieneUnaListaDeOrganizacionesEnLasQueEstaRegistradaInicialmemteVacia() {
		
		assertTrue(this.organizacion.getZonasDeCoberturaRegistradas().isEmpty());
	}
	
	@Test
	public void testOrganizacionRegistraUnaZonaDeCoberturaEnSuListaDeZonasRegistradasVacia() {
		
		this.organizacion.agregarZonaReg(this.zona1);
		
		assertTrue(this.organizacion.getZonasDeCoberturaRegistradas().contains(this.zona1));
	}
	
	@Test
	public void testOrganizacionEliminaUnaZonaDeCoberturaEnSuListaDeZonasRegistradasConUnaZona() {
		
		this.organizacion.agregarZonaReg(this.zona1);
		this.organizacion.eliminarZonareg(this.zona1);
		
		assertTrue(this.organizacion.getZonasDeCoberturaRegistradas().isEmpty());
	}
	
	public void testOrganizacionRealizaFuncionalidadExternaLuegoDeQueUnaZonaDeCoberturaEnLaQueEstaRegistradaAgregaUnaMuestra() {
		Organizacion organizacionSpy=Mockito.spy(organizacion);
		ZonaDeCobertura zonaSpy= Mockito.spy(zona1);
		Muestra muestraSpy= Mockito.spy(muestra1);
		organizacionSpy.agregarZonaReg(zonaSpy);
		zonaSpy.agregarMuestra(muestraSpy);
		
		
		Mockito.verify(organizacionSpy).agregarZonaReg(zonaSpy);
		Mockito.verify(zonaSpy).agregarObservador(organizacionSpy);
		Mockito.verify(zonaSpy).agregarMuestra(muestraSpy);
		Mockito.verify(muestraSpy).agregarObservador(zonaSpy);
		Mockito.verify(zonaSpy).notificarAgregueMuestra(muestraSpy);
		Mockito.verify(observadorZona).actualizarAgregueMuestra(zonaSpy, muestraSpy);		
		Mockito.verify(organizacionSpy.obtenerFuncionalidad()).nuevoEvento(organizacionSpy,zonaSpy,muestraSpy);
		}
	@Test
	
	public void testOrganizacionRealizaFuncionalidadExternaLuegoDeQueSeAgregaUnaVerificacionAUnaMuestraQueseEncuentraEnUnaDelasZonasDeCoberturaDelaListaOrganizacion() {
	
		/*Organizacion organizacionSpy=Mockito.spy(organizacion);
		ZonaDeCobertura zonaSpy= Mockito.spy(zona1);
		Muestra muestra= Mockito.spy(muestra1);
		*/
		Verificacion verificacion=mock(Verificacion.class);
		organizacion.agregarZonaReg(zona1);
		zona1.agregarMuestra(muestra1);
		muestra1.agregarVerificacion(verificacion);
		
		
		assertTrue(organizacion.obtenerFuncionalidad()== new FuncionImprimirReporte() );
		/*Mockito.verify(organizacionSpy).agregarZonaReg(zonaSpy);
		Mockito.verify(zonaSpy).agregarObservador(organizacionSpy);
		Mockito.verify(zonaSpy).agregarMuestra(muestra);
		Mockito.verify(muestra).agregarObservador(zonaSpy);
		Mockito.verify(zonaSpy).notificarAgregueMuestra(muestra);
		Mockito.verify(muestra).agregarVerificacion(verificacion);
		Mockito.verify(muestra).notificarMuestraAZonaAgregoVerificacion();
		Mockito.verify(zonaSpy).notificarMuestraAgregoVerificacion(muestra);
		Mockito.verify(organizacionSpy).actualizarMuestraAgregoVerificacion(zonaSpy, muestra);
		Mockito.verify(organizacionSpy.obtenerFuncionalidad()).nuevoEvento(organizacionSpy,zonaSpy,muestra);
		*/
		}
}