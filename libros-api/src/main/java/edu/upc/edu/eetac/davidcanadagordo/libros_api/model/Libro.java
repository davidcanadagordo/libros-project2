package edu.upc.edu.eetac.davidcanadagordo.libros_api.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibroResource;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.MediaType;

public class Libro {
	
	/*
	@InjectLinks({
		@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Libro", type = MediaType.LIBROS_API_LIBRO, method = "getLibro", bindings = @Binding(name = "id", value = "${instance.id}")), 
		@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "crear-libro", title = "Crear Libro", type = MediaType.LIBROS_API_LIBRO, method = "CreateLibro")
   })*/
	
	@InjectLinks({
		@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "stings", title = "Latest stings", type = MediaType.LIBROS_API_LIBRO_COLLECTION),
		@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Sting", type = MediaType.LIBROS_API_LIBRO, method = "getLibro", bindings = @Binding(name = "id", value = "${instance.id}"))
		
	})
	
	private List<Link> links;
	int id;
	private String titulo;
	private String lengua;
	private String edicion;
	private Date fecha_ed;
	private Date fecha_imp;
	private String editorial;
	private String autor;

	private List<Resena> resena= new ArrayList<Resena>();
	private List<Autor> autores = new ArrayList<Autor>();
	
	public void setResena(List<Resena> resena) {
		this.resena = resena;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLengua() {
		return lengua;
	}
	public void setLengua(String lengua) {
		this.lengua = lengua;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
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
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public List<Resena> getResena() {
		return resena;
	}
	public void addResena(Resena resena) {
		resena.add(resena);
	}
	/*public void setReviews(List<Reviews> review) {
		this.reviews= review
		}*/
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	
	/*public int id;
	public String autor;
	public String lengua;
	public String edicion;
	public Date fecha_ed;
	public Date fecha_imp;
	public String editorial;
	public String titulo;
	private List<Link> links;
	
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
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
    */
	
}

	
	
	