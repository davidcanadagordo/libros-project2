package edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DaviD on 09/12/2014.
 */
public class Libro {


    private String id;
    private String titulo;
    private String lengua;
    private String edicion;
    private long fecha_ed;
    private long fecha_imp;
    private String editorial;
    private String autor;
    private List<Link> link;
    private Map<String, Link> links = new HashMap<String, Link>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLengua() {
        return lengua;
    }

    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public long getFecha_ed() {
        return fecha_ed;
    }

    public void setFecha_ed(long fecha_ed) {
        this.fecha_ed = fecha_ed;
    }

    public long getFecha_imp() {
        return fecha_imp;
    }

    public void setFecha_imp(long fecha_imp) {
        this.fecha_imp = fecha_imp;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }


    //private List<Resena> resena= new ArrayList<Resena>();
    //private List<Autor> autores = new ArrayList<Autor>();

}
