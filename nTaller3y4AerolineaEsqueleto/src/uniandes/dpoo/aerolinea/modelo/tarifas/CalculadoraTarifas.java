package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;


public abstract class CalculadoraTarifas {

	public static final double IMPUESTO = 0.28;
	
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		
		int costoBas = calcularCostoBase(vuelo, cliente);
		double descuento = calcularPorcentajeDescuento(cliente);
		double costoConDescuento = costoBas - (costoBas * descuento);
		double impuesto = costoConDescuento * CalculadoraTarifas.IMPUESTO;
		
		double total = costoConDescuento + impuesto;
		
		int totalInt = (int) total;
		
		return totalInt;
		
	}
	
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		
		Aeropuerto aeroOrigen = ruta.getOrigen();
		Aeropuerto aeroDestino = ruta.getDestino();		
		
		int distancia = Aeropuerto.calcularDistancia(aeroOrigen, aeroDestino);
		
		return distancia;
		
	}
	
	protected int calcularValorImpuestos(int costoBase) {
		
		double imp = costoBase * CalculadoraTarifas.IMPUESTO;
		
		int impInt = (int) imp;
		
		return impInt;
		
	}
	
}
