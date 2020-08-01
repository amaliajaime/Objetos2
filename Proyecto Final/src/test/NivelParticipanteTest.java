package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.NivelBasico;
import src.NivelEspecialista;
import src.NivelExperto;
import src.NivelParticipante;


public class NivelParticipanteTest {

	private NivelParticipante nivelBasico;
	private NivelParticipante nivelExperto;
	private NivelParticipante nivelEspecialista;
	
	@BeforeEach
	public void setUp() {
		nivelBasico = new NivelBasico();
		nivelExperto = new NivelExperto();
		nivelEspecialista = new NivelEspecialista();
	}
	
	@Test
	public void testNivelBasicoSeComparaConOtroNivelBasicoYDevuelve0(){
		NivelParticipante otroNivelBasico = new NivelBasico();
		
		assertTrue(nivelBasico.compareTo(otroNivelBasico) == 0);
	}
	
	@Test
	public void testNivelExpertoSeComparaConOtroNivelExpertoYDevuelve0(){
		NivelParticipante otroNivelExperto = new NivelExperto();
		
		assertTrue(nivelExperto.compareTo(otroNivelExperto) == 0);
	}
	
	@Test
	public void testNivelEspecialistaSeComparaConOtroNivelEspecialistaYDevuelve0(){
		NivelParticipante otroNivelEspecialista = new NivelEspecialista();
		
		assertTrue(nivelEspecialista.compareTo(otroNivelEspecialista) == 0);
	}
	
	@Test
	public void testNivelBasicoSeComparaConNivelExpertoYDevuelve1Negativo(){
		assertTrue(nivelBasico.compareTo(nivelExperto) == -1);
	}
	
	@Test
	public void testNivelBasicoSeComparaConNivelEspecialistaYDevuelve1Negativo(){
		assertTrue(nivelBasico.compareTo(nivelEspecialista) == -1);
	}
	
	@Test
	public void testNivelExpertoSeComparaConNivelBasicoYDevuelve1(){
		assertTrue(nivelExperto.compareTo(nivelBasico) == 1);
	}
	
	@Test
	public void testNivelExpertoSeComparaConNivelEspecialistaYDevuelve0(){
		assertTrue(nivelExperto.compareTo(nivelEspecialista) == 0);
	}
	
	@Test
	public void testNivelEspecialistaSeComparaConNivelBasicoYDevuelve1(){
		assertTrue(nivelEspecialista.compareTo(nivelBasico) == 1);
	}
	
	@Test
	public void testNivelEspecialistaSeComparaConNivelExpertoYDevuelve0(){
		assertTrue(nivelEspecialista.compareTo(nivelExperto) == 0);
	}
}
