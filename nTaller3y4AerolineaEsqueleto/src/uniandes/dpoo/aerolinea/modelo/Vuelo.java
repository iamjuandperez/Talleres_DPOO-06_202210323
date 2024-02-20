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
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public class Vuelo {

	private String fecha;
	private Avion avion;
	private Ruta ruta;
	private List<Tiquete> tiquetes;
	
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		
	}
	
	public Ruta getRuta() {
		
	}
	
	public String getFecha() {
		
	}
	
	public Avion getAvion() {
		
	}
	
	public Collection<Tiquete> getTiquetes(){
		
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora,
			int cantidad) {
		
	}
	
	public boolean equals(Object obj) {
		
	}
}
