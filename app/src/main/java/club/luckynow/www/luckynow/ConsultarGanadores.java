package club.luckynow.www.luckynow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.loopj.android.http.HttpGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

import static java.security.AccessController.getContext;

/**
 * Created by vnk2 on 12/9/2017.
 */

public class ConsultarGanadores extends AsyncTask<String, Void, Boolean> {

    Context context;
    ProgressDialog pDialog;
    View view;

    ConsultarGanadores(Context context, View view){

        this.context = context;
        this.view = view;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            HttpGet httppost = new HttpGet("https://ksantacrwordpresscom.000webhostapp.com/ganadores.php");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);
            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                //JSONObject jsono = new JSONObject(data);

                //Usuario.ganadores_sorteo.clear();

                //String data = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Ganador tmpGanador = new Ganador(jsonArray.getJSONObject(i).getString("nombre"), jsonArray.getJSONObject(i).getString("imagen"), jsonArray.getJSONObject(i).getInt("puntos"));
                        Usuario.ganadores.add(tmpGanador);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.d("Mensaje", "No lo puedo convertir a json");
                }

                /*JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Ganador tmpGanador = new Ganador(jsonArray.getJSONObject(i).getString("nombre"), jsonArray.getJSONObject(i).getString("imagen"), 0);
                    Usuario.ganadores_sorteo.add(tmpGanador);
                    Log.d("Nombre: ", ""+ jsonArray.getJSONObject(i).getString("nombre"));
                    //Log.d("Foto: ", ""+ jsonArray.getJSONObject(i).getString("imagen"));

                }*/
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
        pDialog.setMessage("Cargando Ranking");
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
        cargarInfoRanking();
        //Log.d("OnpostExecute", "Hola");
    }

    public void cargarInfoRanking(){

        final ArrayList<Ganador> ganadores = Usuario.ganadores;

        TextView txtView = (TextView)view.findViewById(R.id.nombre_lugar_1);
        txtView.setText(ganadores.get(0).getNombre());

        TextView txtView1 = (TextView)view.findViewById(R.id.nombre_lugar_2);
        txtView1.setText(ganadores.get(1).getNombre());

        TextView txtView2 = (TextView)view.findViewById(R.id.nombre_lugar_3);
        txtView2.setText(ganadores.get(2).getNombre());

        TextView txtView3 = (TextView)view.findViewById(R.id.nombre_lugar_4);
        txtView3.setText(ganadores.get(3).getNombre());

        TextView txtView4 = (TextView)view.findViewById(R.id.nombre_lugar_5);
        txtView4.setText(ganadores.get(4).getNombre());

        TextView txtView5 = (TextView)view.findViewById(R.id.nombre_lugar_6);
        txtView5.setText(ganadores.get(5).getNombre());

        TextView txtView6 = (TextView)view.findViewById(R.id.nombre_lugar_7);
        txtView6.setText(ganadores.get(6).getNombre());

        ImageView imageView = (ImageView) view.findViewById(R.id.foto_lugar_1);
        //imageView.setMaxWidth(100);
        //imageView.setMaxHeight(100);



        ImageView imageView2 = (ImageView) view.findViewById(R.id.foto_lugar_2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.foto_lugar_3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.foto_lugar_4);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.foto_lugar_5);
        ImageView imageView6 = (ImageView) view.findViewById(R.id.foto_lugar_6);
        ImageView imageView7 = (ImageView) view.findViewById(R.id.foto_lugar_7);


        //Log.d("BOol", ""+ (ganadores.get(0).getFoto()=="foto_perfil_usuario.png"));


        if(!ganadores.get(0).getFoto().contains("foto_perfil_usuario.png")){
            //Log.d("Hola", ""+ganadores.get(0).getFoto());
            Uri myUri = Uri.parse(ganadores.get(0).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView);

            imageView.getLayoutParams().height = 200;
            imageView.getLayoutParams().width = 200;

        }
        if(!ganadores.get(1).getFoto().contains("foto_perfil_usuario.png")){
            //Log.d("Hola2", ""+ganadores.get(1).getFoto());
            Uri myUri = Uri.parse(ganadores.get(1).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView2);
        }
        if(!ganadores.get(2).getFoto().contains("foto_perfil_usuario.png")){
            //Log.d("Holaasd", ""+ganadores.get(2).getFoto());
            Uri myUri = Uri.parse(ganadores.get(2).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView3);
        }
        if(!ganadores.get(3).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(3).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView4);
        }
        if(!ganadores.get(4).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(4).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView5);
        }
        if(!ganadores.get(5).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(5).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView6);
        }
        if(!ganadores.get(6).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(6).getFoto());
            Glide.with(context).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView7);
        }

        TextView txtPuntos = (TextView)view.findViewById(R.id.puntos_lugar_1);
        txtPuntos.setText(""+ganadores.get(0).getPuntos()+ " puntos");
        TextView txtPuntos2 = (TextView)view.findViewById(R.id.puntos_lugar_2);
        txtPuntos2.setText(""+ganadores.get(1).getPuntos()+ " puntos");
        TextView txtPuntos3 = (TextView)view.findViewById(R.id.puntos_lugar_3);
        txtPuntos3.setText(""+ganadores.get(2).getPuntos()+ " puntos");
        TextView txtPuntos4 = (TextView)view.findViewById(R.id.puntos_lugar_4);
        txtPuntos4.setText(""+ganadores.get(3).getPuntos()+ " puntos");
        TextView txtPuntos5 = (TextView)view.findViewById(R.id.puntos_lugar_5);
        txtPuntos5.setText(""+ganadores.get(4).getPuntos()+ " puntos");
        TextView txtPuntos6 = (TextView)view.findViewById(R.id.puntos_lugar_6);
        txtPuntos6.setText(""+ganadores.get(5).getPuntos()+ " puntos");
        TextView txtPuntos7 = (TextView)view.findViewById(R.id.puntos_lugar_7);
        txtPuntos7.setText(""+ganadores.get(6).getPuntos()+ " puntos");




    }
}
