package edu.upc.edu.eetac.davidcanadagordo.libros_api;

import javax.sql.DataSource;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Link;

import edu.upc.edu.eetac.davidcanadagordo.libros_api.model.Libro;
import edu.upc.edu.eetac.davidcanadagordo.libros_api.model.LibrosCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/libros")
public class LibroResource {
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();
	
	private String GET_LIBROS_QUERY = "SELECT * FROM libros";
	@GET
	@Produces(MediaType.LIBROS_API_LIBRO_COLLECTION)
	public LibrosCollection getLibros() {
	       LibrosCollection libros = new LibrosCollection();
	 
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_LIBROS_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getInt("idlibro"));
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setEdicion(rs.getString("edicion"));
				libro.setEditorial(rs.getString("editorial"));
				libro.setFecha_ed(rs.getDate("fecha_ed"));
				libro.setFecha_imp(rs.getDate("fecha_imp"));
				libro.setLengua(rs.getString("lengua"));
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

	private String GET_LIBROS_AUTOR = "SELECT * FROM libros where autor LIKE ? ";
	private String GET_LIBROS_TITULO = "SELECT * FROM libros where titulo LIKE ? ";
	//private String GET_LIBROS_SEARCH = "SELECT * FROM libros where titulo LIKE ? and autor LIKE ?";
	
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
				e.printStackTrace();
			}
			
		    PreparedStatement stmt = null;
			
		    try {
		    	
		    	if (titulo != null)
		        {
				stmt = conn.prepareStatement(GET_LIBROS_TITULO);
				stmt.setString(1, "%"+titulo+"%");
		        }
		    	else if(autor != null)
		    	{
		        stmt = conn.prepareStatement(GET_LIBROS_AUTOR);
		        stmt.setString(1, "%"+autor+"%");
		    	}

		    	else
		    	{
		    		throw new BadRequestException("El autor o el título no pueden estar vacíos");
		    	}
		    	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Libro libro = new Libro();
					libro.setId(rs.getInt("idlibro"));
					libro.setId(rs.getInt("id"));
					libro.setTitulo(rs.getString("titulo"));
					libro.setAutor(rs.getString("autor"));
					libro.setEdicion(rs.getString("edicion"));
					libro.setEditorial(rs.getString("editorial"));
					libro.setFecha_ed(rs.getDate("fecha_ed"));
					libro.setFecha_imp(rs.getDate("fecha_imp"));
					libro.setLengua(rs.getString("lengua"));
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

private String GET_LIBRO_BY_ID = "SELECT * FROM Libro where id = ?";

@GET
@Path("/{id}")
@Produces(MediaType.LIBROS_API_LIBRO)
public Libro getLibro(@PathParam("id") int id) {
	Libro libro = new Libro();
 
	Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 
	PreparedStatement stmt = null;
	System.out.println("conectados a la BD");
	try {
		stmt = conn.prepareStatement(GET_LIBRO_BY_ID);
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

	}


