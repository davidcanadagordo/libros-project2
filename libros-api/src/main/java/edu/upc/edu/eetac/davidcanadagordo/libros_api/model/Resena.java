package edu.upc.edu.eetac.davidcanadagordo.libros_api.model;

import java.sql.Date;

public class Resena {
	
	private int idres;
	private String username;
	private Date fecha;
	private String texto;
	private int idlibros;
	
	public int getIdres() {
		return idres;
	}
	public void setIdres(int idres) {
		this.idres = idres;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getIdlibros() {
		return idlibros;
	}
	public void setIdlibros(int idlibros) {
		this.idlibros = idlibros;
	}
	
	
}
