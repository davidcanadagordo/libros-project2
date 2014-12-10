package edu.upc.edu.eetac.davidcanadagordo.libros_api;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibrosRootAPIResource;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.MediaType;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibroResource;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

public class LibroRootAPI {
	
	@InjectLinks({
	@InjectLink(resource = LibrosRootAPIResource.class, style = Style.ABSOLUTE, rel = "self bookmark home", title = "Libros Root API", method = "getRootAPI"),
	@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "libros", title = "Latest stings", type = MediaType.LIBROS_API_LIBRO_COLLECTION),
	@InjectLink(resource = LibroResource.class, style = Style.ABSOLUTE, rel = "crear", title = "Latest stings", type = MediaType.LIBROS_API_LIBRO) })
	private List<Link> links;
	 
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
