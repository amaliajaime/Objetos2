package src;

import java.util.ArrayList;
	
import java.util.stream.Collectors;
import java.time.LocalDate;

/**
 * Clase que representa a una Muestra que contiene los datos de recolecci�n por parte de un Participante. 
 * Sirve como elemento de control para la comparaci�n de datos y la identificaci�n de los vectores que se quieren reconocer.
 */
public class Muestra {
	
	/**
	 * Fecha de subida de la Muestra
	 */
	private LocalDate fecha;
	/**
	 * Ubicaci�n donde se reconoci� el posible vector
	 */
	private Ubicacion ubicacion;
	/**
	 * Foto de reconocimiento del vector, utilizado para la validaci�n visual de los usuarios
	 */
	private Foto foto;
	/**
	 * Todas las verificaciones cargadas por Participantes que reconocen el tipo de la Muestra
	 */
	private ArrayList<Verificacion> verificaciones;
	/**
	 * Strategy con el algoritmo utilizado para retornar el nivel de validaci�n y el tipo de la Muestra
	 */
	private DeterminadorNivelValidacion validador;
	
	private ArrayList<ObservadorMuestra> observadores;

	public Muestra(LocalDate fecha, Ubicacion ubicacion, Foto foto, Verificacion verificacion) {
		this.verificaciones = new ArrayList<Verificacion>();
		this.validador = new ValidadorPorVerificaciones();
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.foto = foto;
		this.verificaciones.add(verificacion);
		this.observadores= new ArrayList<ObservadorMuestra>();
	}

	/**
	 * M�todo que delega la determinaci�n del tipo de la muestra al algoritmo de validaci�n.
	 * @return String con el tipo de la Muestra en base a sus verificaciones
	 * @see ValidadorPorVerificaciones
	 */
	public String getTipo() {
		return validador.definirTipo(this.verificaciones);
	}

	/**
	 * Getter de la Fecha de subida de la Muestra
	 * @return Instancia de LocalDate
	 * @see LocalDate
	 */
	public LocalDate getFecha() {
		return this.fecha;
	}

	/**
	 * Getter de la Ubicaci�n de la Muestra
	 * @return Instancia de Ubicacion
	 * @see Ubicacion
	 */
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * Getter de la Foto de la Muestra
	 * @return Instancia de Foto
	 * @see Ubicacion
	 */
	public Foto getFoto() {
		return this.foto;
	}


	/**
	 * M�todo que agrega una verificacion a la ArrayList verificaciones
	 * @param verificacion Instancia de Verificacion
	 * @see Verificacion
	 */
	public void agregarVerificacion(Verificacion verificacion) {
			this.verificaciones.add(verificacion);
			this.notificarMuestraAZonaAgregoVerificacion();
	}

	/**
	 * M�todo que retorna todas las verificaciones cargadas para la Muestra
	 * @return ArrayList de Verificacion
	 * @see Verificacion
	 */
	public ArrayList<Verificacion> getVerificaciones() {
		return this.verificaciones;
	}

	/**
	 * M�todo que delega la determinaci�n del nivel de validaci�n de la muestra al algoritmo de validacion.
	 * @return String indicando el nivel de validaci�n en base a un ArrayList de Verificacion
	 * @see ValidadorPorVerificaciones
	 * @see Verificacion
	 */
	public String getNivelValidacion() {
		return this.validador.evaluar(verificaciones);
	}

	/**
	 * M�todo que retorna el Participante que subio originalmente la Muestra, que corresponde a la primera verificaci�n con la que se inicializa la Muestra
	 * @return Instancia de Participante
	 * @see Participante
	 */
	public Participante getColaborador() {
		return this.verificaciones.get(0).getParticipante();
	}
	/* cada ves que una muestra agregue una verificacion todos sus observadores son notificados y actualizados */
	public void notificarMuestraAZonaAgregoVerificacion() {
		if (!this.observadores.isEmpty()) {
			for(ObservadorMuestra observador : this.observadores) {
				observador.actualizarMuestrasReportadasAgregaronVerif(this);
			}
		}
	}
	
	/*Permite agregar un observador y declarar los interesados en la muestra cuando esta ,notifique sus cambios*/ 
	public void agregarObservador(ObservadorMuestra ob) {
		this.observadores.add (ob);
	}
	
	/*remueve un observador de la lista de observadores de una muestra*/ 
	public void removerObservador(ObservadorMuestra ob) {
		this.observadores.remove(ob);
	}

	public ArrayList<ObservadorMuestra> getObservadores() {
		return this.observadores;
	}

	public boolean tieneVerificacion(Participante unParticipante) {
		return this.getVerificaciones().stream()
		.filter(verificacion -> verificacion.estaVerificada(unParticipante))
		.collect(Collectors.toList()).size() == 1;
	}
	
	public boolean tieneVerificacionMensual(Participante unParticipante) {
		return this.getVerificaciones().stream()
		.filter(verificacion -> verificacion.estaVerificadaMensualmente(unParticipante))
		.collect(Collectors.toList()).size() == 1;
	}
	
}