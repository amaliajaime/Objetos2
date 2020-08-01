package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static  org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import src.Foto;
import src.Muestra;
import src.ObservadorMuestra;
import src.ObservadorZonaDeCobertura;
import src.Ubicacion;
import src.Verificacion;
import src.ZonaDeCobertura;

public class ZonaDeCoberturaTest {

	private List<ZonaDeCobertura>zonas;
	private ZonaDeCobertura zona1;
	private ZonaDeCobertura zona2;
	private ZonaDeCobertura zona3;
	private Ubicacion epicentro;
	private Ubicacion otroEpicentro;
	private Ubicacion unTercerEpicentro;

	private Double radio;
	private Double radio2;
	private Double radio3;
	private String nombre;
	private Muestra muestra;
	private ObservadorZonaDeCobertura observador;
	private ObservadorMuestra observadorMuestra;
	private Foto foto;
	private Verificacion verificacion;
	private String nombre2;
	private String nombre3;
	private LocalDate fecha;

	@BeforeEach
	
	public void setUp(){
		
		this.nombre = "cuarentena";
		this.nombre2 = "peligro";
		this.nombre3 = "insectosPeligrosos";		
		this.epicentro = new Ubicacion(0.1,0.2);
		this.otroEpicentro = new Ubicacion(0.1,0.1);
		this.unTercerEpicentro = new Ubicacion(0.9,0.8);
		this.radio = 1.8;
		this.radio2 = (0.1);
		this.radio3 = (1.8);
		this.zona1 = new ZonaDeCobertura(this.nombre, this.epicentro, this.radio);
		this.zona2 = new ZonaDeCobertura(this.nombre2, this.otroEpicentro, this.radio2);
		this.zona3 = new ZonaDeCobertura(this.nombre3, this.unTercerEpicentro, this.radio3);

		this.zonas = new ArrayList<ZonaDeCobertura>();
		this.zonas.add(this.zona2);
		this.zonas.add(this.zona3);

		this.fecha = LocalDate.of(2018, 1, 1);
		this.foto = mock(Foto.class);
		this.verificacion = mock(Verificacion.class);
		this.muestra = new Muestra(fecha, epicentro, foto, verificacion);
		
		this.observador = mock(ObservadorZonaDeCobertura.class);
		this.observadorMuestra = this.zona1;
		}
	
	@Test
	public void testUnaZonaConoceSuNombre() {
		String nombreEsperado = "cuarentena" ;
		String nombreActual = this.zona1.getNombre() ;
		
		assertEquals(nombreEsperado, nombreActual) ;
	}
	
	@Test
	public void testUnaZonaConoceCualEsSuEpicentro() {
		Ubicacion epicentroEsperado = this.epicentro ;
		Ubicacion epicentroActual = this.zona1.getEpicentro() ;
		
		assertEquals(epicentroEsperado, epicentroActual);
	}
	
	@Test
	public void testUnaZonaConoceCualEsSuRadio() {
		Double radioEsperado = this.radio ;
		Double radioActual = this.zona1.getRadio() ;
		
		assertEquals(radioEsperado, radioActual);
	}
	
	@Test 
	public void testUnaZonaIniciaConUnaListaDeMuestrasReportadasVacia(){
		boolean resultadoEsperado = true ;
		boolean resultadoActual = this.zona1.getMuestrasReportadas().isEmpty() ;
				
		assertEquals(resultadoEsperado, resultadoActual);
	}
	
	@Test
	public void testUnaZonaDeCoberturaIniciaConConUnaListaDeObservadoresVacia() {
		boolean resultadoEsperado = true ;
		boolean resultadoActual = this.zona1.getObservadores().isEmpty() ;
				
		assertEquals(resultadoEsperado, resultadoActual);
	}
	
	@Test
	public void testUnaZonaDeCoberturaPuedeSaberSiUnaMuestraEstaDentroDeSuZona() {
		boolean resultadoEsperado = true ;
		boolean resultadoActual = this.zona1.puedeCargarMuestra(muestra) ;
				
		assertEquals(resultadoEsperado, resultadoActual);	
	}
	
	@Test
	public void testUnaZonaDeCoberturaPuedeSaberSiUnaMuestraNoEstaDentroDeSuZona() {
		boolean resultadoEsperado = true ;
		boolean resultadoActual = this.zona2.puedeCargarMuestra(this.muestra) ;
				
		assertNotEquals(resultadoEsperado, resultadoActual);	
	}
	
	@Test
	public void testSiUnaZonaDeCoberturaEncuentraUnaMuestraDentroDeSuZonaDeCoberturaLaAgregaASuListaDeMuestrasReportadas() {
		
		this.zona1.agregarMuestraSiPerteneceAZona(this.muestra);
		assertTrue(this.zona1.getMuestrasReportadas().contains(this.muestra));
	}
	
	@Test
	public void testSiUnaZonaDeCoberturaNoEncuentraUnaMuestraDentroDeSuZonaDeCoberturaNoLaAgregaASuListaDeMuestrasReportadas() {
		
		this.zona2.agregarMuestraSiPerteneceAZona(this.muestra);
		assertFalse(zona2.getMuestrasReportadas().contains(this.muestra));
	}
	
	@Test
	public void testZonaDeCoberturaPuedeAgregarUnObservadorYLoContendraEnSuListaDeObservadores(){
		this.zona1.agregarObservador(this.observador);
		
		boolean observadorEsperado = true ;
		boolean observadorActual = this.zona1.getObservadores().contains(this.observador) ;
		
		assertEquals(observadorEsperado, observadorActual);
	}
	
	@Test
	public void testUnaZonaDeCoberturaConUnObservadorQueLoEliminaTendraUnaListaVaciaDeObservadores() {
		this.zona1.agregarObservador(this.observador);
		this.zona1.removerObservador(this.observador);
		
		boolean observadorEsperado = true ;
		boolean observadorActual = this.zona1.getObservadores().isEmpty() ;
		
		assertEquals(observadorEsperado, observadorActual);
	}
	
	@Test
	public void testVerificarALosObservadoresQueSeAgregoUnaMuestraEnLaZonaDeCobertura() {
	
		ZonaDeCobertura zona1spy= Mockito.spy(this.zona1);
		zona1spy.agregarObservador(this.observador);
		zona1spy.agregarMuestraSiPerteneceAZona(this.muestra);
		
		verify(zona1spy).agregarMuestra(this.muestra);
		verify(zona1spy).notificarAgregueMuestra(this.muestra);
		verify(this.observador).actualizarAgregueMuestra(zona1spy, this.muestra);
	}
	
	@Test
	public void testVerificarALosObservadoresQueSeAgregoUnaVerificacionAUnaMuestraQuePerteneceAZona1() {
		
		Verificacion verificacion = mock(Verificacion.class);
		ZonaDeCobertura zona1spy = Mockito.spy(this.zona1);
		Muestra muestraSpy = Mockito.spy(this.muestra);
		zona1spy.agregarMuestra(muestraSpy);
		muestraSpy.agregarObservador(this.zona1);
		muestraSpy.agregarVerificacion(verificacion);
		
		verify(muestraSpy).notificarMuestraAZonaAgregoVerificacion();
		verify(zona1spy).actualizarMuestrasReportadasAgregaronVerif(muestraSpy);
		verify(zona1spy).notificarMuestraAgregoVerificacion(muestraSpy);	
	}
	
	@Test
	public void testUnaZonaConoceUnaListaDeZonasDeCoberturaQueSeSolapanASiMisma() {
		List<ZonaDeCobertura>zonasEsperadas = new ArrayList<ZonaDeCobertura>();
		zonasEsperadas.add(this.zona2);
		
		assertTrue(this.zona1.getZonasDeCoberturaSolapadas(this.zonas).containsAll(zonasEsperadas));	
	}	
}