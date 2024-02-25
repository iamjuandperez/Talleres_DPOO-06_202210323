package uniandes.dpoo.aerolinea.modelo;

import java.lang.Integer;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre
 * una aerolínea.
 */
public class Ruta {
	// TODO completar
	private String horaSalida;
	private String horaLlegada;
	private String codigoRuta;
	private Aeropuerto destino;
	private Aeropuerto origen;

	/**
	 * Dada una cadena con una hora y minutos, retorna los minutos.
	 * 
	 * Por ejemplo, para la cadena '715' retorna 15.
	 * 
	 * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan
	 *                     los dos últimos caracteres
	 * @return Una cantidad de minutos entre 0 y 59
	 */

	public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {

		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.codigoRuta = codigoRuta;
		this.destino = destino;
		this.origen = origen;

	}

	public String getCodigoRuta() {

		return this.codigoRuta;

	}

	public Aeropuerto getOrigen() {

		return this.origen;

	}

	public Aeropuerto getDestino() {

		return this.destino;

	}

	public String getHoraSalida() {

		return this.horaSalida;

	}

	public int getDuracion() {

		int duracion = 0;
		int totalHoras = 0;
		int totalMin = 0;

		int horaSa = getHoras(this.horaSalida);
		int horaLl = getHoras(this.horaLlegada);
		int minSa = getMinutos(this.horaSalida);
		int minLl = getMinutos(this.horaLlegada);

		if (horaLl < horaSa) {
			totalHoras = horaLl + (24 - horaSa);

		} else {
			totalHoras = horaLl - horaSa;
		}

		if (minLl > horaSa) {
			totalMin = minLl - minSa;
		} else {
			totalMin = minSa - minLl;
		}

		duracion = (totalHoras * 60) + totalMin;

		return duracion;

	}

	public static int getMinutos(String horaCompleta) {
		int minutos = Integer.parseInt(horaCompleta) % 100;
		return minutos;
	}

	/**
	 * Dada una cadena con una hora y minutos, retorna las horas.
	 * 
	 * Por ejemplo, para la cadena '715' retorna 7.
	 * 
	 * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan
	 *                     los dos últimos caracteres
	 * @return Una cantidad de horas entre 0 y 23
	 */
	public static int getHoras(String horaCompleta) {
		int horas = Integer.parseInt(horaCompleta) / 100;
		return horas;
	}

}
