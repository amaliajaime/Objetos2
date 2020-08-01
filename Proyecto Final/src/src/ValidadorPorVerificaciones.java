package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Este es un algoritmo para la clasificaci�n del nivel de validaci�n de instancias de Muestra en base a sus 
 * verificaciones cargadas, adem�s de poder computar el tipo de forma din�mica.
 * <p>
 * Implementa la interfaz DeterminadorNivelValidacion
 * @see DeterminadorNivelValidacion
 */
public class ValidadorPorVerificaciones implements DeterminadorNivelValidacion {

	/**
	 * Este m�todo implementa filtrarVerifDeUsuarios y luego determina cu�l es el nivel que condice a ellas.
	 * El nivel por defecto siempre ser� "Bajo".
	 *@return String indicando el nivel condicionado por las verificaciones.
	 *@param verificaciones una lista de verificaciones cualquiera.
	 */
	@Override
	public String evaluar(ArrayList<Verificacion> verificaciones) {
		
		ArrayList<Verificacion> verificacionesFiltradas = this.filtrarVerifDeUsuarios(verificaciones);
		
		String respuesta = "Bajo";
		
		if (this.esNivelAlto(verificacionesFiltradas)) {
			respuesta = "Alto";
		} else if (this.esNivelMedio(verificacionesFiltradas)) {
			respuesta = "Medio";
		}
		
		return respuesta;
	}

	
	/**
	 *Condiciones: O son tres verificaciones de usuarios b�sicos o hay al menos una verificaci�n de un usuario Experto.
	 *@return Boolean indicando si, por las condiciones mencionadas, las verificaciones implican un nivel de validacion Alto.
	 *@param verificaciones una lista de verificaciones cuyos participantes son todos del mismo nivel o equiparables.
	 */
	private Boolean esNivelAlto(ArrayList<Verificacion> verificaciones) {	
		Boolean retorno = false;
		NivelParticipante nivelEstandar = verificaciones.get(0).getParticipante().getNivel();

		//Primero valida por el absoluto: Si son 3 verificaciones, es nivel Alto sin importar los niveles de los participantes.
		if (verificaciones.size() == 3) {
			retorno = true;
		}
		//Sino verifica si los participantes son de nivel > b�sico
		else if(nivelEstandar.compareTo(new NivelExperto()) == 0) {
			retorno = true;
		}
		
		return retorno;
	}

	/**
	 *Condiciones: todas las verificaciones son de participantes con nivel B�sico y hay al menos 2 de ellas
	 *@return Boolean indicando si, por las condiciones mencionadas, las verificaciones implican un nivel de validacion Medio.
	 *@param verificaciones una lista de verificaciones cuyos participantes son todos del mismo nivel o equiparables.
	 */
	private Boolean esNivelMedio(ArrayList<Verificacion> verificaciones) {
		Boolean retorno = false;

		//Valida si hay dos verificaciones filtradas. Nunca puede llegar una verificaci�n de experto a este punto ya que se filtran en el m�todo esNivelAlto()
		if (verificaciones.size() == 2) {
			retorno = true;
		}
		return retorno;
	}
	

	/**
	 * Este m�todo implementa filtrarVerifDeUsuarios y luego determina cu�l es el tipo que m�s se comparte. De no haber un tipo compartido,
	 * se define el tipo como "Ninguno"
	 *@return String indicando el tipo compartido por las verificaciones.
	 *@param verificaciones una lista de verificaciones cualquiera.
	 *@see this.filtrarVerifDeUsuarios(ArrayList<Verificacion> verificaciones)
	 */
	@Override
	public String definirTipo(ArrayList<Verificacion> verificaciones) {
		ArrayList<Verificacion> verificacionesFiltradas = this.filtrarVerifDeUsuarios(verificaciones);
		Set<String> tiposVerificados = new HashSet<String>();
		String tipo = "Ninguno";
		
		if (verificacionesFiltradas.size()==1) {
			tipo = verificacionesFiltradas.get(0).getTipo();
		}
		
		for (Verificacion verif : verificacionesFiltradas) {
			tiposVerificados.add(verif.getTipo()); 
		}
		
		if (tiposVerificados.size() == 1) {
			tipo = tiposVerificados.iterator().next();
		}

		return tipo;
	}

	/**
	 * Este m�todo filtra las verificaciones por aquellas de participantes de mayor nivel.
	 *@return String indicando el tipo compartido por las verificaciones.
	 *@param verificaciones una lista de verificaciones cualquiera.
	 */
	@Override
	public ArrayList<Verificacion> filtrarVerifDeUsuarios(ArrayList<Verificacion> verificaciones) {
		
		Verificacion verificacionDeUsuarioMasAlto = verificaciones.stream()
				.max((Verificacion ver1, Verificacion ver2)->ver1.getParticipante().getNivel()
						.compareTo(ver2.getParticipante().getNivel())).get();
		
		ArrayList<Verificacion> verificacionesRetorno = verificaciones.stream()
				.filter(v -> v.getParticipante().getNivel().equals(verificacionDeUsuarioMasAlto.getParticipante().getNivel()))
				.collect(Collectors.toCollection(ArrayList::new));
		
		return verificacionesRetorno;
	}

}