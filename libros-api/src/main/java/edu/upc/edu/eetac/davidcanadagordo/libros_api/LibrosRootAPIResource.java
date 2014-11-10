package edu.upc.edu.eetac.davidcanadagordo.libros_api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.LibroRootAPI;

@Path("/")
public class LibrosRootAPIResource {
	@GET
	public LibroRootAPI getRootAPI() {
		LibroRootAPI api = new LibroRootAPI();
		return api;
	}
}

