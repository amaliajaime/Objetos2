package src;

public class NivelEspecialista implements NivelParticipante {

	@Override
	public void actualizarNivel(Participante unParticipante) {
		// No implementation
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
