package src;

public class FiltroNivelMuestra implements FiltroDeMuestras {

	private String argumento;
	
	@Override
	public Boolean canHandle(Object objeto, String tipoFiltro) {
		Boolean valorRetorno = false;
		if (objeto.getClass()==String.class && tipoFiltro.equals("Nivel")) {
			valorRetorno = true;
		}
		return valorRetorno;
	}

	@Override
	public void cargarParametro(Object objeto) {
		this.argumento = (String) objeto;
	}
	
	@Override
	public boolean test(Muestra muestra) {
		Boolean valorRetorno = false;
		if (muestra.getNivelValidacion().equals(argumento)) {
			valorRetorno = true;
		}
		return valorRetorno;
	}
}
