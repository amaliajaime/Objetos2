package src;

import java.util.function.Predicate;

public interface FiltroDeMuestras extends Predicate<Muestra> {

	public Boolean canHandle(Object objeto, String tipoFiltro);

	public void cargarParametro(Object objeto);
	
	//Podria utilizar instanceOf para evitar usar casting? Qué alternativas podría hallar?

}
