package src;


import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {
	//atributos
	private  Double latitud; 
	private  Double longitud; 
	
	
	
	public  Ubicacion (double longitud, double latitud) {
		
		this.latitud = latitud ;
		this.longitud = longitud ;
	
	}
	//getters
	public double getLatitud() {
		
		return this.latitud;
	}

	public double getLongitud() {
		
		return this.longitud;
		
	}
	
	//setters
	public void setLatitud(double latitud) {
		
		this.latitud= latitud;
	}
	
	public void  setLongitud(double longitud) {
		
		this.longitud= longitud;
	}
	
	
	public double distanciaA( Ubicacion ubicacion) {  
        //Proposito:Retorna una distancia en km  entre la ubicacion actual y una pasada por parametro;
		//Formula Haversine 
		// 1ero se pasan la resta de entre las latitudes y longitudes entre dos ubicaciones y luego se pasan ambas a radianes y se guardan
		// en sus variables correspondientes
		// 2do se le aplica el seno a las dos variables que contienen la longitud y longitud y se las divide por 2 
		// 3ro se le aplica potencia de dos a las variables anteriores unidas por una suma  y eso multiplicado por la misma multiplicacion
		// de  el coseno de las latitudes de ambas ubicaciones( pasadas a radianes)
		//4to 2 * la Arcotangente resultante del conciente entre las raices cuadradas de la variable anterior
		// 5to ese resultado se multiplica por el radio de la tierra.
		double radioTierra = 6371;//en kilómetros  
        
		double distLat = Math.toRadians( this.getLatitud() - ubicacion.getLatitud() );  
        double distLong = Math.toRadians(this.getLongitud() - ubicacion.getLongitud());  
        double sindLat = Math.sin(distLat / 2);  
        double sindLng = Math.sin(distLong / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(this.getLatitud())) * Math.cos(Math.toRadians(ubicacion.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        
        return distancia;   
    }
	
	public void saberDistanciaEntreDosPuntos(Ubicacion ubicacion) {
		
		System.out.println(this.distanciaA(ubicacion));
			
	}
	
	public List<Ubicacion> ubicacionesADetermDist(List <Ubicacion> ubicaciones,Double distancia ) {
		//Proposito:Retorna una coleccion de ubicaciones cuya distancia es menor a la distancia pasada por parametro 
		
		return ubicaciones.stream().
				filter(ubicacion -> this.distanciaA(ubicacion)  <  distancia).
				collect(Collectors.toList());
		
	}
	
	
	
	
}