package test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.junit.jupiter.api.BeforeEach;
import src.Foto;
import src.Muestra;
import src.NivelBasico;
import src.NivelExperto;
import src.NivelParticipante;
import src.ObservadorMuestra;
import src.Participante;
import src.Ubicacion;
import src.Verificacion;

import static org.junit.Assert.*;

public class MuestraTest {
	
	ObservadorMuestra observador;
	Participante participante1;
	Participante participante2;
	Participante participante3;
	
	NivelParticipante nivelBasico;
	NivelParticipante nivelExperto;
	NivelParticipante nivelEspecialista;
	
	Verificacion verificacion1;
	Verificacion verificacion2;
	Verificacion verificacion3;

	Foto foto;
	Ubicacion ubicacion;

	Muestra muestra;

	@BeforeEach
	public void setUp() {
		LocalDate fecha = LocalDate.of(2018, 1, 1);
		ubicacion = mock(Ubicacion.class);
		foto = mock(Foto.class);
		verificacion1 = mock(Verificacion.class);
		this.muestra = new Muestra( fecha, ubicacion, foto, verificacion1);
		
		participante1 = mock(Participante.class);
		participante2 = mock(Participante.class);
		participante3 = mock(Participante.class);
		
		verificacion2 = mock(Verificacion.class);
		verificacion3 = mock(Verificacion.class);

		observador=mock(ObservadorMuestra.class);
		
		nivelBasico = new NivelBasico();
		nivelExperto = new NivelExperto();
	}

	@Test
	public void testMuestraSabeDeQueTipoDeVinchucaEs() {
		participante1 = mock(Participante.class);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion1.getParticipante()).thenReturn(participante1, participante1, participante1);
		when(participante1.getNivel()).thenReturn(nivelBasico);
	
		String res = muestra.getTipo();
		assertTrue("Vinchuca".equals(res));
		verify(verificacion1, times(2)).getTipo();
		verify(verificacion1, times(2)).getParticipante();
		verify(participante1, times(2)).getNivel();

	}

	@Test
	public void testMuestraConoceSuFechaDeCreacion() {
		LocalDate fechaDeCreacion = LocalDate.of(2018, 1, 1);
		assertTrue(muestra.getFecha().equals(fechaDeCreacion));

		LocalDate fechaDistinta = LocalDate.of(2001, 3, 3);
		assertNotEquals(muestra.getFecha(), fechaDistinta);
	}

	@Test
	public void testMuestraConoceSuUbicacion() {
		Ubicacion ubicacionCargada = muestra.getUbicacion();
		assertEquals(ubicacion, ubicacionCargada);

		Ubicacion ubicacionDistinta = mock(Ubicacion.class);
		assertNotEquals(ubicacionCargada, ubicacionDistinta);
	}

	@Test
	public void testMuestraConoceSuFoto() {
		Foto fotoCargada = muestra.getFoto();
		assertEquals(fotoCargada, foto);

		Foto fotoDistinta = mock(Foto.class);
		assertNotEquals(fotoCargada, fotoDistinta);
	}

	@Test
	public void testMuestraPuedeAgregarUnaVerificacion() {
		
		ArrayList<Verificacion> listaDeLaMuestra = muestra.getVerificaciones();

		assertTrue(listaDeLaMuestra.size() == 1);
		assertEquals(listaDeLaMuestra.get(0), verificacion1);
		
		muestra.agregarVerificacion(verificacion2);
		
		listaDeLaMuestra = muestra.getVerificaciones();
		
		assertTrue(listaDeLaMuestra.size() == 2);
		assertEquals(listaDeLaMuestra.get(1), verificacion2);
	}
	
	@Test
	public void testMuestraConoceQuienEsSuColaborador() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);
		
		Participante res = muestra.getColaborador();
		
		assertEquals(res, participante1);
		verify(verificacion1, times(1)).getParticipante();
		verify(verificacion2, times(0)).getParticipante();
		verify(verificacion3, times(0)).getParticipante();
	}

	@Test

	public void testMuestraPoseeUnaVerificacionDeUsuarioBasicoYSuNivelDeValidacionEsBajoYConoceSuTipo() {	
		when(verificacion1.getTipo()).thenReturn("Vinchuca");

		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(participante1.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Bajo".equals(nivel));
		assertTrue("Vinchuca".equals(tipo));
		verify(verificacion1, times(2)).getTipo();
		verify(verificacion1, times(5)).getParticipante();
		verify(participante1, times(5)).getNivel();
	}
	
	
	@Test
	public void testMuestraPoseeUnaVerificacionDeUsuarioExpertoYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(participante1.getNivel()).thenReturn(nivelExperto);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Vinchuca".equals(tipo));
		
		verify(verificacion1, times(2)).getTipo();
		verify(verificacion1, times(5)).getParticipante();
		verify(participante1, times(5)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeDosVerificacionesDiferentesDeUsuariosBasicosYSuNivelDeValidacionEsMedioYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Medio".equals(nivel));
		assertTrue("Ninguno".equals(tipo));
		
		verify(verificacion1, times(1)).getTipo();
		verify(verificacion1, times(9)).getParticipante();
		verify(participante1, times(9)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeDosVerificacionesIgualesDeUsuariosBasicosYSuNivelDeValidacionEsMedioYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Vinchuca");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Medio".equals(nivel));
		assertTrue("Vinchuca".equals(tipo));
		
		verify(verificacion1, times(1)).getTipo();
		verify(verificacion1, times(9)).getParticipante();
		verify(participante1, times(9)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeDosVerificacionesDiferentesConUnUsuarioExpertoYUnoBasicoYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelExperto);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Cinche Foliada".equals(tipo));
		
		verify(verificacion1, times(0)).getTipo();
		verify(verificacion1, times(4)).getParticipante();
		verify(participante1, times(4)).getNivel();
		
		verify(verificacion2, times(2)).getTipo();
		verify(verificacion2, times(9)).getParticipante();
		verify(participante2, times(9)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeTresVerificacionesDiferentesConUsuariosBasicosYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		when(verificacion3.getTipo()).thenReturn("Ninguno");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		when(participante3.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Ninguno".equals(tipo));
		
		verify(verificacion1, times(1)).getTipo();
		verify(verificacion1, times(13)).getParticipante();
		verify(participante1, times(13)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
		
		verify(verificacion3, times(1)).getTipo();
		verify(verificacion3, times(4)).getParticipante();
		verify(participante3, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeTresVerificacionesConDosIgualesDeUsuariosBasicosYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		when(verificacion3.getTipo()).thenReturn("Vinchuca");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		when(participante3.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Ninguno".equals(tipo));
		
		verify(verificacion1, times(1)).getTipo();
		verify(verificacion1, times(13)).getParticipante();
		verify(participante1, times(13)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
		
		verify(verificacion3, times(1)).getTipo();
		verify(verificacion3, times(4)).getParticipante();
		verify(participante3, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeTresVerificacionesIgualesDeUsuariosBasicosYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Vinchuca");
		when(verificacion3.getTipo()).thenReturn("Vinchuca");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		when(participante3.getNivel()).thenReturn(nivelBasico);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Vinchuca".equals(tipo));
		
		verify(verificacion1, times(1)).getTipo();
		verify(verificacion1, times(13)).getParticipante();
		verify(participante1, times(13)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
		
		verify(verificacion3, times(1)).getTipo();
		verify(verificacion3, times(4)).getParticipante();
		verify(participante3, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeDosVerificacionesDiferentesDeUsuariosBasicosYUnaDeUnUsuarioAltoYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		when(verificacion3.getTipo()).thenReturn("Imagen Poco Clara");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelBasico);
		when(participante3.getNivel()).thenReturn(nivelExperto);

		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Imagen Poco Clara".equals(tipo));
		
		verify(verificacion1, times(0)).getTipo();
		verify(verificacion1, times(6)).getParticipante();
		verify(participante1, times(6)).getNivel();
		
		verify(verificacion2, times(0)).getTipo();
		verify(verificacion2, times(4)).getParticipante();
		verify(participante2, times(4)).getNivel();
		
		verify(verificacion3, times(2)).getTipo();
		verify(verificacion3, times(11)).getParticipante();
		verify(participante3, times(11)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeUnaVerificacionDeUsuarioBasicoYDosDeUsuariosExpertosDiferentesYSuNivelDeValidacionEsAltoYConoceSuTipo() {
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Vinchuca");
		when(verificacion2.getTipo()).thenReturn("Cinche Foliada");
		when(verificacion3.getTipo()).thenReturn("Imagen Poco Clara");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelExperto);
		when(participante3.getNivel()).thenReturn(nivelExperto);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Ninguno".equals(tipo));
		
		verify(verificacion1, times(0)).getTipo();
		verify(verificacion1, times(4)).getParticipante();
		verify(participante1, times(4)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(13)).getParticipante();
		verify(participante2, times(13)).getNivel();
		
		verify(verificacion3, times(1)).getTipo();
		verify(verificacion3, times(4)).getParticipante();
		verify(participante3, times(4)).getNivel();
	}
	
	@Test
	public void testMuestraPoseeUnaVerificacionDeUsuarioBasicoYDosDeUsuariosExpertosIgualesYSuNivelDeValidacionEsAltoYConoceSuTipo() {	
		muestra.agregarVerificacion(verificacion2);
		muestra.agregarVerificacion(verificacion3);
		
		when(verificacion1.getTipo()).thenReturn("Cinche Foliada");
		when(verificacion2.getTipo()).thenReturn("Vinchuca");
		when(verificacion3.getTipo()).thenReturn("Vinchuca");
		
		when(verificacion1.getParticipante()).thenReturn(participante1);
		when(verificacion2.getParticipante()).thenReturn(participante2);
		when(verificacion3.getParticipante()).thenReturn(participante3);

		when(participante1.getNivel()).thenReturn(nivelBasico);
		when(participante2.getNivel()).thenReturn(nivelExperto);
		when(participante3.getNivel()).thenReturn(nivelExperto);
		
		String nivel = muestra.getNivelValidacion();
		String tipo = muestra.getTipo();
		
		assertTrue("Alto".equals(nivel));
		assertTrue("Vinchuca".equals(tipo));
		
		verify(verificacion1, times(0)).getTipo();
		verify(verificacion1, times(4)).getParticipante();
		verify(participante1, times(4)).getNivel();
		
		verify(verificacion2, times(1)).getTipo();
		verify(verificacion2, times(13)).getParticipante();
		verify(participante2, times(13)).getNivel();
		
		verify(verificacion3, times(1)).getTipo();
		verify(verificacion3, times(4)).getParticipante();
		verify(participante3, times(4)).getNivel();

	}
	
	@Test
	public void testMuestraPoseeUnaListaDeObservadoresDeTipoZonaDeCoberturainicialmenteVacia() {
		
	assertTrue(muestra.getObservadores().isEmpty());	
	}
	@Test
	public void testMuestraAgregaUnObservadorAsuListaDeObservadoresVacia() {
	muestra.agregarObservador(observador);	
		
	assertTrue(muestra.getObservadores().contains(observador));
	}
	@Test
	public void testMuestraEliminaUnObservadorDeSuListaQueConteniaUnElementoPorLoQueQuedaVacia() {
	muestra.agregarObservador(observador);
	muestra.removerObservador(observador);
	
	assertTrue(muestra.getObservadores().isEmpty());
	}
	@Test
	public void verificarQueCuandoMuestraRecibeUnaVerificacionNotificaATodosSusObservadoresSeActualicen() {
	
	Muestra muestraSpy= Mockito.spy(muestra);
	muestraSpy.agregarObservador(observador);
	muestraSpy.agregarVerificacion(verificacion1);
	
	verify(muestraSpy).notificarMuestraAZonaAgregoVerificacion();
	verify(observador).actualizarMuestrasReportadasAgregaronVerif(muestraSpy);
	}
	
}


