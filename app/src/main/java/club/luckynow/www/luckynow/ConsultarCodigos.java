package club.luckynow.www.luckynow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

public class ConsultarCodigos extends AsyncTask<String, Void, Boolean> {
    Context context;
    ProgressDialog pDialog;
    String codigo;
    int cantidad = 0;
    int estado = 1;

    public ConsultarCodigos(Context context, String codigo) {
        this.context = context;
        this.codigo = codigo;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            HttpGet httppost = new HttpGet("https://ksantacrwordpresscom.000webhostapp.com/verificarCodigo.php?codigo="+codigo);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);
            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jsono = new JSONObject(data);
                cantidad = jsono.getInt("valor");
                estado = jsono.getInt("estado");
                //Log.d("ESTADO------------------------------>", ""+estado);
                //Log.d("---------------------->", ""+jsono.toString());
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
        pDialog.setMessage("Verificando Código");
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

        String mensaje = "Código incorrecto :(";

        if(cantidad!=0 && estado == 0){
            Usuario.monedas+=cantidad;
            Usuario.puntos+=50;
            HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
            mensaje = "Se acreditaron "+ cantidad +" luckymonedas";
            Usuario.actualizarPuntos();
        }
        Toast.makeText(context,mensaje, Toast.LENGTH_LONG).show();


        //Log.d("OnpostExecute", "Hola");

    }
}
