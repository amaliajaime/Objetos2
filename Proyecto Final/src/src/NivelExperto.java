package src;

public class NivelExperto implements NivelParticipante {

	@Override
	public void actualizarNivel(Participante unParticipante) {
		if ( unParticipante.superaValidacionesYContribuciones()) {
			unParticipante.setNivel(new NivelBasico()); 
		}
	}

	@Override
	public int compareTo(NivelParticipante otroNivel) {
		Integer ret = 0;
		if (otroNivel.getClass() == NivelBasico.class) {
			ret = 1;
		}
		return ret;
	}
}
