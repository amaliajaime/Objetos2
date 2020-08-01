package src;

public class FuncionLanzarAlerta implements FuncionalidadExterna {

	@Override
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zonaDeCob, Muestra muestra) {
		
		System.out.println("Nueva Amenaza");

	}

}
