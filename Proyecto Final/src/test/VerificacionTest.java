package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.mockito.*;
import org.mockito.cglib.core.Local;
import org.junit.Before;
import org.junit.Test;

import src.*;

public class VerificacionTest {
	
	private Verificacion verificacion;
	private AplicacionWeb aplicacionWeb;
	private Muestra muestra;
	private Participante participante;
	private Participante participante2;
	private NivelBasico nivelBasico;
	private Foto foto;
	private Ubicacion ubicacion;
	private LocalDate fecha;
	private LocalDate fecha2;
	private Verificacion verificacion2;

	@Before
	public void setUp() {
		fecha = LocalDate.now();
		fecha2 = LocalDate.of(2018, 10, 12);
		nivelBasico = new NivelBasico();
		participante = new Participante("Emi");
		participante2 = new Participante("Lola");
		verificacion = new Verificacion("Vinchuca", fecha, participante);
		verificacion2 = new Verificacion("Vinchuca", fecha2, participante2);
		aplicacionWeb = new AplicacionWeb();
	}
	
	
	@Test
	public void testUnaVerificacionConoceSuTipo() {
		String tipoEsperado = "Vinchuca";
		String tipoActual = this.verificacion.getTipo();
		
		assertEquals(tipoEsperado, tipoActual);
	}

	@Test
	public void testUnaVerificacionConoceSuFecha() {
		LocalDate fechaEsperada = fecha;
		LocalDate fechaActual = this.verificacion.getFecha();
		
		assertEquals(fechaEsperada, fechaActual );
	}
	
	@Test
	public void testUnaVerificacionConoceSuParticipante() {
		Participante participanteEsperado = participante;
		Participante participanteActual = this.verificacion.getParticipante();
		
		assertEquals(participanteEsperado, participanteActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeVerdaderoSiFueVerificadaPorUnParticipante() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion.estaVerificada(participante);
		
		assertEquals(respuestaEsperada, respuestaActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeFalsoSiNoFueVerificadaPorUnParticipante() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion.estaVerificada(participante2);
		
		assertNotEquals(respuestaEsperada, respuestaActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeVerdaderoSiFueVerificadaMensualmentePorUnParticipante() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion.estaVerificadaMensualmente(participante);
		
		assertEquals(respuestaEsperada, respuestaActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeFalsoSiNoFueVerificadaPorUnParticipanPeroSiMensualmente() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion.estaVerificadaMensualmente(participante2);
		
		assertNotEquals(respuestaEsperada, respuestaActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeFalsoSiFueVerificadaPorUnParticipantePeroNoMensualmente() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion2.estaVerificadaMensualmente(participante2);
		
		assertNotEquals(respuestaEsperada, respuestaActual);
	}
	
	@Test
	public void testUnaVerificacionRespondeFalsoSiNoFueVerificadaPorUnParticipanteNiTampocoMensualmente() {
		boolean respuestaEsperada = true;
		boolean respuestaActual = this.verificacion2.estaVerificadaMensualmente(participante);
		
		assertNotEquals(respuestaEsperada, respuestaActual);
	}

}