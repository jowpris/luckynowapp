package club.luckynow.www.luckynow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements GoogleListener {

    GoogleHelper mGoogle;
    Button btnGoogle;
    Intent homeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoogle = (Button) findViewById(R.id.btnGoogleLogin);

        btnGoogle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mGoogle.performSignIn(MainActivity.this);
            }
        });
        mGoogle = new GoogleHelper(this, this, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(!isOnline()){
            AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.no_internet_title);
            builder.setMessage(R.string.porfavor_verifique_su_conexion_a_internet);
            builder.setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }else{
            Usuario.ganadores_sorteo.clear();
            Toast.makeText(getApplicationContext(), "Cargando datos ...", Toast.LENGTH_SHORT).show();

        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        GoogleSignInAccount acct = result.getSignInAccount();
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();
        String personFamilyName = acct.getFamilyName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();

        Usuario.id = personId;
        Usuario.imagen = personPhoto;

        //Log.d("Imagen 1 : ", ""+Usuario.imagen);
        Usuario.apodo = personGivenName;
        Usuario.apellido = personFamilyName;
        Usuario.nombre = personName;
        Usuario.correo = personEmail;
        if (Usuario.imagen == null) {
            Usuario.imagen = Uri.parse("foto_perfil_usuario.png");
        }

        AsyncHttpClient client = new AsyncHttpClient();
//        Consultar ganadores
        /*client.get("https://ksantacrwordpresscom.000webhostapp.com/ganadores.php", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String data = new String(responseBody);
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
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });*/

        //AsyncHttpClient
        client = new AsyncHttpClient();
//        Consultar datos del usuario
        client.get("https://ksantacrwordpresscom.000webhostapp.com/prueba.php?user_id=" + Usuario.id + "&nombre=" + Usuario.nombre + "&correo=" + Usuario.correo + "&imagen=" + Usuario.imagen, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                //Log.d("OnStart: ", "OK");
                Toast.makeText(getApplicationContext(), "Conectando...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String data = new String(response);
                Log.d("onSuccess: -------------------->", "" + data);
                //JSONObject text = new JSONObject();
                try {


                    JSONArray jsonArray = new JSONArray(data);

                    JSONObject jsonObject =jsonArray.getJSONObject(0);
                    Usuario.cantidadPremios = jsonObject.getInt("premios");
                    Usuario.puntos = jsonObject.getInt("puntos");
                    Usuario.monedas = jsonObject.getInt("monedas");
                    //HomeActivity.textViewCantidadMonedas.setText("" + Usuario.monedas);

                    //Log.d("JSON", "<<<<<<<"+jsonObject.toString());
                    /*JSONArray jsonArray = new JSONArray(data);
                    String texto = "";
                    Log.d("JSON ARRAY", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        texto = jsonArray.getJSONObject(i).getString("monedas");
                        Usuario.cantidadPremios = new Integer(jsonArray.getJSONObject(i).getString("premios"));
                        Usuario.puntos = new Integer(jsonArray.getJSONObject(i).getString("puntos"));
                        Usuario.monedas = new Integer(texto);
                        HomeActivity.textViewCantidadMonedas.setText("" + Usuario.monedas);
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                    //Usuario.monedas = 350;
                    //Usuario.cantidadPremios = 2;
                    //Log.d("Mensaje", "No lo puedo convertir a json<---- al prueba.php");
                }


                homeActivity = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeActivity);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                //Log.d("onFailure: ", "OK");


                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.no_internet_title);
                builder.setMessage(R.string.porfavor_verifique_su_conexion_a_internet);
                builder.setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                //Log.d("onRetry: ", "OK");
            }
        });
//        Consultar estado del participante
        client.get("https://ksantacrwordpresscom.000webhostapp.com/consultarParticipanteLoteria.php?user_id=" + Usuario.id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                //Log.d("OnStart: ", "OK");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String data = new String(response);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    Usuario.participando = jsonObject.getBoolean("jugando");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                //Log.d("onRetry: ", "OK");
            }
        });

        client.get("https://ksantacrwordpresscom.000webhostapp.com/consultarNumeroParticipantesLoteria.php", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                //Log.d("OnStart: ", "OK");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String data = new String(response);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    Usuario.cantidadParticipantes = jsonObject.getInt("cantidad");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                //Log.d("onRetry: ", "OK");
            }
        });

        client.get("https://ksantacrwordpresscom.000webhostapp.com/consultarGanadoresLoteria.php", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                //Log.d("OnStart: ", "OK");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String data = new String(response);
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Ganador tmpGanador = new Ganador(jsonArray.getJSONObject(i).getString("nombre"), jsonArray.getJSONObject(i).getString("imagen"), 0);
                        Usuario.ganadores_sorteo.add(tmpGanador);
                        //Log.d("Nombre: ", ""+ jsonArray.getJSONObject(i).getString("nombre"));
                        //Log.d("Foto: ", ""+ jsonArray.getJSONObject(i).getString("imagen"));

                    }
                    //JSONObject jsonObject = new JSONObject(data);
                    //Usuario.participando = jsonObject.getBoolean("jugando");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                //Log.d("onRetry: ", "OK");
            }
        });


        //Toast.makeText(this, "on Google auth sign in: ", Toast.LENGTH_LONG).show();
        mGoogle.onActivityResult(requestCode, resultCode, data);
    }


    }

    @Override
    public void onGoogleAuthSignIn(String s, String s1) {

        //Toast.makeText(this, "on Google auth sign in: "+s, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "Bienvenido a Luckynow", Toast.LENGTH_LONG).show();
        //Log.d("String1:" , ""+ s);
        //Log.d("String2:" , ""+ s1);
        //Toast.makeText(this, "on Google auth sign in: "+s1, Toast.LENGTH_LONG).show();
       /* homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);*/

    }
    @Override
    public void onGoogleAuthSignInFailed(String s) {
        Toast.makeText(this, "No se puede iniciar con Google desde tu dispositivo", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "onGoogleAuthSignInFailed: "+s.toString(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void onGoogleAuthSignOut() {
        //Toast.makeText(this, "logout ", Toast.LENGTH_LONG).show();
    }
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            //Toast.makeText(MainActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
