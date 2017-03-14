package com.dataservicios.ttauditalicorpregular;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataservicios.ttauditalicorpregular.app.AppController;
import com.dataservicios.ttauditalicorpregular.util.DirectionsJSONParser;
import com.dataservicios.ttauditalicorpregular.util.GlobalConstant;


/**
 * Created by usuario on 26/01/2015.
 */
public class MapaRuta extends FragmentActivity {
    GoogleMap map;
    ArrayList<LatLng> markerPoints;

    ArrayList<Marker> myMarkersPointsAlicorp = new ArrayList<Marker>();

    MarkerOptions options;
    private int IdRuta ;
    private ProgressDialog pDialog;
    private JSONObject params;
    private double lat ;
    private double lon ;
    //private Button  btn_MarkeAlicorp ;

    Activity MyActivity= (Activity) this;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_rutas);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Mapa de Rutas");
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);



        Bundle bundle = getIntent().getExtras();
        IdRuta= bundle.getInt("id");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando...");
        pDialog.setCancelable(false);



        //Recogiendo paramentro del anterior Activity
        //Bundle bundle = savedInstanceState.getArguments();
        params = new JSONObject();
        try {
            params.put("id", IdRuta);
            //params.put("id_pdv",idPDV);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Initializing
        markerPoints = new ArrayList<LatLng>();
        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Getting reference to Button
        //Button btnDraw = (Button) findViewById(R.id.btn_draw);
        // Obtener mapas para el SupportMapFragment
        map = fm.getMap();
        // Habilitar Botón MyLocation en el Mapa
        map.setMyLocationEnabled(true);

        showpDialog();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST , GlobalConstant.dominio + "/JsonRoadsMap" ,params,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("DATAAAA", response.toString());
                        //adapter.notifyDataSetChanged();
                        try {
                            //String agente = response.getString("agentes");
                            int success =  response.getInt("success");
                            if (success == 1) {
//
                                JSONArray ObjJson;
                                ObjJson = response.getJSONArray("storeMaps");
                                // looping through All Products
                                if(ObjJson.length() > 0) {
                                    for (int i = 0; i < ObjJson.length(); i++) {
                                        try {
                                            JSONObject obj = ObjJson.getJSONObject(i);


                                            if(obj.getInt("company_id") == GlobalConstant.company_id){
                                                myMarkersPointsAlicorp.add(map.addMarker(new MarkerOptions()
                                                        .position(new LatLng(Double.valueOf(obj.getString("latitude")), Double.valueOf(obj.getString("longitude"))))
                                                        .title("Shop")
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_alicorp))
                                                        .title(obj.getString("fullname"))));
                                            }




//
                                            lat = Double.valueOf(obj.getString("latitude")) ;
                                            lon = Double.valueOf(obj.getString("longitude"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(lat, lon)).zoom(15).build();
                                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        hidepDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //VolleyLog.d(TAG, "Error: " + error.getMessage());
                        hidepDialog();
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(jsObjRequest);


    }



    /**
     * Un método para descargar datos JSON de url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creación de una conexión HTTP para comunicarse con url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Conexión a url
            urlConnection.connect();

            // Lectura de datos de url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }



    /**
     * A class to parse the Google Places in JSON format
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //this.finish();
        //Intent a = new Intent(this, PuntosVenta.class);
        //a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        //startActivity(a);
    }
///METODOS------

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}