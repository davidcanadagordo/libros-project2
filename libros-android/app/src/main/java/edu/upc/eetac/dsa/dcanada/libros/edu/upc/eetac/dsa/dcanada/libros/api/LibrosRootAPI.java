package edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DaviD on 09/12/2014.
 */
public class LibrosRootAPI {
    private Map<String, Link> links;

    public LibrosRootAPI() {
        links = new HashMap<String, Link>();
    }

    public Map<String, Link> getLinks() {
        return links;
    }


}
