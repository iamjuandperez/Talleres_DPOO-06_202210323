package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTOS_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES = 0.2;
	
	public int calcularCostosBase(Vuelo vuelo) {
		
	}
	
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
	}
	
}
