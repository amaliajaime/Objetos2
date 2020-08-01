package model;

public class SolicitudCreditoPersonal extends TipoDeSolicitudDeCredito{

	@Override
	public boolean esAceptable() {
		
		return  & true;
	}
	
	private boolean evaluarSueldoAnual(double ingresosAnuales) {
		return this.getCliente().getSueldoAnual() >= ingresosAnuales ;
	} 
	
}
