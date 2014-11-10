package edu.upc.edu.eetac.davidcanadagordo.libros_api.model;

import java.util.ArrayList;
import java.util.List;
 

public class LibrosCollection {

	private List<Libro> libros;
 
	public LibrosCollection() {
		super();
		libros = new ArrayList<Libro>();
	}
 
	public List<Libro> getLibro() {
		return libros;
	}
 
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
 
	public void add(Libro libro) {
		libros.add(libro);
	}

}

