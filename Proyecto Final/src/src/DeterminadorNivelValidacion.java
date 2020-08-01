package src;

import java.util.ArrayList;

/**
 * Interfaz para los algoritmos de c�mputo del nivel de validaci�n y tipo definidos en base a una lista de validaciones.
 */
public interface DeterminadorNivelValidacion {

	/**
	 * M�todo que retorna un String con el nivel de validaci�n en base a las verificaciones cargadas.
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return String que indica el nivel de validaci�n correspondiente
	 */
	public String evaluar(ArrayList<Verificacion> verificaciones);

	/**
	 * M�todo que retorna un String con el tipo en base a las verificaciones cargadas
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return String que indica el tipo correspondiente
	 */
	public String definirTipo(ArrayList<Verificacion> verificaciones);
	
	/**
	 * M�todo que filtra las verificaciones de los Usuarios, usualmente en funci�n de su nivel, 
	 * retornando todas aquellas que posean el mismo grado de validez
	 * @param verificaciones Una ArrayList de verificaciones
	 * @return Una ArrayList de verificaciones con un filtro determinado
	 */
	public ArrayList<Verificacion> filtrarVerifDeUsuarios(ArrayList<Verificacion> verificaciones);

}
