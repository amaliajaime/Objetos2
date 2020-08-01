package src;

import java.time.LocalDate;

public class FiltroFechaMuestra implements FiltroDeMuestras {

	private LocalDate argumento;
	
	@Override
	public Boolean canHandle(Object objeto, String tipoFiltro) {
		Boolean valorRetorno = false;
		if (objeto.getClass()==LocalDate.class && tipoFiltro.equals("Muestra")) {
			valorRetorno = true;
		}
		return valorRetorno;
	}

	@Override
	public void cargarParametro(Object objeto) {
		this.argumento = (LocalDate) objeto;
	}
	
	public void cargarParametro(LocalDate fecha) {
		this.argumento = fecha;
	}
	
	@Override
	public boolean test(Muestra muestra) {
		Boolean valorRetorno = false;
		if (muestra.getFecha().compareTo(argumento) > 0) {
			valorRetorno = true;
		}
		return valorRetorno;
	}
}
