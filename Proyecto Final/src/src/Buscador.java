package src;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Buscador {
	
	private ArrayList<FiltroDeMuestras> filtros;
	private AplicacionWeb aplicacionWeb;
	
	public Buscador(AplicacionWeb aplicacion) {
		this.filtros = new ArrayList<FiltroDeMuestras>();
		this.aplicacionWeb = aplicacion;
	}

	public void agregarFiltro(FiltroDeMuestras filtro) {
		this.filtros.add(filtro);
	}

	public ArrayList<Muestra> buscarCon(Predicate<Muestra> predicate) {
		ArrayList<Muestra> muestrasFiltradas = this.aplicacionWeb.getMuestrasCargadas().stream().filter(m -> predicate.test(m))
				.collect(Collectors.toCollection(ArrayList::new));
		return muestrasFiltradas;
	}

	public FiltroDeMuestras filtro(Object objeto, String tipoFiltro) {
		FiltroDeMuestras filtroRetorno = this.filtros.stream().filter(filtro -> filtro.canHandle(objeto, tipoFiltro))
				.findFirst().get();
		filtroRetorno.cargarParametro(objeto);
		return filtroRetorno;
	}
}
