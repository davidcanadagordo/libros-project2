package edu.upc.edu.eetac.davidcanadagordo.libros_api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibroResource;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.MediaType;
 

public class LibrosCollection {
	
	  @InjectLinks({
		  @InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "self", title = "libros", type = MediaType.LIBROS_API_LIBRO_COLLECTION),
		  @InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "libro", title = "libro", type = MediaType.LIBROS_API_LIBRO)
			})
	private List<Link> links;
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	private List<Libro> libros;


	public List<Libro> getLibros() {
		return libros;
	}

	public LibrosCollection() {
		super();
		libros = new ArrayList<>();
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

