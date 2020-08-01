package src;

public interface ObservadorZonaDeCobertura {

		void actualizarAgregueMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

		void actualizarMuestraAgregoVerificacion(ZonaDeCobertura zonaDeCobertura,
				Muestra muestra);
}