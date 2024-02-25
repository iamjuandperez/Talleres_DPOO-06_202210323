package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;


public class Tiquete {

	private String codigo;
	private int tarifa;
	private boolean usado;
	private Cliente cliente;
	private Vuelo vuelo;
	
	public Tiquete(String codigo, Vuelo vuelo, 
			Cliente clienteComprador, int tarifa){
		
		Tiquete tiq = GeneradorTiquetes.generarTiquete(vuelo, clienteComprador, tarifa);
		this.codigo = tiq.getCodigo();
		this.tarifa = tarifa;
		this.usado = false;
		this.cliente = clienteComprador;
		this.vuelo = vuelo;
		clienteComprador.agregarTiquete(tiq);
		
		
	}
	
	public Cliente getCliente() {
		
		return this.cliente;
		
	}
	
	public Vuelo getVuelo() {
		
		return this.vuelo;
		
	}
	
	public String getCodigo() {
		
		return this.codigo;
		
	}
	
	public int getTarifa() {
		
		return this.tarifa;
		
	}
	
	public void marcarComoUsado() {
		
		this.usado = true;
		
	}
	
	public boolean esUsado() {
		
		return this.usado;
		}
		
	}
