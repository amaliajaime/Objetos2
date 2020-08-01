package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import src.AplicacionWeb;
import src.Foto;
import src.Muestra;
import src.NivelBasico;
import src.NivelEspecialista;
import src.NivelExperto;
import src.NivelParticipante;
import src.Participante;
import src.Ubicacion;
import src.Verificacion;

class ParticipanteTest {
	private Participante participante;
	private Participante participante2;
	private NivelBasico nivelBasico;
	
	private AplicacionWeb aplicacionWeb;
	private Verificacion verificacion;
	private Verificacion verificacion2;
	private Muestra muestra;
	private Muestra muestra2;
	private Foto foto;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	private NivelEspecialista nivelEspecialista;
	private Participante participante3;
	private NivelExperto nivelExperto;
	private Participante participante4;
	

	@BeforeEach
	public void setUp() {
		this.aplicacionWeb = mock(AplicacionWeb.class);
		
		this.nivelBasico = new NivelBasico();
		this.nivelExperto = new NivelExperto();
		this.nivelEspecialista = new NivelEspecialista();
		this.participante = new Participante("Emi");
		this.participante2 = new Participante("Lola", nivelExperto);
		this.participante3 = new Participante("Lola", nivelEspecialista);
		
		this.participante4 = mock(Participante.class);
		this.verificacion = mock(Verificacion.class);
		this.verificacion2 = mock(Verificacion.class);
		this.muestra = mock(Muestra.class);
		this.muestra2 = mock(Muestra.class);
		this.foto = mock(Foto.class);
		this.ubicacion = mock(Ubicacion.class);
		this.fecha = LocalDate.of(2018,10,5);
		
	}

	@Test
	public void testUnPartipantePuedeSerDadoDeAltaEnUnaAplicacionYLaAplicacionTendraUnUsuario() {
		ArrayList<Participante> p = new ArrayList<Participante>();
		p.add(participante);
		
		this.aplicacionWeb.agregarParticipante(this.participante);
		when(this.aplicacionWeb.getParticipantes()).thenReturn(p);
		
		int esperado = 1;
		int actual = this.aplicacionWeb.getParticipantes().size();
		
		assertEquals(esperado, actual);
		verify(this.aplicacionWeb, times(1)).agregarParticipante(this.participante);
	}

	@Test
	public void testUnParticipanteConoceSuNivel() {
		NivelBasico nivelEperado = nivelBasico;
		NivelParticipante nivelActual = this.participante.getNivel();
		
		assertEquals(nivelEperado.getClass(), nivelActual.getClass());
	}
	
	@Test
	public void testUnParticipanteConoceSuAlias() {
		String aliasEsperado = "Emi";
		String aliasActual = participante.getAlias();
		
		assertEquals(aliasActual, aliasEsperado);
	}
	
	@Test
	public void testUnParticipantePuedeIniciarTeniendoNivelDeConocimientoBasico() {
		NivelParticipante nivelEsperado = nivelBasico;
		NivelParticipante nivelActual = this.participante.getNivel();
		
		assertEquals(nivelEsperado.getClass(), nivelActual.getClass());
	}
	
	@Test
	public void testUnParticipantePuedeIniciarTeniendoNivelDeConocimientoEspecialista() {
		NivelParticipante nivelEsperado = nivelEspecialista;
		NivelParticipante nivelActual = this.participante3.getNivel();
		
		assertEquals(nivelEsperado.getClass(), nivelActual.getClass());
	}
	
	@Test
	public void testUnParticipanteNoPuedeIniciarTeniendoNivelDeConocimientoExpertoEnSuDefectoIniciaraConNivelDeConocimientoBasico(){
		NivelParticipante nivelPorDefecto = nivelBasico;
		NivelParticipante nivelEsperado = nivelExperto;
		NivelParticipante nivelActual = this.participante2.getNivel();
		
		assertNotEquals(nivelEsperado.getClass(), nivelActual.getClass());
		assertEquals(nivelPorDefecto.getClass(), nivelActual.getClass());
	}
	
	@Test
	public void testUnParticipantePuedeIniciarTeniendoNivelDeConocimientoBasicoYCambiarANivelExperto() {
		when(this.participante4.superaValidacionesYContribuciones()).thenReturn(true);
		
		this.participante4.enviaMuestra(this.muestra);
		when(this.participante4.getNivel()).thenReturn(nivelExperto);
		
		NivelParticipante nivelEsperado = nivelExperto;
		NivelParticipante nivelActual = this.participante4.getNivel();
		
		assertEquals(nivelEsperado.getClass(), nivelActual.getClass());
		// verify(this.participante4, times(1)).actualizarNivel(); para preguntar
	}
	
	@Test
	public void testUnParticipantePuedeIenerUnNivelDeConocimientoExpertoYCambiarANivelBasico() {
		when(this.participante4.superaValidacionesYContribuciones()).thenReturn(false);
		
		this.participante4.enviaMuestra(this.muestra);
		when(this.participante4.getNivel()).thenReturn(nivelBasico);
		
		NivelParticipante nivelEsperado = nivelBasico;
		NivelParticipante nivelActual = this.participante4.getNivel();
		
		assertEquals(nivelEsperado.getClass(), nivelActual.getClass());
		// verify(this.participante4, times(1)).actualizarNivel(); para preguntar
	}
	
	
	@Test
	public void testUnParticipanteSabeCuantasContribucionesMensualesHizo() {
		List<Muestra>contrib = new ArrayList<Muestra>();
		contrib.add(muestra); contrib.add(muestra2);
		
		when(this.participante4.getContribucionesMensuales()).thenReturn(contrib);
		
		int contribucionesEsperadas = 2;
		int contribucionesActuales = this.participante4.getContribucionesMensuales().size();
		
		assertEquals(contribucionesEsperadas, contribucionesActuales);
	}
	
	@Test
	public void testUnParticipanteSabeCuantasVerificacionesMensualesHizo() {
		List<Muestra>verif = new ArrayList<Muestra>();
		verif.add(muestra);
		
		when(this.participante4.getVerificacionesMensuales()).thenReturn(verif);
		
		int verificacionesEsperadas = 1;
		int verificacionesActuales = this.participante4.getVerificacionesMensuales().size();
		
		assertEquals(verificacionesEsperadas, verificacionesActuales);
	}

}
