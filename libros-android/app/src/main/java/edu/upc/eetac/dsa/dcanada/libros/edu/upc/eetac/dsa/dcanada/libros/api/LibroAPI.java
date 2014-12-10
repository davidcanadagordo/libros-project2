package edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * Created by DaviD on 09/12/2014.
 */
public class LibroAPI {
    private final static String TAG = LibroAPI.class.getName();
    private static LibroAPI instance = null;
    private URL url;

    private LibrosRootAPI rootAPI = null;

    private LibroAPI(Context context) throws IOException, AppException {
        super();

        AssetManager assetManager = context.getAssets();
        Properties config = new Properties();
        config.load(assetManager.open("config.properties"));
        String serverAddress = config.getProperty("server.address");
        String serverPort = config.getProperty("server.port");
        //String urlHome = config.getProperty("beeter.home");
        url = new URL("http://" + serverAddress + ":" + serverPort + "/libros-api");

        Log.d("LINKS", url.toString());
        getRootAPI();
    }
    public final static LibroAPI getInstance(Context context) throws AppException {
        if (instance == null)
            try {
                instance = new LibroAPI(context);
            } catch (IOException e) {
                throw new AppException(
                        "Can't load configuration file");
            }
        return instance;
    }

    private void getRootAPI() throws AppException {
        Log.d(TAG, "getRootAPI()");
        rootAPI = new LibrosRootAPI();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Beeter API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, rootAPI.getLinks());
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Beeter API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Beeter Root API");
        }


    }
    private void parseLinks(JSONArray jsonLinks, Map<String, Link> map)
            throws AppException, JSONException {
        for (int i = 0; i < jsonLinks.length(); i++) {
            Link link = null;
            try {
                link = SimpleLinkHeaderParser
                        .parseLink(jsonLinks.getString(i));
            } catch (Exception e) {
                throw new AppException(e.getMessage());
            }
            String rel = link.getParameters().get("rel");
            String rels[] = rel.split("\\s");
            for (String s : rels)
                map.put(s, link);
        }
    }
    public LibroCollection getLibros() throws AppException {
        System.out.println("Entramos");
        Log.d(TAG, "getLibros()");
        LibroCollection libros = new LibroCollection();

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(rootAPI.getLinks()
                    .get("stings").getTarget()).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
        } catch (IOException e) {
            throw new AppException(
                    "Can't connect to Libro API Web Service");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonLinks = jsonObject.getJSONArray("links");
            parseLinks(jsonLinks, libros.getLinks());

            libros.setNewestTimestamp(jsonObject.getLong("newestTimestamp"));
            libros.setOldestTimestamp(jsonObject.getLong("oldestTimestamp"));
            JSONArray jsonStings = jsonObject.getJSONArray("libros");
            for (int i = 0; i < jsonStings.length(); i++) {
                Libro libro = new Libro();
                JSONObject jsonSting = jsonStings.getJSONObject(i);
                libro.setId(jsonSting.getString("id"));
                libro.setTitulo(jsonSting.getString("titulo"));
                libro.setLengua(jsonSting.getString("lengua"));
                libro.setEdicion(jsonSting.getString("edicion"));
                libro.setFecha_ed(jsonSting.getLong("fecha_ed"));
                libro.setFecha_imp(jsonSting.getLong("fecha_imp"));
                libro.setEditorial(jsonSting.getString("editorial"));
                libro.setAutor(jsonSting.getString("autor"));
                System.out.println("obtenemos todos los campos");
                jsonLinks = jsonSting.getJSONArray("links");
                parseLinks(jsonLinks, libro.getLinks());
                libros.getLibros().add(libro);
            }
        } catch (IOException e) {
            throw new AppException(
                    "Can't get response from Libro API Web Service");
        } catch (JSONException e) {
            throw new AppException("Error parsing Libro Root API");
        }

        return libros;
    }
    //de aqui para arriba comun


}

