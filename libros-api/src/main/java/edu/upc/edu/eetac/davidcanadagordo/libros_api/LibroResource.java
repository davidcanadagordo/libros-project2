package edu.upc.edu.eetac.davidcanadagordo.libros_api;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.mysql.jdbc.Statement;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.model.Autor;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.model.Libro;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.model.LibrosCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Path("/libros")
public class LibroResource {
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	private SecurityContext security;
	private boolean admin, registered;
	
////muestra la BD de libros
	private String GET_LIBROS_QUERY = "SELECT * FROM libros";
	
	@GET
	@Produces(MediaType.LIBROS_API_LIBRO_COLLECTION)
	public LibrosCollection getLibros() {
		
		System.out.println("no conectados a la BD"); 
		LibrosCollection libros = new LibrosCollection();
	   
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
		System.out.println("conectados a la BD");
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_LIBROS_QUERY);
			ResultSet rs = stmt.executeQuery();
		
			System.out.println(stmt);
			while (rs.next()) 
			{
				
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setLengua(rs.getString("lengua"));
				libro.setEdicion(rs.getString("edicion"));
				libro.setFecha_ed(rs.getDate("fecha_ed"));
				libro.setFecha_imp(rs.getDate("fecha_imp"));
				libro.setEditorial(rs.getString("editorial"));
				
				
				libros.add(libro);
			    
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 
		return libros;
	}

////búsqueda para un usuario de autor y titulo o individual
	private String GET_LIBROS_AUTOR = "SELECT * FROM libros where autor LIKE ? ;";
	private String GET_LIBROS_TITULO = "SELECT * FROM libros where titulo LIKE ? ;";
	private String GET_LIBROS_SEARCH = "SELECT * FROM libros where titulo LIKE ? and autor LIKE ?";
	
@GET
@Path("/search")
@Produces(MediaType.LIBROS_API_LIBRO_COLLECTION)
public LibrosCollection SearchLibros(@QueryParam("titulo") String titulo,
		@QueryParam("autor") String autor) {
	        LibrosCollection libros = new LibrosCollection();
		   
			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the databes", 
						Response.Status.SERVICE_UNAVAILABLE);
			}
			System.out.println("datos: " + autor);
			System.out.println("datos: " + titulo);
		    PreparedStatement stmt = null;
			
		    try {
		    	
		    	if (titulo != null && autor != null)
		        {
				stmt = conn.prepareStatement(GET_LIBROS_SEARCH);
				stmt.setString(1, titulo);  //(1, "%"+titulo+"%");
				stmt.setString(2, autor);
		        }
		    	else if(titulo != null && autor== null)
		    	{
		        stmt = conn.prepareStatement(GET_LIBROS_TITULO);
		        stmt.setString(1, titulo);   //(1, "%"+autor+"%");
		    	}

		    	else if(titulo == null && autor != null)
		    	{
		        stmt = conn.prepareStatement(GET_LIBROS_AUTOR);
		        stmt.setString(1, autor);   //(1, "%"+autor+"%");
		    	}
		    	


				ResultSet rs = stmt.executeQuery();
				System.out.println(stmt);
				while (rs.next()) {
					Libro libro = new Libro();
					
					libro.setId(rs.getInt("id"));
					libro.setTitulo(rs.getString("titulo"));
					libro.setAutor(rs.getString("autor"));
					libro.setLengua(rs.getString("lengua"));
					libro.setEdicion(rs.getString("edicion"));
					libro.setFecha_ed(rs.getDate("fecha_ed"));
					libro.setFecha_imp(rs.getDate("fecha_imp"));
					libro.setEditorial(rs.getString("editorial"));
	                libros.add(libro);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 
			return libros;
		}

/////búsqueda libro por id concreto
private String GET_LIBRO_BY_ID = "SELECT * FROM libros where id = ?";

@GET
@Path("/{id}")
@Produces(MediaType.LIBROS_API_LIBRO)
public Libro getLibro(@PathParam("id") int id) {
	
	Libro libro = new Libro();
    Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		throw new ServerErrorException("Could not connect to the database",
				Response.Status.SERVICE_UNAVAILABLE);
	}
 
	PreparedStatement stmt = null;
	
	try {
		stmt = conn.prepareStatement(GET_LIBRO_BY_ID);
		stmt.setInt(1, Integer.valueOf(id));
		System.out.println(stmt);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			libro.setId(rs.getInt("id"));
			libro.setTitulo(rs.getString("titulo"));
			libro.setAutor(rs.getString("autor"));
			libro.setLengua(rs.getString("lengua"));
			libro.setEdicion(rs.getString("edicion"));
			libro.setFecha_ed(rs.getDate("fecha_ed"));
			libro.setFecha_imp(rs.getDate("fecha_imp"));
			libro.setEditorial(rs.getString("editorial"));
		}
		else{
			throw new NotFoundException("There's no libro with id ="
					+ id);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return libro;
	
}



///////admin crea una ficha de autor
private String INSERT_AUTOR = "insert into autor (name) values (?)";

@POST
@Path("/autor")
@Consumes(MediaType.LIBROS_API_LIBRO)
@Produces(MediaType.LIBROS_API_LIBRO)

public Autor CreateAutor(Autor autor)
{
	
	if (!security.isUserInRole("admin"))
	throw new ForbiddenException("You are not allowed to create a autor");
	
	//Autor autor = new Autor();
	Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	PreparedStatement stmt = null;
	try {
		stmt = conn.prepareStatement(INSERT_AUTOR);
				
 
		stmt.setString(1, autor.getName());
		
		System.out.println(autor.getName());
		System.out.println(stmt);
		
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			autor.setIdautor(rs.getInt("id"));
			autor.setName(rs.getString("titulo"));
			
		 } else {
			throw new BadRequestException("You are not allowed to create a autor");
		}
           
	} catch (SQLException e) {
		throw new ServerErrorException(e.getMessage(),
				Response.Status.INTERNAL_SERVER_ERROR);
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
	}
 
	return autor;
}

//////admin elimina una ficha de autor

private String DELETE_AUTOR = "DELETE FROM Autor Where name LIKE ?";

@DELETE
@Path("/autor/{idautor}")
@Consumes(MediaType.LIBROS_API_LIBRO)
@Produces(MediaType.LIBROS_API_LIBRO)

public Autor DeleteAutor(Autor autor)
{
	if (!security.isUserInRole("admin"))
		throw new ForbiddenException("You are not allowed to delete a book");
	
	Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	PreparedStatement stmt = null;
	try {
		stmt = conn.prepareStatement(DELETE_AUTOR);
				
 
		stmt.setString(1, autor.getName());
		System.out.println(autor.getName());
		System.out.println(stmt);
		stmt.executeUpdate();
		int rows = stmt.executeUpdate();
		if (rows == 0)
		{
			throw new NotFoundException("No hay un autor con este nombre"
					+ autor);// Updating inexistent sting
		}
		else
		{
			System.out.println("Autor eliminado");
		}
	} catch (SQLException e) {
		throw new ServerErrorException(e.getMessage(),
				Response.Status.INTERNAL_SERVER_ERROR);
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
	}
 
	return autor;
}

////////admin actualiza ficha de autor

private String UPDATE_AUTOR = "UPDATE stings set name=ifnull(?, name)";

@PUT
@Path("/autor/{name}")
@Consumes(MediaType.LIBROS_API_LIBRO)
@Produces(MediaType.LIBROS_API_LIBRO)

public Autor UpdateAutor(Autor autor) {
	if (!security.isUserInRole("admin"))
		throw new ForbiddenException("You are not allowed to update a book");
	Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	PreparedStatement stmt = null;
	try {
		stmt = conn.prepareStatement(UPDATE_AUTOR);
		stmt.setString(1, autor.getName());
		
 
		int rows = stmt.executeUpdate();
		if (rows == 0)
		{
			throw new NotFoundException("No hay un autor con este nombre"
					+ autor);// Updating inexistent sting
		}
		else
		{
			System.out.println("Autor eliminado");
		}
 
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null)
				stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
	}
 
	return autor;
	}



}







	


