package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class FiltroFechaVerificacion implements FiltroDeMuestras {

	private LocalDate argumento;
	
	@Override
	public Boolean canHandle(Object objeto, String tipoFiltro) {
		Boolean valorRetorno = false;
		if (objeto.getClass()==LocalDate.class && tipoFiltro.equals("Verificacion")) {
			valorRetorno = true;
		}
		return valorRetorno;
	}

	@Override
	public void cargarParametro(Object objeto) {
		this.argumento = (LocalDate) objeto;
	}
	

	@Override
	public boolean test(Muestra muestra) {
		Boolean valorRetorno = false;
		ArrayList<Verificacion> verificaciones = muestra.getVerificaciones();
		if (verificaciones.get(verificaciones.size()-1).getFecha().compareTo(argumento) > 0) {
			valorRetorno = true;
		}
		return valorRetorno;
	}

}
