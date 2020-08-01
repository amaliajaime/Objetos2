package model;

	public abstract class TipoDeSolicitudDeCredito {
		protected Cliente cliente;
		protected Integer monto;
		protected Integer plazo;
		protected Boolean esAceptable;
	
		public TipoDeSolicitudDeCredito ( Cliente cliente, Integer monto, Integer plazo) {
			this.cliente = cliente;
			this.monto = monto;
			this.plazo = plazo;
		}
			
		protected Cliente getCliente() {
			return cliente;
		}
	
		protected Integer getMonto() {
			return monto;
		}
	
		protected Integer getPlazo() {
			return plazo;
		}
	
		public abstract boolean esAceptable();
		
	}
