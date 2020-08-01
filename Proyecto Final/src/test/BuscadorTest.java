package test;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.AplicacionWeb;
import src.Buscador;
import src.FiltroDeMuestras;
import src.FiltroFechaMuestra;
import src.FiltroFechaVerificacion;
import src.FiltroNivelMuestra;
import src.FiltroTipoInsecto;
import src.Muestra;
import src.Verificacion;

import static org.junit.Assert.assertTrue;
import  static org.mockito.Mockito.*;

public class BuscadorTest {
		
	private AplicacionWeb aplicacionWeb;
	private Buscador buscador;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;

	private Verificacion verif1;
	private Verificacion verif2;
	private Verificacion verif3;
	private Verificacion verif4;
	private Verificacion verif5;
	private Verificacion verif6;
	
	@BeforeEach
	public void setUp() {
		
		this.aplicacionWeb = mock(AplicacionWeb.class);
		this.buscador = new Buscador(aplicacionWeb);
		
		FiltroDeMuestras filtroFechaMuestra = new FiltroFechaMuestra();
		FiltroDeMuestras filtroFechaVerificacion = new FiltroFechaVerificacion();
		FiltroDeMuestras filtroTipoInsecto = new FiltroTipoInsecto();
		FiltroDeMuestras filtroNivelMuestra = new FiltroNivelMuestra();
		
		this.buscador.agregarFiltro(filtroFechaMuestra);
		this.buscador.agregarFiltro(filtroFechaVerificacion);
		this.buscador.agregarFiltro(filtroTipoInsecto);
		this.buscador.agregarFiltro(filtroNivelMuestra);

		this.muestra1 = mock(Muestra.class);
		this.muestra2 = mock(Muestra.class);
		this.muestra3 = mock(Muestra.class);
		this.muestra4 = mock(Muestra.class);
		
		ArrayList<Muestra> muestrasCargadas = new ArrayList<Muestra>();
		muestrasCargadas.add(muestra1);
		muestrasCargadas.add(muestra2);
		muestrasCargadas.add(muestra3);
		muestrasCargadas.add(muestra4);
		
		when(aplicacionWeb.getMuestrasCargadas()).thenReturn(muestrasCargadas);
		
		verif1 = mock(Verificacion.class);
		verif2 = mock(Verificacion.class);
		verif3 = mock(Verificacion.class);
		verif4 = mock(Verificacion.class);
		verif5 = mock(Verificacion.class);
		verif6 = mock(Verificacion.class);
		
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacion() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra3);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha1);
		when(muestra3.getFecha()).thenReturn(fecha1);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro = "Muestra";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeLaUltimaVerificacion() {
	
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra3);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(verif1.getFecha()).thenReturn(fecha1);
		when(verif2.getFecha()).thenReturn(fecha1);
		when(verif3.getFecha()).thenReturn(fecha2);
		when(verif4.getFecha()).thenReturn(fecha1);
		when(verif5.getFecha()).thenReturn(fecha2);
		when(verif6.getFecha()).thenReturn(fecha2);

		ArrayList<Verificacion> verificacionesMuestra1 = new ArrayList<Verificacion>();
		verificacionesMuestra1.add(verif1);
		verificacionesMuestra1.add(verif2);
		
		ArrayList<Verificacion> verificacionesMuestra2 = new ArrayList<Verificacion>();
		verificacionesMuestra2.add(verif3);
		
		ArrayList<Verificacion> verificacionesMuestra3 = new ArrayList<Verificacion>();
		verificacionesMuestra3.add(verif4);
		
		ArrayList<Verificacion> verificacionesMuestra4 = new ArrayList<Verificacion>();
		verificacionesMuestra4.add(verif5);
		verificacionesMuestra4.add(verif6);
		
		when(muestra1.getVerificaciones()).thenReturn(verificacionesMuestra1);
		when(muestra3.getVerificaciones()).thenReturn(verificacionesMuestra3);
		when(muestra2.getVerificaciones()).thenReturn(verificacionesMuestra2);
		when(muestra4.getVerificaciones()).thenReturn(verificacionesMuestra4);
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro = "Verificacion";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseAlTipoDeInsectoDetectado() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra2);
		listaEsperada.add(muestra4);
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto1);
		when(muestra2.getTipo()).thenReturn(tipoInsecto1);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		String tipoFiltro = "TipoInsecto";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(tipoInsecto1, tipoFiltro));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseAlNivelDeValidacion() {
		
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra3);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoFiltro = "Nivel";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(nivelValidacionAlto, tipoFiltro));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionYLaFechaDeLaUltimaVerificacion() {

		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra3);
		listaEsperada.add(muestra4);
		
		LocalDate fechaMuestra1 = LocalDate.of(2018, 11, 18);
		LocalDate fechaMuestra2 = LocalDate.of(2018, 10, 5);
		
		when(muestra1.getFecha()).thenReturn(fechaMuestra1);
		when(muestra3.getFecha()).thenReturn(fechaMuestra1);
		when(muestra2.getFecha()).thenReturn(fechaMuestra2);
		when(muestra4.getFecha()).thenReturn(fechaMuestra2);
		
		LocalDate fechaVerif1 = LocalDate.of(2018, 12, 18);
		LocalDate fechaVerif2 = LocalDate.of(2018, 10, 28);
		
		when(verif1.getFecha()).thenReturn(fechaVerif1);
		when(verif2.getFecha()).thenReturn(fechaVerif1);
		when(verif3.getFecha()).thenReturn(fechaVerif2);
		when(verif4.getFecha()).thenReturn(fechaVerif1);
		when(verif5.getFecha()).thenReturn(fechaVerif2);
		when(verif6.getFecha()).thenReturn(fechaVerif1);

		ArrayList<Verificacion> verificacionesMuestra1 = new ArrayList<Verificacion>();
		verificacionesMuestra1.add(verif1);
		verificacionesMuestra1.add(verif2);
		
		ArrayList<Verificacion> verificacionesMuestra2 = new ArrayList<Verificacion>();
		verificacionesMuestra2.add(verif3);
		
		ArrayList<Verificacion> verificacionesMuestra3 = new ArrayList<Verificacion>();
		verificacionesMuestra3.add(verif4);
		
		ArrayList<Verificacion> verificacionesMuestra4 = new ArrayList<Verificacion>();
		verificacionesMuestra4.add(verif5);
		verificacionesMuestra4.add(verif6);
		
		when(muestra1.getVerificaciones()).thenReturn(verificacionesMuestra1);
		when(muestra3.getVerificaciones()).thenReturn(verificacionesMuestra3);
		when(muestra2.getVerificaciones()).thenReturn(verificacionesMuestra2);
		when(muestra4.getVerificaciones()).thenReturn(verificacionesMuestra4);
		
		LocalDate fechaFiltro1 = LocalDate.of(2018, 10, 1);
		String tipoFiltro1 = "Muestra";
		LocalDate fechaFiltro2 = LocalDate.of(2018, 11, 1);
		String tipoFiltro2 = "Verificacion";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro1, tipoFiltro1).and(buscador.filtro(fechaFiltro2, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionYElTipoDeInsectoDetectado() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha1);
		when(muestra3.getFecha()).thenReturn(fecha1);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto1);
		when(muestra2.getTipo()).thenReturn(tipoInsecto1);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		String tipoFiltro2 = "TipoInsecto";
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro1 = "Muestra";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro1).and(buscador.filtro(tipoInsecto1, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionYAlNivelDeValidacion() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha1);
		when(muestra3.getFecha()).thenReturn(fecha1);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoFiltro2 = "Nivel";
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro1 = "Muestra";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro1).and(buscador.filtro(nivelValidacionBajo, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseAlNivelDeValidacionYElTipoDeInsecto() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra4);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoFiltro1 = "Nivel";
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto1);
		when(muestra2.getTipo()).thenReturn(tipoInsecto1);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		String tipoFiltro2 = "TipoInsecto";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(nivelValidacionBajo, tipoFiltro1).and(buscador.filtro(tipoInsecto1, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionOLaFechaDeLaUltimaVerificacion() {

		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra2);
		listaEsperada.add(muestra3);
		
		LocalDate fechaMuestra1 = LocalDate.of(2018, 11, 18);
		LocalDate fechaMuestra2 = LocalDate.of(2018, 9, 5);
		
		when(muestra1.getFecha()).thenReturn(fechaMuestra1);
		when(muestra3.getFecha()).thenReturn(fechaMuestra1);
		when(muestra2.getFecha()).thenReturn(fechaMuestra2);
		when(muestra4.getFecha()).thenReturn(fechaMuestra2);
		
		LocalDate fechaVerif1 = LocalDate.of(2018, 12, 18);
		LocalDate fechaVerif2 = LocalDate.of(2018, 10, 28);
		
		when(verif1.getFecha()).thenReturn(fechaVerif1);
		when(verif2.getFecha()).thenReturn(fechaVerif2);
		when(verif3.getFecha()).thenReturn(fechaVerif1);
		when(verif4.getFecha()).thenReturn(fechaVerif2);
		when(verif5.getFecha()).thenReturn(fechaVerif1);
		when(verif6.getFecha()).thenReturn(fechaVerif2);

		ArrayList<Verificacion> verificacionesMuestra1 = new ArrayList<Verificacion>();
		verificacionesMuestra1.add(verif1);
		verificacionesMuestra1.add(verif2);
		
		ArrayList<Verificacion> verificacionesMuestra2 = new ArrayList<Verificacion>();
		verificacionesMuestra2.add(verif3);
		
		ArrayList<Verificacion> verificacionesMuestra3 = new ArrayList<Verificacion>();
		verificacionesMuestra3.add(verif4);
		
		ArrayList<Verificacion> verificacionesMuestra4 = new ArrayList<Verificacion>();
		verificacionesMuestra4.add(verif5);
		verificacionesMuestra4.add(verif6);
		
		when(muestra1.getVerificaciones()).thenReturn(verificacionesMuestra1);
		when(muestra3.getVerificaciones()).thenReturn(verificacionesMuestra3);
		when(muestra2.getVerificaciones()).thenReturn(verificacionesMuestra2);
		when(muestra4.getVerificaciones()).thenReturn(verificacionesMuestra4);
		
		LocalDate fechaFiltro1 = LocalDate.of(2018, 10, 1);
		String tipoFiltro1 = "Muestra";
		LocalDate fechaFiltro2 = LocalDate.of(2018, 11, 1);
		String tipoFiltro2 = "Verificacion";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro1, tipoFiltro1).or(buscador.filtro(fechaFiltro2, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionOElTipoDeInsectoDetectado() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra4);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha1);
		when(muestra3.getFecha()).thenReturn(fecha2);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto2);
		when(muestra2.getTipo()).thenReturn(tipoInsecto2);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		String tipoFiltro2 = "TipoInsecto";
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro1 = "Muestra";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro1).or(buscador.filtro(tipoInsecto1, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseALaFechaDeCreacionOAlNivelDeValidacion() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra2);
		listaEsperada.add(muestra3);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha2);
		when(muestra3.getFecha()).thenReturn(fecha1);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoFiltro2 = "Nivel";
		
		LocalDate fechaFiltro = LocalDate.of(2018, 10, 01);
		String tipoFiltro1 = "Muestra";
		
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(fechaFiltro, tipoFiltro1).or(buscador.filtro(nivelValidacionMedio, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseAlNivelDeValidacionOElTipoDeInsecto() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra2);
		listaEsperada.add(muestra4);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoFiltro1 = "Nivel";
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto1);
		when(muestra2.getTipo()).thenReturn(tipoInsecto1);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		String tipoFiltro2 = "TipoInsecto";
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon(buscador.filtro(nivelValidacionBajo, tipoFiltro1).or(buscador.filtro(tipoInsecto1, tipoFiltro2)));
		assertTrue(listaResultado.equals(listaEsperada));
	}
	
	@Test
	public void testAplicacionPuedeBuscarUnaListaDeMuestrasEnBaseAlNivelDeValidacionYElTipoDeInsectoODeLaFechaDeUltimaVerificacionOLaFechaDeCreacionDeLaMuestra() {
		ArrayList<Muestra> listaEsperada = new ArrayList<Muestra>();
		listaEsperada.add(muestra1);
		listaEsperada.add(muestra3);
		listaEsperada.add(muestra4);
		
		String nivelValidacionAlto = "Alto";
		String nivelValidacionBajo = "Bajo";
		String nivelValidacionMedio = "Medio";
		
		when(muestra1.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		when(muestra2.getNivelValidacion()).thenReturn(nivelValidacionMedio);
		when(muestra3.getNivelValidacion()).thenReturn(nivelValidacionAlto);
		when(muestra4.getNivelValidacion()).thenReturn(nivelValidacionBajo);
		
		String tipoInsecto1 = "Vinchuca";
		String tipoInsecto2 = "Ninguno";
		
		when(muestra1.getTipo()).thenReturn(tipoInsecto1);
		when(muestra2.getTipo()).thenReturn(tipoInsecto1);
		when(muestra3.getTipo()).thenReturn(tipoInsecto2);
		when(muestra4.getTipo()).thenReturn(tipoInsecto1);
		
		LocalDate fechaVerif1 = LocalDate.of(2018, 12, 18);
		LocalDate fechaVerif2 = LocalDate.of(2018, 10, 28);
		
		when(verif1.getFecha()).thenReturn(fechaVerif1);
		when(verif2.getFecha()).thenReturn(fechaVerif1);
		when(verif3.getFecha()).thenReturn(fechaVerif2);
		when(verif4.getFecha()).thenReturn(fechaVerif1);
		when(verif5.getFecha()).thenReturn(fechaVerif2);
		when(verif6.getFecha()).thenReturn(fechaVerif1);

		ArrayList<Verificacion> verificacionesMuestra1 = new ArrayList<Verificacion>();
		verificacionesMuestra1.add(verif1);
		verificacionesMuestra1.add(verif2);
		
		ArrayList<Verificacion> verificacionesMuestra2 = new ArrayList<Verificacion>();
		verificacionesMuestra2.add(verif3);
		
		ArrayList<Verificacion> verificacionesMuestra3 = new ArrayList<Verificacion>();
		verificacionesMuestra3.add(verif4);
		
		ArrayList<Verificacion> verificacionesMuestra4 = new ArrayList<Verificacion>();
		verificacionesMuestra4.add(verif5);
		verificacionesMuestra4.add(verif6);
		
		when(muestra1.getVerificaciones()).thenReturn(verificacionesMuestra1);
		when(muestra2.getVerificaciones()).thenReturn(verificacionesMuestra2);
		when(muestra3.getVerificaciones()).thenReturn(verificacionesMuestra3);
		when(muestra4.getVerificaciones()).thenReturn(verificacionesMuestra4);
		
		LocalDate fecha1 = LocalDate.of(2018, 11, 18);
		LocalDate fecha2 = LocalDate.of(1995, 3, 8);
		
		when(muestra1.getFecha()).thenReturn(fecha1);
		when(muestra3.getFecha()).thenReturn(fecha1);
		when(muestra2.getFecha()).thenReturn(fecha2);
		when(muestra4.getFecha()).thenReturn(fecha2);
		
		String tipoFiltroTipo = "TipoInsecto";
		String tipoFiltroNivel = "Nivel";
		String tipoFiltroVerif = "Verificacion";
		String tipoFiltroMuestra = "Muestra";

		LocalDate fechaFiltroVerif = LocalDate.of(2018, 11, 1);
		LocalDate fechaFiltroMuestra = LocalDate.of(2018, 10, 01);
	
		ArrayList<Muestra> listaResultado = this.buscador.buscarCon((buscador.filtro(nivelValidacionBajo, tipoFiltroNivel)
				.and(buscador.filtro(tipoInsecto1, tipoFiltroTipo))
						.or(buscador.filtro(fechaFiltroVerif, tipoFiltroVerif)
								.or(buscador.filtro(fechaFiltroMuestra, tipoFiltroMuestra)))));
		assertTrue(listaResultado.equals(listaEsperada));
	}

}
