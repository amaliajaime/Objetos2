package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participante {

	private NivelParticipante nivel;
	private String alias;
	private AplicacionWeb aplicacion;
	private ArrayList<Muestra>verificaciones;
	private ArrayList<Muestra>contribuciones;
	
	public Participante(String alias) {
		this.alias = alias;
		this.aplicacion = null;
		this.verificaciones= new ArrayList<Muestra>();
		this.contribuciones = new ArrayList<Muestra>();
		this.nivel = new NivelBasico();
	}
	
	public Participante(String alias, NivelParticipante unNivel) {
		this.alias = alias;
		this.aplicacion = null;
		this.verificaciones= new ArrayList<Muestra>();
		this.contribuciones = new ArrayList<Muestra>();
		this.nivel = this.cargarNivelDeConocimiento(unNivel);
	}
	
	public NivelParticipante cargarNivelDeConocimiento(NivelParticipante unNivel) {
		NivelParticipante nivel = new NivelBasico();
		if (unNivel.getClass() == new NivelEspecialista().getClass()) {
			nivel = unNivel;
		}
	return nivel;
	}
	
	public void enviaMuestra(Muestra unaMuestra) {
		this.contribuciones.add(unaMuestra);
		this.actualizarNivel();
	}
	
	public void veificaMuestra(Muestra unaMuestra) {
		this.verificaciones.add(unaMuestra);
		this.actualizarNivel();
	}
	
	public void actualizarNivel() {
		this.nivel.actualizarNivel(this);
	}
	
	public NivelParticipante getNivel () {
		return this.nivel;
	}

	public void setNivel(NivelParticipante nivelActualizado) {
		this.nivel = nivelActualizado;
	}

	public boolean superaValidacionesYContribuciones() {
		return (getVerificacionesMensuales().size() > 20 & getContribucionesMensuales().size() > 10) ;
		}
	
	public List<Muestra> getContribucionesMensuales() {
		return this.contribuciones.stream()
				.filter(muestra -> muestra.getFecha().plusMonths(1).isAfter(LocalDate.now()))
				.collect(Collectors.toList());
	}
	
	public List<Muestra> getVerificacionesMensuales() {
		return this.verificaciones.stream()
				.filter(muestra -> muestra.tieneVerificacionMensual(this))
				.collect(Collectors.toList());
	}

	public void darDeAlta(AplicacionWeb aplicacionWeb) {
		this.aplicacion = aplicacionWeb;
	}

	public String getAlias() {
		return this.alias;
	}

}
	
		
		
		
	