package model;

import java.util.ArrayList;

public class Banco {
	private ArrayList <Cliente> clientes;
	private ArrayList<TipoDeSolicitudDeCredito> solicitudes;
	private TipoDeSolicitudDeCredito solicitud; 
	
	public Banco () {
		this.clientes = new ArrayList<Cliente>();
		this.solicitudes = new ArrayList<TipoDeSolicitudDeCredito>();
	}

	public void solicitarCredito (Cliente cliente, Integer monto, Integer plazo, String tipoDeSolicitud ) {
		this.solicitud = TipoDeSolicitudDeCredito (cliente, monto, plazo, tipoDesolicitud)
	}

	public Boolean evaluarSolicitudDeCredito ( TipoDeSolicitudDeCredito solicitud) {
		return solicitud.esAceptable;
	}

	public void agregarCliente(Cliente cliente) {
		this.clientes.add(cliente);	
	}
} 
