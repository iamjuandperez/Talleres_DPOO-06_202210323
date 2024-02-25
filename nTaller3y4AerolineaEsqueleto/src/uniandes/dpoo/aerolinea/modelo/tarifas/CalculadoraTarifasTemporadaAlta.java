package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

	protected final int COSTO_POR_KM = 1000;

	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {

		Ruta rutaV = vuelo.getRuta();
		Aeropuerto punOrigen = rutaV.getOrigen();
		Aeropuerto punDestino = rutaV.getDestino();
		int distancia = Aeropuerto.calcularDistancia(punOrigen, punDestino);

		return distancia * this.COSTO_POR_KM;

	}

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {

		return 0.0;
	}

}
