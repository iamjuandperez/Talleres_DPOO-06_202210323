package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;

public class Vuelo {

	private String fecha;
	private Avion avion;
	private Ruta ruta;
	private List<Tiquete> tiquetes;

	public Vuelo(Ruta ruta, String fecha, Avion avion) {

		this.fecha = fecha;
		this.avion = avion;
		this.ruta = ruta;

	}

	public Ruta getRuta() {

		return this.ruta;

	}

	public String getFecha() {

		return this.fecha;

	}

	public Avion getAvion() {

		return this.avion;

	}

	public Collection<Tiquete> getTiquetes() {

		return this.tiquetes;

	}

	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
			throws VueloSobrevendidoException {

		Vuelo vueloCalc = new Vuelo(this.ruta, this.fecha, this.avion);
		int total = calculadora.calcularTarifa(vueloCalc, cliente);
		Tiquete tiquete = GeneradorTiquetes.generarTiquete(vueloCalc, cliente, total);
		tiquetes.add(tiquete);
		cliente.getTiquetesSinUsar().add(tiquete);

		return total;

	}

	public boolean equals(Object obj) {

		return equals(obj);

	}
}
