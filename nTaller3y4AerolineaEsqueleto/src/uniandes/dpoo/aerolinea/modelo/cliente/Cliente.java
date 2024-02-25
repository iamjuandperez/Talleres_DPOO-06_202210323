package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {

	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;

	public Cliente() {

		tiquetesSinUsar = new ArrayList<Tiquete>();
		tiquetesUsados = new ArrayList<Tiquete>();

	}

	public abstract String getTipoCliente();

	public abstract String getIdentificador();

	public void agregarTiquete(Tiquete tiquete) {

		tiquetesSinUsar.add(tiquete);

	}

	public int calcularValorTotalTiquete() {

		int total = 0;

		for (int i = 0; i < tiquetesSinUsar.size(); i++) {

			total += tiquetesSinUsar.get(i).getTarifa();

		}

		for (int j = 0; j < tiquetesUsados.size(); j++) {

			total += tiquetesUsados.get(j).getTarifa();

		}

		return total;

	}

	public void usarTiquetes(Vuelo vuelo) {

		Tiquete tiqAct = null;

		for (int i = 0; i < this.tiquetesSinUsar.size(); i++) {

			Vuelo vueloAct = this.tiquetesSinUsar.get(i).getVuelo();

			if (vuelo.equals(vueloAct)) {

				tiqAct = this.tiquetesSinUsar.get(i);
				this.tiquetesSinUsar.remove(i);
				tiquetesUsados.add(tiqAct);
			}

		}

	}

	public List<Tiquete> getTiquetesSinUsar() {

		return this.tiquetesSinUsar;
	}

}
