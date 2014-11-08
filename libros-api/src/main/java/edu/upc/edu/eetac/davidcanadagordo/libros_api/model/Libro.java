package edu.upc.edu.eetac.davidcanadagordo.libros_api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Link;

public class Libro {
	

	public int id;
	public String autor;
	public String lengua;
	public String edicion;
	public Date fecha_ed;
	public Date fecha_imp;
	public String editorial;
	public String titulo;
	private List<Link> links = new ArrayList<Link>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getLengua() {
		return lengua;
	}
	public void setLengua(String lengua) {
		this.lengua = lengua;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public Date getFecha_ed() {
		return fecha_ed;
	}
	public void setFecha_ed(Date fecha_ed) {
		this.fecha_ed = fecha_ed;
	}
	public Date getFecha_imp() {
		return fecha_imp;
	}
	public void setFecha_imp(Date fecha_imp) {
		this.fecha_imp = fecha_imp;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void add(Link link) {
		links.add(link);
	}
}

	
	
	