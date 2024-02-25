package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTOS_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES = 0.2;
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		
		Ruta rutaV = vuelo.getRuta();
		Aeropuerto punOrigen = rutaV.getOrigen();
		Aeropuerto punDestino = rutaV.getDestino();
		int distancia = Aeropuerto.calcularDistancia(punOrigen, punDestino);
		String tipoCliente = cliente.getTipoCliente();
		int total = 0;
		
		if(tipoCliente == ClienteNatural.NATURAL) {
			total = distancia * this.COSTO_POR_KM_NATURAL;
			
		} else if(tipoCliente == ClienteCorporativo.CORPORTATIVO) {
			int totalSinDesc = distancia * this.COSTOS_POR_KM_CORPORATIVO;
			double descApl = calcularPorcentajeDescuento(cliente);
			double totalDouble = totalSinDesc - (totalSinDesc * descApl);
			total = (int) totalDouble;
		}
		
		return total;
		
	}
	
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
		ClienteCorporativo clienteCorp = (ClienteCorporativo) cliente;
		int tamanoEmp = clienteCorp.getTamanoEmpresa();
		double desc = 0;
		
		switch(tamanoEmp) {
			case 1:
				desc = this.DESCUENTO_GRANDES;
			
			case 2:
				desc = this.DESCUENTO_MEDIANAS;
				
			case 3:
				desc = this.DESCUENTO_PEQ;
		}
		
		return desc;
		
	}
	
}
