package club.luckynow.www.luckynow;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
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

public class ConsultarMilisegundos extends AsyncTask<String, Void, Boolean> {

    Context context;
    ProgressDialog pDialog;
    TextView textView;
    final String FORMAT = "%02d:%02d:%02d";
    ConsultarMilisegundos(Context context, TextView textView){
        this.context = context;
        this.textView = textView;

    }


    @Override
    protected Boolean doInBackground(String... strings) {

        //Log.d("doInBackground", "Hola");

        try {
            HttpGet httppost = new HttpGet("https://ksantacrwordpresscom.000webhostapp.com/horaMilisegundos.php");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);
            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jsono = new JSONObject(data);
                Usuario.milisegundos = jsono.getLong("milisegundos");
                //Log.d("JSON:", ""+jsono);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

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

        new CountDownTimer(Usuario.milisegundos, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                textView.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                textView.setText("Se está preparando el sorteo");

            }
        }.start();


    }
}