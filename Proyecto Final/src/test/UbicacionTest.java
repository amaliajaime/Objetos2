package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Ubicacion;

public class UbicacionTest {
	
		

		List<Ubicacion>ubicaciones;
	
		Ubicacion ubicacion;
		Ubicacion otraUbicacion;
		Ubicacion unaTerceraUbicacion;
		Ubicacion unaCuartaUbicacion;
		
		@BeforeEach
		
		public void setUp() {
		
		ubicacion= new Ubicacion (0.1,0.1);	
		otraUbicacion=new Ubicacion(0.1,0.1);
		unaTerceraUbicacion=new Ubicacion(0.2,0.2);	
		unaCuartaUbicacion=new Ubicacion(0.2,0.2);
		ubicaciones=new ArrayList<Ubicacion>();	
		
		ubicaciones.add (otraUbicacion);
		ubicaciones.add (unaTerceraUbicacion);
		ubicaciones.add (unaCuartaUbicacion);
		
		}
		
		@Test
		
		public void testUbicacionConoceSuLatitud() {
			
			assertTrue(ubicacion.getLatitud()==0.1);
			assertFalse(ubicacion.getLatitud()==0.2);
		}
		
		@Test
		
		public void testUbicacionConoceSuLongitud() {
			
			assertTrue(ubicacion.getLongitud()==0.1);
		}
		
		@Test
		
		public void testUbicacionEstaALaMismaDistanciaQueOtraUbicacionPorLoQueLaDistanciaEs0(){
			
			assertTrue(ubicacion.distanciaA(otraUbicacion)==0) ;
				
		}
		
		@Test
		
		public void testUbicacionConoceLaDistanciaAUnaTerceraUbicacion() {
			
			assertEquals(ubicacion.distanciaA(unaTerceraUbicacion),15.72,4);
		}
	
		@Test
		public void testUnaUbicacionConoceUnaListaDeUbicacionesQueSeEncuentranAunaDeterminadaDistancia() {
			
			List<Ubicacion>ubicacionesEsperadas= new ArrayList<Ubicacion>();
			ubicacionesEsperadas.add(otraUbicacion);
			
		    assertFalse(ubicaciones.contains(ubicacion) );
			assertTrue(ubicacion.ubicacionesADetermDist(ubicaciones,0.2).containsAll(ubicacionesEsperadas)); 
			
		}
	}


