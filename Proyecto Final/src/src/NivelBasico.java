package src;

public class NivelBasico implements NivelParticipante {

	@Override
	public void actualizarNivel(Participante unParticipante) {
		if (unParticipante.superaValidacionesYContribuciones()) {
			unParticipante.setNivel(new NivelExperto()); 
		}
	}


	@Override
	public int compareTo(NivelParticipante otroNivel) {
		Integer ret = 0;
		if (otroNivel.getClass() != this.getClass()) {
			ret = -1;
		}
		return ret;
	}

}
