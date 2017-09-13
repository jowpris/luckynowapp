package club.luckynow.www.luckynow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by vnk2 on 12/9/2017.
 */

public class EnviarCorreo extends AsyncTask<String, Void, Boolean> {

    Context context;
    ProgressDialog pDialog;
    public EnviarCorreo(Context context) {
        this.context = context;
    }


    @Override
    protected Boolean doInBackground(String... params) {

        try {
            HttpGet httppost = new HttpGet("https://ksantacrwordpresscom.000webhostapp.com/enviarCorreo.php?mail="+Usuario.correo);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);
            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                //JSONObject jsono = new JSONObject(data);
                Log.d("correo------->", ""+data);
                //cantidad = jsono.getInt("valor");
                //estado = jsono.getInt("estado");
                //Log.d("ESTADO------------------------------>", ""+estado);
                //Log.d("---------------------->", ""+jsono.toString());
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
        pDialog.setMessage("Enviando correo...");
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

        Toast.makeText(context, "El premio ha llegado a tu correo, revísalo", Toast.LENGTH_LONG).show();

    }

}
