package club.luckynow.www.luckynow;

import android.net.Uri;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vnk2 on 26/8/2017.
 */

public class Usuario {

    public static String id = "";
    public static String apodo = "";
    public static String nombre = "";
    public static String apellido = "";
    public static int monedas = 0;
    public static String correo = "";
    public static Uri imagen;
    public static int cantidadPremios = 0;
    public static int puntos = 0;
    public static long milisegundos = 0;
    public static boolean participando = false;
    public static int cantidadParticipantes = 0;
    public static ArrayList<Ganador> ganadores_sorteo = new ArrayList<>();

    public static ArrayList<Ganador> ganadores = new ArrayList<>();

    //Funcion para guardar los premios del usuario
    public static void guardarPremios(){

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://ksantacrwordpresscom.000webhostapp.com/guardarPremios.php?user_id="+id+"&premios="+cantidadPremios, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
    //Actualizar los puntos de un usuario
    public static void actualizarPuntos(){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://ksantacrwordpresscom.000webhostapp.com/guardarMonedas.php?user_id="+id+"&monedas="+monedas+"&puntos="+puntos, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }
}
