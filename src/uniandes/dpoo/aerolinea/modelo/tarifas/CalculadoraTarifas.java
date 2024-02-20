package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;




public abstract class CalculadoraTarifas {

	public double IMPUESTO = 0.28;
	
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		
	}
	
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		
	}
	
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		
	}
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		
	}
	
	protected int calcularValorImpuestos(int costoBase) {
		
	}
	
}
