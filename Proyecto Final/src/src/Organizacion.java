package src;

import java.util.ArrayList;

public  class Organizacion implements ObservadorZonaDeCobertura{
	
	//atributos
	private Ubicacion ubicacionOrg;
	private String tipoDeOrganizacion;
	private int cantidadDeEmpleados;
	private	ArrayList<ZonaDeCobertura>zonasDeCoberturaReg= new ArrayList<ZonaDeCobertura>();
	private FuncionalidadExterna funcionalidad;
	private FuncionalidadExterna funcionalidadAgregarMuestra;
	private FuncionalidadExterna funcionalidadAgregarVerificacion;
	
	//constructores
	public Organizacion(Ubicacion ubicacion, String tipoDeOrg, int cantEmpleados){
		this.ubicacionOrg = ubicacion;
		this.tipoDeOrganizacion = tipoDeOrg;
		this.cantidadDeEmpleados = cantEmpleados;
		this.funcionalidadAgregarMuestra=new FuncionLanzarAlerta();
		this.funcionalidadAgregarVerificacion=new FuncionImprimirReporte();
		
	}

	public void agregarfuncionalidad(FuncionalidadExterna unaFuncionalidad) {
		this.funcionalidad=unaFuncionalidad;
	}
	public FuncionalidadExterna obtenerFuncionalidad() {
		return this.funcionalidad;

	}
	public Ubicacion getUbicacion() {
		return this.ubicacionOrg;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacionOrg = ubicacion ;
	}
	
	public String getTipoDeOrganizacion() {
		return this.tipoDeOrganizacion;
	}
	
	public void setTipoDeOrganizacion(String tipoDeOrg) {
		this.tipoDeOrganizacion=tipoDeOrg;
	}
	
	public int getCantidadDeEmpleados() {
		return this.cantidadDeEmpleados;
	}

	public ArrayList<ZonaDeCobertura> getZonasDeCoberturaRegistradas() {
		return this.zonasDeCoberturaReg;
	}

	public void agregarZonaReg(ZonaDeCobertura unaZona) {
		this.zonasDeCoberturaReg.add(unaZona);
		unaZona.agregarObservador(this);
	}
	
	public void eliminarZonareg(ZonaDeCobertura unaZona) {
		this.zonasDeCoberturaReg.remove(unaZona);
	}
	
	@Override
	public void actualizarAgregueMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra){
	    this.agregarfuncionalidad(funcionalidadAgregarMuestra);
	    
	    this.funcionalidad.nuevoEvento(this,zonaDeCobertura,muestra);
	}
	
	@Override
	public void actualizarMuestraAgregoVerificacion(ZonaDeCobertura zonaDeCobertura,Muestra muestra) {
		this.agregarfuncionalidad(funcionalidadAgregarVerificacion);
	
		this .funcionalidad.nuevoEvento(this,zonaDeCobertura,muestra);
	}
	
}	
