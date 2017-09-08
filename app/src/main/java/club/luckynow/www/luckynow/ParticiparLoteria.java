package club.luckynow.www.luckynow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by vnk2 on 7/9/2017.
 */

public class ParticiparLoteria extends AsyncTask<String, Void, Boolean> {

    Context context;
    ProgressDialog pDialog;
    Button btn_participar;
    TextView textViewTicketsComprados;

    ParticiparLoteria(Context context, Button btn_participar, TextView textViewTicketsComprados){
        this.context = context;
        this.btn_participar = btn_participar;
        this.textViewTicketsComprados = textViewTicketsComprados;

    }


    @Override
    protected Boolean doInBackground(String... strings) {

        //Log.d("doInBackground", "Hola");

        try {
            HttpGet httppost = new HttpGet("https://ksantacrwordpresscom.000webhostapp.com/participarLoteria.php?user_id="+Usuario.id);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);
            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Log.d("onPreExecute", "Hola");
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Cargando");
        pDialog.setCancelable(true);
        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //errorMesaje = "Process cancelled";
                cancel(true);
            }
        });
        pDialog.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        pDialog.dismiss();

        //Log.d("OnpostExecute", "Hola");
        btn_participar.setEnabled(false);
        btn_participar.setBackgroundResource(R.drawable.bg_btn_lanzado);
        btn_participar.setPadding(0,0,0,0);
        btn_participar.setText("Ya estas participando");
        btn_participar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Usuario.participando = true;
        textViewTicketsComprados.setText(""+ (Usuario.cantidadParticipantes+1));
    }
}
