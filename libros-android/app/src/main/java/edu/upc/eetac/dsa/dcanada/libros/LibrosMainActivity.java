package edu.upc.eetac.dsa.dcanada.libros;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api.Libro;
import edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api.LibroAPI;
import edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api.LibroCollection;
import edu.upc.eetac.dsa.dcanada.libros.edu.upc.eetac.dsa.dcanada.libros.api.AppException;



public class LibrosMainActivity extends ListActivity {

    private final static String TAG = LibrosMainActivity.class.toString();
    private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus" };
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        SharedPreferences prefs = getSharedPreferences("beeter-profile",Context.MODE_PRIVATE);
        final String username = prefs.getString("username", null);
        final String password = prefs.getString("password", null);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("david", "david"
                        .toCharArray());
            }
        });
        (new FetchStingsTask()).execute();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }


   /* ArrayList<Libro> Listalibros;

    private void addLibro(LibroCollection libros){
        Listalibros.addAll(libros.getLibros());
        adapter.notifyDataSetChanged();
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.libros_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FetchStingsTask extends AsyncTask<Void, Void, LibroCollection> {
        private ProgressDialog pd;

        @Override
        protected LibroCollection doInBackground(Void... params) {
            LibroCollection libros = null;
            try {
                libros = LibroAPI.getInstance(LibrosMainActivity.this).getLibros();
            } catch (AppException e) {
                e.printStackTrace();
            }
            return libros;
        }

        @Override
        protected void onPostExecute(LibroCollection result) {
            ArrayList<Libro> stings = new ArrayList<Libro>(result.getLibros());
            for (Libro l : stings) {
                Log.d(TAG, l.getId() + "-" + l.getAutor());
            }
            if (pd != null) {
                pd.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(LibrosMainActivity.this);
            pd.setTitle("Searching...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }


    }

}
