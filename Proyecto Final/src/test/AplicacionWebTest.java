package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;
import  static org.mockito.Mockito.*;

import src.*;

import org.junit.jupiter.api.BeforeEach;
import src.AplicacionWeb;
import src.Foto;
import src.Muestra;
import src.NivelBasico;
import src.Participante;
import src.Ubicacion;
import src.Verificacion;

class AplicacionWebTest {
	private AplicacionWeb aplicacionWeb;
	
	private Muestra muestra;
	private Muestra otraMuestra;
	private Muestra unaTerceraMuestra;
	private Participante participante;
	private Participante participante2;
	private Verificacion verificacion;
	private Verificacion verificacion2;
	private Verificacion verificacion3;
	private Foto foto;
	private Ubicacion ubicacion;
	private Ubicacion otraUbicacion;
	private Ubicacion unaTerceraUbicacion;
	private LocalDate fecha;

	private ZonaDeCobertura zona1;
	private NivelBasico nivelBasico;
	
	private Muestra muestra0;

	
	@BeforeEach
	public void setUp() {
		
		this.ubicacion = mock(Ubicacion.class);
		this.otraUbicacion = mock(Ubicacion.class);
		this.unaTerceraUbicacion = mock(Ubicacion.class);
		this.nivelBasico = new NivelBasico();
		
		this.muestra = mock(Muestra.class) ;
		this.otraMuestra = mock(Muestra.class) ;
		this.unaTerceraMuestra = mock(Muestra.class) ;
		this.zona1 = mock(ZonaDeCobertura.class) ;
		
		this.muestra0 = mock(Muestra.class);
		this.fecha = LocalDate.of(2018,10,5);
		this.foto = mock(Foto.class);
		this.participante = mock(Participante.class);
		this.participante2 = mock(Participante.class);
		this.verificacion = mock(Verificacion.class);
		this.verificacion2 = mock(Verificacion.class);
		this.verificacion3 = mock(Verificacion.class);
		
		
		this.aplicacionWeb = new AplicacionWeb();
	}
	
	@Test
	public void testUnaAplicacionIniciaConCeroParticipantes() {
		int esperado = 0;
		int actual = this.aplicacionWeb.getParticipantes().size();
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void testUnaAplicacionIniciaConCeroMuestras() {
		int esperado = 0;
		int actual = this.aplicacionWeb.getMuestrasCargadas().size();
		
		assertEquals(esperado, actual);
	}
	
	@Test
	public void testUnaAplicacionPuedeAgregarUnaMuestra() {
		
		participante = mock(Participante.class);

		this.aplicacionWeb.agregarMuestra(this.muestra, this.verificacion, this.participante);
		
		assertTrue(this.aplicacionWeb.getMuestrasCargadas().contains(this.muestra));
	}
	
	@Test
	public void testUnaAplicacionPuedeAgregarUnParticipante() {
		this.aplicacionWeb.agregarParticipante(this.participante);
		
		assertTrue (this.aplicacionWeb.getParticipantes().contains(this.participante));
	}
	
	@Test
	public void testAplicacionSoloPuedeCargarUnaVerificacionEnMuestraConUnUsuarioQueNoLoHizoAntes() {		
		
		ArrayList<Verificacion>verificaciones = new ArrayList<Verificacion>();
		verificaciones.add(verificacion); verificaciones.add(verificacion2);
		when(this.muestra0.getVerificaciones()).thenReturn(verificaciones);
		
		when(this.muestra0.tieneVerificacion(participante2)).thenReturn(false);
		when(this.muestra0.getColaborador()).thenReturn(participante);
		
		aplicacionWeb.verificarMuestra(muestra0, verificacion2, participante2);
		
		int esperado = 2;
		int actual = this.muestra0.getVerificaciones().size();
		
		assertEquals(esperado, actual);
		verify(this.muestra0, times(1)).agregarVerificacion(verificacion2);
	}
	
	@Test
	public void testAplicacionPuedeDeterminarCuantasColaboracionesMensualesRealizoUnUsuario() {
		ArrayList<Muestra>contribucion = new ArrayList<Muestra>();
		contribucion.add(this.muestra);
		
		this.aplicacionWeb.agregarMuestra(this.muestra, this.verificacion, this.participante2);
		
		when(this.participante2.getContribucionesMensuales()).thenReturn(contribucion);
		
		int esperado = 1;
		int actual = this.aplicacionWeb.getContribucionesMensualesPorParticipante(this.participante2);
		
		assertEquals(esperado, actual);
		verify(this.participante2, times(1)).enviaMuestra(this.muestra);
	}
	
	public void testAplicacionPuedeDeterminaCuantasVerificacionesMensualesRealizoUnUsuario() {
		ArrayList<Muestra>verificaciones = new ArrayList<Muestra>();
		verificaciones.add(this.muestra);
		when(this.muestra.getColaborador()).thenReturn(participante);
		
		this.aplicacionWeb.verificarMuestra(this.muestra, this.verificacion, this.participante2);
		
		when(this.participante2.getVerificacionesMensuales()).thenReturn(verificaciones);
		
		int esperado = 1;
		int actual = this.aplicacionWeb.getVerificacionesMensualesPorParticipante(this.participante2);
		
		assertEquals (esperado, actual);
		verify(this.participante2, times(1)).veificaMuestra(this.muestra);
	}
	
	@Test
	public void testAplicacionLePideAUnaMuestraQueLeDigaLasMuestrasCercanasAEstaEnUnaDetermDistancia() {
		
		List<Muestra> muestrasEsperadas= new ArrayList<Muestra>();
		muestrasEsperadas.add(otraMuestra);
		
		this.aplicacionWeb.agregarMuestra(otraMuestra, verificacion2, participante2);
		this.aplicacionWeb.agregarMuestra(unaTerceraMuestra, verificacion2, participante2);
		
		when(otraMuestra.getUbicacion()).thenReturn(otraUbicacion);
		when(muestra.getUbicacion()).thenReturn(ubicacion);
		when(unaTerceraMuestra.getUbicacion()).thenReturn(unaTerceraUbicacion);
		when(ubicacion.distanciaA(otraUbicacion)).thenReturn (1.0);
		when(ubicacion.distanciaA(unaTerceraUbicacion)).thenReturn (3.0);
		
		Double valor1 = ubicacion.distanciaA(otraUbicacion);
		Double valor2 = ubicacion.distanciaA(unaTerceraUbicacion);
		
		ArrayList<Muestra> muestrasResultado = aplicacionWeb.muestrasCercanasA(muestra, 2.0);
		
		assertTrue(muestrasResultado.equals(muestrasEsperadas));
		assertFalse(muestrasResultado.contains(unaTerceraMuestra));
	}
	
	@Test
	public void testCuandoSeRegistraUnaNuevaMuestraLaAppSeFijaSiPerteneceAUnaZonaDeCoberturaYSeLaRegistra() {
		aplicacionWeb.agregarMuestra(muestra, verificacion, participante);
		
		assertTrue((zona1.getMuestrasReportadas()).contains(muestra));
	}
	
	@Test
	public void testCuandoSeRegistraUnaNuevaMuestraLaAppvVerficaQuePerteneceAUnaZonaDeCoberturaYSeLaRegistra(){
		ArrayList<Muestra>muestraReportada = new ArrayList<Muestra>();
		muestraReportada.add(muestra);
		
		this.aplicacionWeb.agregarZonaDeCobertura(zona1);
		this.aplicacionWeb.agregarMuestra(muestra, verificacion, participante);
		
		when(zona1.getMuestrasReportadas()).thenReturn(muestraReportada);
		
		assertTrue((zona1.getMuestrasReportadas()).contains(muestra));		
	}
	
	@Test
	public void testAplicacionConoceCualEsSuBuscador() {
		assertTrue(this.aplicacionWeb.getBuscador().getClass() == Buscador.class);
	}
	
}
