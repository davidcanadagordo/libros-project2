package edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api;

/**
 * Created by DaviD on 09/12/2014.
 */
public interface MediaType {
    public final static String LIBROS_API_USER = "application/vnd.libros.api.user+json";
    public final static String LIBROS_API_USER_COLLECTION = "application/vnd.libros.api.user.collection+json";
    public final static String LIBROS_API_RESENA = "application/vnd.libros.api.resena+json";
    public final static String LIBROS_API_RESENA_COLLECTION = "application/vnd.libros.api.resena.collection+json";
    public final static String LIBROS_API_LIBRO = "application/vnd.libros.api.libro+json";
    public final static String LIBROS_API_LIBRO_COLLECTION = "application/vnd.libros.api.libros.collection+json";
    public final static String LIBROS_API_ERROR = "application/vnd.libros.error+json";
}
