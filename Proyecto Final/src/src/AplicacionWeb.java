package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AplicacionWeb {
	private ArrayList<Muestra>muestrasCargadas;
	private ArrayList<Participante>participantes;
	private ArrayList<ZonaDeCobertura>zonasDeCobertura;
	private Buscador buscador;

	public AplicacionWeb() {

		this.muestrasCargadas= new ArrayList<Muestra>();
		this.participantes   = new ArrayList<Participante>();
		this.zonasDeCobertura=new ArrayList<ZonaDeCobertura>();
		this.buscador = new Buscador(this);

	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura unaZona) {
		
		this.zonasDeCobertura.add(unaZona);
		unaZona.escanearMuestras(this.muestrasCargadas);
	}
	
	public void agregarParticipante(Participante unParticipante) {
		this.participantes.add(unParticipante);
		unParticipante.darDeAlta(this);
	}
	
	public void agregarMuestra(Muestra unaMuestra, Verificacion unaVerificacion, Participante unParticipante) {
		unaMuestra.agregarVerificacion(unaVerificacion);
		unParticipante.enviaMuestra(unaMuestra);
		this.muestrasCargadas.add(unaMuestra);
		zonasDeCobertura.stream().forEach(zona->zona.agregarMuestraSiPerteneceAZona(unaMuestra));
	
		
		}
	
	public void verificarMuestra(Muestra unaMuestra, Verificacion unaVerificacion, Participante unParticipante) {
		if ((! participanteEnvioMuestra(unaMuestra, unParticipante)) && (! participanteVerificoUnaMuestra(unaMuestra, unParticipante)) && unaMuestra.getVerificaciones().size() < 3) {
			this.getMuestrasCargadas().remove(unaMuestra);
			
			unaMuestra.agregarVerificacion(unaVerificacion);
			unParticipante.veificaMuestra(unaMuestra);
			
			this.getMuestrasCargadas().add(unaMuestra);
		}
	}

	public int getVerificacionesMensualesPorParticipante(Participante participante) {
		return participante.getVerificacionesMensuales().size();
	}
	
	public int getContribucionesMensualesPorParticipante(Participante participante) {
		return participante.getContribucionesMensuales().size();
	}
	
	
	public boolean participanteVerificoUnaMuestra(Muestra unaMuestra, Participante unParticipante) {
		return unaMuestra.tieneVerificacion(unParticipante);
	} 
	
	public boolean participanteEnvioMuestra (Muestra unaMuestra, Participante unParticipante) {
		return unaMuestra.getColaborador().getAlias().equals(unParticipante.getAlias()) ;
	}
	
	public ArrayList<Muestra> muestrasCercanasA (Muestra muestraAAnalizar, Double distancia) {
		ArrayList<Muestra> resultados = this.getMuestrasCargadas().stream()
				.filter(muestra -> muestra.getUbicacion().distanciaA(muestraAAnalizar.getUbicacion())==distancia)
				.collect(Collectors.toCollection(ArrayList::new));
		return resultados;
	} 
	
	public ArrayList<Participante>getParticipantes() {
		return this.participantes;
	}
	
	public ArrayList<Muestra> getMuestrasCargadas() {
		return this.muestrasCargadas;
	}

	public Buscador getBuscador() {
		return this.buscador;
	}

}