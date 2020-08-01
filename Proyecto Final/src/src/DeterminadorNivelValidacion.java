package src;

import java.util.ArrayList;

/**
 * Interfaz para los algoritmos de cómputo del nivel de validación y tipo definidos en base a una lista de validaciones.
 */
public interface DeterminadorNivelValidacion {

	/**
	 * Método que retorna un String con el nivel de validación en base a las verificaciones cargadas.
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return String que indica el nivel de validación correspondiente
	 */
	public String evaluar(ArrayList<Verificacion> verificaciones);

	/**
	 * Método que retorna un String con el tipo en base a las verificaciones cargadas
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return String que indica el tipo correspondiente
	 */
	public String definirTipo(ArrayList<Verificacion> verificaciones);
	
	/**
	 * Método que filtra las verificaciones de los Usuarios, usualmente en función de su nivel, 
	 * retornando todas aquellas que posean el mismo grado de validez
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return Una ArrayList de verificaciones con un filtro determinado
	 */
	public ArrayList<Verificacion> filtrarVerifDeUsuarios(ArrayList<Verificacion> verificaciones);

}
