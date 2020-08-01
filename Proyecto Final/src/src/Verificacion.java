package src;

import java.time.LocalDate;

public class Verificacion {
	private String tipo;
	private LocalDate fecha;
	private Participante participante ;
	
	public Verificacion (String tipo, LocalDate unaFecha, Participante unParticipante) {
		this.tipo= tipo;
		this.fecha = unaFecha;
		this.participante = unParticipante;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}

	public Participante getParticipante() {
		return this.participante;
	}
	
	public boolean estaVerificada(Participante unParticipante) {
		return unParticipante.getAlias().equals(this.participante.getAlias());
	}
	
	public boolean estaVerificadaMensualmente(Participante unParticipante) {
		return this.getFecha().plusMonths(1).isAfter(LocalDate.now())
				&& unParticipante.getAlias().equals(this.participante.getAlias());
	}
}