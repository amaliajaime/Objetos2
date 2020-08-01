package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZonaDeCobertura  implements ObservadorMuestra {
	
	private ArrayList<ObservadorZonaDeCobertura>observadores ; 
	private String nombre;
	private Ubicacion epicentro;
	private Double radio;
	private ArrayList<Muestra>muestrasReportadas ;

	//constructores
	public  ZonaDeCobertura(String nombre,Ubicacion epicentro,Double radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;		
		this.muestrasReportadas = new ArrayList<Muestra>();
		this.observadores = new ArrayList<ObservadorZonaDeCobertura>();
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public void setEpicentro(Ubicacion epicentro){
		this.epicentro=epicentro;
	}
	
	public void setRadio(Double radio){
		this.radio=radio;
	}
	//Nombre de la zona de cobertura 
	public String getNombre() {
		return this.nombre;
	}
	//Ubicacion de la zona de cobertura
    public Ubicacion getEpicentro() {
		return this.epicentro;
	}
    //Radio de la zona de cobertura 
    public double getRadio() {
    	return this.radio;
	}

    //Muestras que se encuentran dentro del radio de cobertura de una zona de cobertura
    public ArrayList<Muestra> getMuestrasReportadas() {
    	return this.muestrasReportadas;
    }
    /**
     *  retorna una lista de zonas de cobertura cuyos radios se solapan con la de la zona principal*/
    public List<ZonaDeCobertura> getZonasDeCoberturaSolapadas(List<ZonaDeCobertura> zonasDeCobertura){
    	return zonasDeCobertura.stream().
				filter(zona -> (this.getEpicentro()).distanciaA(zona.getEpicentro())< (this.getRadio()+zona.getRadio())).
				collect(Collectors.toList());
    }

    //lista de observadores que estan pendientes del estado de una zona de cobertura
    public ArrayList<ObservadorZonaDeCobertura> getObservadores() {
		return this.observadores;
	}
    
    /*agrega un observador a mi lista de observadores */
	public void agregarObservador(ObservadorZonaDeCobertura ob){
		
		this.observadores.add (ob);
	}
	/* remueve una zona de  mi lista de observadores*/
	public void removerObservador(ObservadorZonaDeCobertura ob) {
		
		this.observadores.remove(ob);
	}
	
    
	/*	verifica si una muestra esta dentro de una zona de cobertura*/
	public boolean puedeCargarMuestra(Muestra muestra) {
		
		return (((this.getEpicentro()).distanciaA(muestra.getUbicacion())))< this.getRadio();

	}
	
	//*si una muestra se encuentra dentro del radio de cobertura de una zona la agrega , ademas la muestra agrega como observador a dicha zona */
	public void agregarMuestraSiPerteneceAZona(Muestra muestra) {
		
		if(this.puedeCargarMuestra(muestra)) {
			
			this.agregarMuestra(muestra);
			
			muestra.agregarObservador(this);
			
		}
	}
	/*Agrega una muestra  a la lista de muestrasReportadas de una zona de cobertura , ademas se notifica a sus observadores que se agrego dicha muestra*/
	public void agregarMuestra(Muestra muestra) {
		
		this.muestrasReportadas.add(muestra);
		this.notificarAgregueMuestra(muestra);
	}	
	
	@Override
	/*Cuando una muestra que pertenece a la lista de muestras reportadas de una zona , agrega una verificacion,se notifica a los observadores de la zonaDeCobertura*/
	public void actualizarMuestrasReportadasAgregaronVerif(Muestra muestra) {
		
		this.notificarMuestraAgregoVerificacion(muestra);
	}
	
	/*notifica a los observadores que agregue una muestra a mi lista de muestras reportadas*/
	public  void notificarAgregueMuestra(Muestra muestra) {
		if (!this.observadores.isEmpty()) {
			for(ObservadorZonaDeCobertura observador : this.observadores) {
				observador.actualizarAgregueMuestra(this, muestra);
			}
		}
	}
	/*todos los observadores de una zona de cobertura son notificados y actualizados*/
	public void notificarMuestraAgregoVerificacion(Muestra muestra) {
		
		for (ObservadorZonaDeCobertura observador :observadores) {
			
			observador.actualizarMuestraAgregoVerificacion(this,muestra);
		}
	}
	/*permite a una zona de cobertura encontrar a todas las muestras  que se encuentran en su radio de cobertura y las agrega*/
	public void escanearMuestras(ArrayList<Muestra> muestrasCargadas) {
		
			muestrasCargadas.stream().forEach(muestra->this.agregarMuestraSiPerteneceAZona(muestra));
	}


}