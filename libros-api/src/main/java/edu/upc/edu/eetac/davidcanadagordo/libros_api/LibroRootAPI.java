package edu.upc.edu.eetac.davidcanadagordo.libros_api;
/*
import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibrosRootAPIResource;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.MediaType;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibroResource;
*/

import java.util.List;
import javax.ws.rs.core.Link;
public class LibroRootAPI {
	
	private List<Link> links;
	 
	public List<Link> getLinks() {
		return links;
	}
 
	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
