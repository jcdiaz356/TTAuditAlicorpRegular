package com.dataservicios.ttauditalicorpregular.AditoriaAlicorp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.dataservicios.ttauditalicorpregular.MainActivity;
import com.dataservicios.ttauditalicorpregular.Model.Audit;
import com.dataservicios.ttauditalicorpregular.Model.PollDetail;
import com.dataservicios.ttauditalicorpregular.R;
import com.dataservicios.ttauditalicorpregular.SQLite.DatabaseHelper;
import com.dataservicios.ttauditalicorpregular.util.AuditUtil;
import com.dataservicios.ttauditalicorpregular.util.GPSTracker;
import com.dataservicios.ttauditalicorpregular.util.GlobalConstant;
import com.dataservicios.ttauditalicorpregular.util.JSONParserX;
import com.dataservicios.ttauditalicorpregular.util.SessionManager;

/**
 * Created by Jaime on 16/08/2016.
 */
public class ClientePerfecto extends Activity {
    private Activity MyActivity = this ;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private SessionManager session;

    private Switch swSiNo ;
    private Button bt_guardar;
    //private EditText etComentario;
    private TextView tv_Pregunta;
    //private LinearLayout lyNoClientPerfet ;

    private String tipo,cadenaruc, fechaRuta, comentario="", type, region;

    private Integer user_id, company_id,store_id,rout_id,audit_id, product_id, poll_id, poll_id2;

    int  is_sino=0 ;


    private DatabaseHelper db;

    private ProgressDialog pDialog;

    private PollDetail pollDetail;
    private Audit mAudit;
    private GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_perfecto);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Cliente Perfecto");

        swSiNo = (Switch) findViewById(R.id.swSiNo);

        //lyNoClientPerfet = (LinearLayout) findViewById(R.id.lyNoClientPerfet);





        tv_Pregunta = (TextView) findViewById(R.id.tvPregunta);
        bt_guardar = (Button) findViewById(R.id.btGuardar);

        //etComentario = (EditText) findViewById(R.id.etComentario);
        //etComentario = (EditText) findViewById(R.id.etComentario);





        Bundle bundle = getIntent().getExtras();
        company_id = GlobalConstant.company_id;
        store_id = bundle.getInt("store_id");
        rout_id = bundle.getInt("rout_id");
        region = bundle.getString("region");
        fechaRuta = bundle.getString("fechaRuta");
        audit_id = bundle.getInt("audit_id");
        type = bundle.getString("type");

        //audit_id = 4; //Inicio de auditoría Alicorp
//        poll_id = 496;
//        poll_id2 = 497;
        poll_id = GlobalConstant.poll_id[7];
        poll_id2 = GlobalConstant.poll_id[8];

        pDialog = new ProgressDialog(MyActivity);
        pDialog.setMessage(getString(R.string.text_loading));
        pDialog.setCancelable(false);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // id
        user_id = Integer.valueOf(user.get(SessionManager.KEY_ID_USER)) ;



        swSiNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    is_sino = 1;
//                    lyNoClientPerfet.setEnabled(false);
//                    lyNoClientPerfet.setVisibility(View.VISIBLE);
                   // etComentario.setText("");
                } else {
                    is_sino = 0;
//                    lyNoClientPerfet.setEnabled(true);
//                    lyNoClientPerfet.setVisibility(View.INVISIBLE);
                  //  etComentario.setText("");
                }
            }
        });




        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!swSiNo.isChecked()){
//

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity);
                builder.setTitle("Guardar Encuesta");
                builder.setMessage("Está seguro de guardar todas las encuestas: ");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // comentario = String.valueOf(etComentario.getText()) ;

                        pollDetail = new PollDetail();
                        pollDetail.setPoll_id(poll_id);
                        pollDetail.setStore_id(store_id);
                        pollDetail.setSino(1);
                        pollDetail.setOptions(0);
                        pollDetail.setLimits(0);
                        pollDetail.setMedia(0);
                        pollDetail.setComment(0);
                        pollDetail.setResult(is_sino);
                        pollDetail.setLimite("0");
                        pollDetail.setComentario("");
                        pollDetail.setAuditor(user_id);
                        pollDetail.setProduct_id(0);
                        pollDetail.setCategory_product_id(0);
                        pollDetail.setPublicity_id(0);
                        pollDetail.setCompany_id(GlobalConstant.company_id);
                        pollDetail.setCommentOptions(0);
                        pollDetail.setSelectdOptions("");
                        pollDetail.setSelectedOtionsComment("");
                        pollDetail.setPriority("0");

                        new loadPoll().execute();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                builder.setCancelable(false);

            }
        });

    }



    class loadPoll extends AsyncTask<Void, Integer , Boolean> {
        /**
         * Antes de comenzar en el hilo determinado, Mostrar progresión
         * */
        boolean failure = false;
        @Override
        protected void onPreExecute() {
            //tvCargando.setText("Cargando Product...");
            pDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO Auto-generated method stub

            if(is_sino==1) {

                if(!AuditUtil.insertPollDetail(pollDetail)) return false;

//                pollDetail.setPoll_id(poll_id2);
//                pollDetail.setSino(0);
//                pollDetail.setComment(1);
//                pollDetail.setResult(0);
//                pollDetail.setLimite("0");
//                pollDetail.setComentario(comentario);
//                if(!AuditUtil.insertPollDetail(pollDetail)) return false;


            } else if(is_sino==0){
                if(!AuditUtil.insertPollDetail(pollDetail)) return false;
            }

            String time_close = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date());
            mAudit = new Audit();
            mAudit.setCompany_id(GlobalConstant.company_id);
            mAudit.setStore_id(store_id);
            mAudit.setId(audit_id);
            mAudit.setRoute_id(rout_id);
            mAudit.setUser_id(user_id);
            mAudit.setLatitude_close(String.valueOf(""));
            mAudit.setLongitude_close(String.valueOf(""));
            mAudit.setLatitude_open(String.valueOf(GlobalConstant.latitude_open));
            mAudit.setLongitude_open(String.valueOf(GlobalConstant.longitude_open));
            mAudit.setTime_open(GlobalConstant.inicio);
            mAudit.setTime_close(time_close);

            if(!AuditUtil.closeAuditRoadStore(mAudit)) return false;

            return true;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(Boolean result) {
            // dismiss the dialog once product deleted

            if (result){

                    finish();

            } else {
                Toast.makeText(MyActivity , "No se pudo guardar la información intentelo nuevamente", Toast.LENGTH_LONG).show();
            }
            hidepDialog();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                Intent a = new Intent(this,PanelAdmin.class);
//                //a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(a);
//                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }



    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
