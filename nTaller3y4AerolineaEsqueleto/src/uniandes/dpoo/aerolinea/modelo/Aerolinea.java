package uniandes.dpoo.aerolinea.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * En esta clase se organizan todos los aspectos relacionados con una Aerolínea.
 * 
 * Por un lado, esta clase cumple un rol central como estructurador para todo el
 * resto de elementos: directa o indirectamente, todos están contenidos y se
 * pueden acceder a través de la clase Aerolínea.
 * 
 * Por otro lado, esta clase implementa algunas funcionalidades adicionales a su
 * rol como estructurador, para lo cual se apoya en las otras clases que hacen
 * parte del proyecto.
 */
public class Aerolinea {
	/**
	 * Una lista con los aviones de los que dispone la aerolínea
	 */
	private List<Avion> aviones;

	/**
	 * Un mapa con las rutas que cubre la aerolínea.
	 * 
	 * Las llaves del mapa son el código de la ruta, mientras que los valores son
	 * las rutas
	 */
	private Map<String, Ruta> rutas;

	/**
	 * Una lista de los vuelos programados por la aerolínea
	 */
	private List<Vuelo> vuelos;

	/**
	 * Un mapa con los clientes de la aerolínea.
	 * 
	 * Las llaves del mapa son los identificadores de los clientes, mientras que los
	 * valores son los clientes
	 */
	private Map<String, Cliente> clientes;

	/**
	 * Construye una nueva aerolínea con un nombre e inicializa todas las
	 * contenedoras con estructuras vacías
	 */
	public Aerolinea() {
		aviones = new LinkedList<Avion>();
		rutas = new HashMap<String, Ruta>();
		vuelos = new LinkedList<Vuelo>();
		clientes = new HashMap<String, Cliente>();
	}

	// ************************************************************************************
	//
	// Estos son los métodos que están relacionados con la manipulación básica de
	// los atributos
	// de la aerolínea (consultar, agregar)
	//
	// ************************************************************************************

	/**
	 * Agrega una nueva ruta a la aerolínea
	 * 
	 * @param ruta
	 */
	public void agregarRuta(Ruta ruta) {
		this.rutas.put(ruta.getCodigoRuta(), ruta);
	}

	/**
	 * Agrega un nuevo avión a la aerolínea
	 * 
	 * @param avion
	 */
	public void agregarAvion(Avion avion) {
		this.aviones.add(avion);
	}

	/**
	 * Agrega un nuevo cliente a la aerolínea
	 * 
	 * @param cliente
	 */
	public void agregarCliente(Cliente cliente) {
		this.clientes.put(cliente.getIdentificador(), cliente);
	}

	/**
	 * Verifica si ya existe un cliente con el identificador dado
	 * 
	 * @param identificadorCliente
	 * @return Retorna true si ya existía un cliente con el identificador,
	 *         independientemente de su tipo
	 */
	public boolean existeCliente(String identificadorCliente) {
		return this.clientes.containsKey(identificadorCliente);
	}

	/**
	 * Busca el cliente con el identificador dado
	 * 
	 * @param identificadorCliente
	 * @return Retorna el cliente con el identificador, o null si no existía
	 */
	public Cliente getCliente(String identificadorCliente) {
		return this.clientes.get(identificadorCliente);
	}

	/**
	 * Retorna todos los aviones de la aerolínea
	 * 
	 * @return
	 */
	public Collection<Avion> getAviones() {
		return aviones;
	}

	/**
	 * Retorna todas las rutas disponibles para la aerolínea
	 * 
	 * @return
	 */
	public Collection<Ruta> getRutas() {
		return rutas.values();
	}

	/**
	 * Retorna la ruta de la aerolínea que tiene el código dado
	 * 
	 * @param codigoRuta El código de la ruta buscada
	 * @return La ruta con el código, o null si no existe una ruta con ese código
	 */
	public Ruta getRuta(String codigoRuta) {
		return rutas.get(codigoRuta);
	}

	/**
	 * Retorna todos los vuelos de la aerolínea
	 * 
	 * @return
	 */
	public Collection<Vuelo> getVuelos() {
		return vuelos;
	}

	/**
	 * Busca un vuelo dado el código de la ruta y la fecha del vuelo.
	 * 
	 * @param codigoRuta
	 * @param fechaVuelo
	 * @return Retorna el vuelo que coincide con los parámetros dados. Si no lo
	 *         encuentra, retorna null.
	 */
	public Vuelo getVuelo(String codigoRuta, String fechaVuelo) {
		List<Vuelo> listaVuelos = this.vuelos;
		List<Vuelo> vuelosFechaDada = new ArrayList<Vuelo>();
		boolean encontrado = false;
		Vuelo vueloResp = null;

		for (int i = 0; i < listaVuelos.size(); i++) {
			if (listaVuelos.get(i).getFecha() == fechaVuelo) {
				vuelosFechaDada.add(listaVuelos.get(i));
			}
		}

		for (int j = 0; j < vuelosFechaDada.size(); j++) {
			if (vuelosFechaDada.get(j).getRuta().getCodigoRuta() == codigoRuta) {
				encontrado = true;
				vueloResp = vuelosFechaDada.get(j);
			}

		}

		if (encontrado) {
			return vueloResp;
		}

		else {
			return null;
		}
		// TODO implementar
	}

	/**
	 * Retorna todos los clientes de la aerolínea
	 * 
	 * @return
	 */
	public Collection<Cliente> getClientes() {
		return clientes.values();
	}

	/**
	 * Retorna todos los tiquetes de la aerolínea, los cuales se recolectan vuelo
	 * por vuelo
	 * 
	 * @return
	 */
	public Collection<Tiquete> getTiquetes() {
		// TODO implementar

		List<Vuelo> listaVuelos = this.vuelos;
		List<Tiquete> listaTiquetes = new ArrayList<Tiquete>();

		for (int i = 0; i < listaVuelos.size(); i++) {

			for (Iterator<Tiquete> tiq = listaVuelos.get(i).getTiquetes().iterator(); tiq.hasNext();) {

				Tiquete tiqActual = tiq.next();
				listaTiquetes.add(tiqActual);

			}
		}

		return listaTiquetes;

	}

	// ************************************************************************************
	//
	// Estos son los métodos que están relacionados con la persistencia de la
	// aerolínea
	//
	// ************************************************************************************

	/**
	 * Carga toda la información de la aerolínea a partir de un archivo
	 * 
	 * @param archivo     El nombre del archivo.
	 * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o
	 *                    CentralPersistencia.PLAIN.
	 * @throws TipoInvalidoException             Se lanza esta excepción si se
	 *                                           indica un tipo de archivo inválido
	 * @throws IOException                       Lanza esta excepción si hay
	 *                                           problemas leyendo el archivo
	 * @throws InformacionInconsistenteException Lanza esta excepción si durante la
	 *                                           carga del archivo se encuentra
	 *                                           información que no es consistente
	 */
	public void cargarAerolinea(String archivo, String tipoArchivo)
			throws TipoInvalidoException, IOException, InformacionInconsistenteException {
		// TODO implementar
		IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
		cargador.cargarAerolinea(archivo, this);

	}

	/**
	 * Salva la información de la aerlínea en un archivo
	 * 
	 * @param archivo     El nombre del archivo.
	 * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o
	 *                    CentralPersistencia.PLAIN.
	 * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de
	 *                               archivo inválido
	 * @throws IOException           Lanza esta excepción si hay problemas
	 *                               escribiendo en el archivo
	 */
	public void salvarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
		// TODO implementar
		IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
		cargador.salvarAerolinea(archivo, this);
	}

	/**
	 * Carga toda la información de sobre los clientes y tiquetes de una aerolínea a
	 * partir de un archivo
	 * 
	 * @param archivo     El nombre del archivo.
	 * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o
	 *                    CentralPersistencia.PLAIN.
	 * @throws TipoInvalidoException             Se lanza esta excepción si se
	 *                                           indica un tipo de archivo inválido
	 * @throws IOException                       Lanza esta excepción si hay
	 *                                           problemas leyendo el archivo
	 * @throws InformacionInconsistenteException Lanza esta excepción si durante la
	 *                                           carga del archivo se encuentra
	 *                                           información que no es consistente
	 *                                           con la información de la aerolínea
	 */
	public void cargarTiquetes(String archivo, String tipoArchivo)
			throws TipoInvalidoException, IOException, InformacionInconsistenteException {
		IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
		cargador.cargarTiquetes(archivo, this);
	}

	/**
	 * Salva la información de la aerlínea en un archivo
	 * 
	 * @param archivo     El nombre del archivo.
	 * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o
	 *                    CentralPersistencia.PLAIN.
	 * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de
	 *                               archivo inválido
	 * @throws IOException           Lanza esta excepción si hay problemas
	 *                               escribiendo en el archivo
	 */
	public void salvarTiquetes(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
		IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes(tipoArchivo);
		cargador.salvarTiquetes(archivo, this);
	}

	// ************************************************************************************
	//
	// Estos son los métodos que están relacionados con funcionalidades interesantes
	// de la aerolínea
	//
	// ************************************************************************************

	/**
	 * Agrega un nuevo vuelo a la aerolínea, para que se realice en una cierta
	 * fecha, en una cierta ruta y con un cierto avión.
	 * 
	 * Este método debe verificar que el avión seleccionado no esté ya ocupado para
	 * otro vuelo en el mismo intervalo de tiempo del nuevo vuelo. No es necesario
	 * verificar que se encuentre en el lugar correcto (origen del vuelo).
	 * 
	 * @param fecha       La fecha en la que se realizará el vuelo
	 * @param codigoRuta  La ruta que cubirá el vuelo
	 * @param nombreAvion El nombre del avión que realizará el vuelo
	 * @throws Exception Lanza esta excepción si hay algún problema con los datos
	 *                   suministrados
	 */
	public void programarVuelo(String fecha, String codigoRuta, String nombreAvion) throws Exception {
		// TODO Implementar el método
		List<Vuelo> listaVuelos = this.vuelos;
		boolean programPosible = true;

		for (int i = 0; i < listaVuelos.size(); i++) {
			Vuelo vueloAct = listaVuelos.get(i);
			String fechaVuelo = vueloAct.getFecha();
			String avionVuelo = vueloAct.getAvion().getNombre();

			if (fechaVuelo.equals(fecha) && avionVuelo.equals(nombreAvion)) {
				System.err.println("Avión en uso durante esa fecha");
				programPosible = false;
			}

		}

		if (programPosible) {

			Avion avionProg = null;

			for (int j = 0; j < this.aviones.size(); j++) {
				if (this.aviones.get(j).getNombre().equals(nombreAvion)) {

					avionProg = this.aviones.get(j);

				}

			}

			Ruta rutaProg = this.rutas.get(codigoRuta);
			Vuelo vueloProg = new Vuelo(rutaProg, fecha, avionProg);
			this.vuelos.add(vueloProg);

		}

	}

	/**
	 * Vende una cierta cantidad de tiquetes para un vuelo, verificando que la
	 * información sea correcta.
	 * 
	 * Los tiquetes deben quedar asociados al vuelo y al cliente.
	 * 
	 * Según la fecha del vuelo, se deben usar las tarifas de temporada baja (enero
	 * a mayo y septiembre a noviembre) o las de temporada alta (el resto del año).
	 * 
	 * @param identificadorCliente El identificador del cliente al cual se le venden
	 *                             los tiquetes
	 * @param fecha                La fecha en la que se realiza el vuelo para el
	 *                             que se van a vender los tiquetes
	 * @param codigoRuta           El código de la ruta para el que se van a vender
	 *                             los tiquetes
	 * @param cantidad             La cantidad de tiquetes que se quieren comprar
	 * @return El valor total de los tiquetes vendidos
	 * @throws VueloSobrevendidoException Se lanza esta excepción si no hay
	 *                                    suficiente espacio en el vuelo para todos
	 *                                    los pasajeros
	 * @throws Exception                  Se lanza esta excepción para indicar que
	 *                                    no se pudieron vender los tiquetes por
	 *                                    algún otro motivo
	 */
	public int venderTiquetes(String identificadorCliente, String fecha, String codigoRuta, int cantidad)
			throws VueloSobrevendidoException, Exception {
		// TODO Implementar el método
		Cliente clienteComp = this.clientes.get(identificadorCliente);
		List<Vuelo> listaVuelos = this.vuelos;
		Vuelo vueloComp = null;
		Avion avionComp;
		int capacidadAvion = 0;
		int ocupacionVuelo = 0;
		int total = 0;

		for (int i = 0; i < listaVuelos.size(); i++) {

			String fechaVueloComp = listaVuelos.get(i).getFecha();
			Ruta rutaVueloComp = listaVuelos.get(i).getRuta();
			String codRutaVueloComp = rutaVueloComp.getCodigoRuta();

			if (fechaVueloComp.equals(fecha) && codRutaVueloComp.equals(codigoRuta)) {

				vueloComp = listaVuelos.get(i);
				avionComp = vueloComp.getAvion();
				capacidadAvion = avionComp.getCapacidad();
				ocupacionVuelo = vueloComp.getTiquetes().size();

			}

		}

		if (capacidadAvion >= (ocupacionVuelo + cantidad)) {

			String[] fechaSepar = fecha.split("-");
			String mes = fechaSepar[1];

			if (mes.equals("01") || mes.equals("02") || mes.equals("03") || mes.equals("04") || mes.equals("05")
					|| mes.equals("09") || mes.equals("10") || mes.equals("11")) {

				CalculadoraTarifas calcTar = new CalculadoraTarifasTemporadaBaja();
				total = vueloComp.venderTiquetes(clienteComp, calcTar, cantidad);

			}

			else {
				CalculadoraTarifas calcTar = new CalculadoraTarifasTemporadaAlta();
				total = vueloComp.venderTiquetes(clienteComp, calcTar, cantidad);
			}

		}

		return total;
	}

	/**
	 * Registra que un cierto vuelo fue realizado
	 * 
	 * @param fecha      La fecha del vuelo
	 * @param codigoRuta El código de la ruta que recorrió el vuelo
	 */
	public void registrarVueloRealizado(String fecha, String codigoRuta) {
		// TODO Implementar el método
		List<Vuelo> listaVuelos = this.vuelos;
		boolean eliminado = false;

		int i = 0;
		while (i < listaVuelos.size() && eliminado != true) {

			Vuelo vueloAct = listaVuelos.get(i);
			String fechaVuelo = vueloAct.getFecha();
			Ruta rutaVuelo = vueloAct.getRuta();
			String codRut = rutaVuelo.getCodigoRuta();

			if (fechaVuelo.equals(fecha) && codRut.equals(codigoRuta)) {
				this.vuelos.remove(i);
				eliminado = true;
			}

			i++;
		}

	}

	/**
	 * Calcula cuánto valen los tiquetes que ya compró un cliente dado y que todavía
	 * no ha utilizado
	 * 
	 * @param identificadorCliente El identificador del cliente
	 * @return La suma de lo que pagó el cliente por los tiquetes sin usar
	 */
	public String consultarSaldoPendienteCliente(String identificadorCliente) {
		// TODO Implementar el método
		Cliente clienteAct = this.clientes.get(identificadorCliente);
		List<Tiquete> tiqSinUsar = clienteAct.getTiquetesSinUsar();
		int total = 0;

		for (int i = 0; i < tiqSinUsar.size(); i++) {

			Tiquete tiqAct = tiqSinUsar.get(i);
			total += tiqAct.getTarifa();

		}

		return String.valueOf(total);
	}

}
