package club.luckynow.www.luckynow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class HomeActivity extends AppCompatActivity {

    ImageView imageViewFotoPerfil;
    static TextView textViewNombreUsuario,textViewCantidadMonedas;
    RelativeLayout layout_editarPerfil;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    Context vistaPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vistaPrincipal = getApplicationContext();

        imageViewFotoPerfil = (ImageView)findViewById(R.id.foto_perfil_);
        //textViewNombreUsuario = (TextView) findViewById(R.id.textViewNombreUsuario);
        //textViewNombreUsuario.setText(Usuario.nombre);
        textViewCantidadMonedas = (TextView) findViewById(R.id.txtCantidadMonedas);
        textViewCantidadMonedas.setText(""+Usuario.monedas);

        ImageView foto_perfil_ = (ImageView) findViewById(R.id.foto_perfil_);
        foto_perfil_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, PerfilActivity.class);
                startActivity(i);
                //Toast.makeText(getApplicationContext(), "Editar perfil", Toast.LENGTH_LONG).show();
            }
        });
        //layout_editarPerfil = (RelativeLayout)findViewById(R.id.layout_editarPerfil);
        //layout_editarPerfil.setOnClickListener( new View.OnClickListener(){
         //   @Override
         //   public void onClick(View v) {

                //Toast.makeText(v.getContext(), "Muy pronto", Toast.LENGTH_LONG).show();
                //RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout_editarPerfil);
                //RelativeLayout.LayoutParams a = new RelativeLayout.LayoutParams(rl.getLayoutParams());
                //double ancho = rl.getLayoutParams().height * 2.5;
                //a.height = (int)ancho;
                //rl.setLayoutParams(a);
                //RelativeLayout.LayoutParams params = rl.getLayoutParams();
                // Changes the height and width to the specified *pixels*
                //params.height = 100;
                //params.width = 100;
                //rl.setLayoutParams(new RelativeLayout.LayoutParams(rl.getLayoutParams()));
                //rl.setMinimumHeight(300);
                //rl.setVisibility(View.INVISIBLE);
                //rl.bringToFront();

                // Gets linearlayout
                ///LinearLayout layout = findViewById(R.id.numberPadLayout);
// Gets the layout params that will allow you to resize the layout
                ///RelativeLayout.LayoutParams params = rl.getLayoutParams().height;
// Changes the height and width to the specified *pixels*
//                params.height = 100;
//                params.width = 100;
//                layout.setLayoutParams(params);
                //LinearLayout ra = (LinearLayout)findViewById(R.id.fragment_editar_perfil);
                //Log.d("linear :", ""+ra);
                //Log.d("Alto: ", ""+ra.getLayoutParams().height);
                //Log.d("Ancho: ", ""+ra.getLayoutParams().width);

          //      editarPerfil();
          //  }
        //});
        // Si el usuario permite compartir la imagen, la pone en la vista.
        if(Usuario.imagen.toString() != "foto_perfil_usuario.png"){
            Glide.with(this).load(Usuario.imagen).apply(RequestOptions.circleCropTransform()).into(imageViewFotoPerfil);
            //Glide.with(this).load(Usuario.imagen).into(imageViewFotoPerfil);
            //Log.d("Soy la imagen:", ""+Usuario.imagen);
        }

        //Log.d("Soy la imagen:", ""+Usuario.imagen);


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        //Log.d("Menu: ", ""+bottomNavigationView.getMenu().getItem(0).toString());
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //pantallaRecargar();
        pantallaJuegos();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();

                //Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_LONG).show();

                switch (item.getItemId()) {
                    case R.id.inicio:
                        pantallaJuegos();

                        return true;
                    case R.id.recompensas:pantallaRecompensas();

                        //juegoTragamonedas();
                        //transaction.replace(R.id.layout_contenedor, new HomeFragment()).commit();
                        //layout_editarPerfil.bringToFront();
                        return true;
                    case R.id.top5: pantallaRanking();

                        //transaction.replace(R.id.layout_contenedor, new AyudaFragment()).commit();
                        //layout_editarPerfil.bringToFront();
                        return true;
                    case R.id.recargar:
                        pantallaIntercambio();
                        //pantallaRecargar();
                        //Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
                        //startActivity(mainActivity);

                        /*AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
                        dialog.setCancelable(false);
                        dialog.setTitle("Cerrar sesión");
                        dialog.setMessage("¿Seguro quieres cerrar sesión?" );
                        dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Action for "Delete".
                                Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
                                startActivity(mainActivity);
                            }
                        })
                                .setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Action for "Cancel".
                                    }
                                });

                        final AlertDialog alert = dialog.create();
                        alert.show();*/

                        return true;
                    case R.id.ayuda: pantallaAyuda(); return true;
                }
                return false;
            }

        });
    }

    public void pantallaRecargar(){
        transaction.replace(R.id.layout_contenedor, new RecargarFragment()).commit();
        //layout_editarPerfil.bringToFront();
        //Log.d("Hola", ""+layout_editarPerfil);
    }
    public void pantallaJuegos(){
        transaction.replace(R.id.layout_contenedor, new HomeFragment()).commit();
        //layout_editarPerfil.bringToFront();
    }
    public void pantallaAyuda(){
        transaction.replace(R.id.layout_contenedor, new AyudaFragment()).commit();
        //layout_editarPerfil.bringToFront();
    }
    public void pantallaSalir(){

        transaction.replace(R.id.layout_contenedor, new RecargarFragment()).commit();
        //layout_editarPerfil.bringToFront();
    }
    public void editarPerfil(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layout_contenedor, new EditarPerfilFragment()).commit();
        //layout_editarPerfil.bringToFront();
    }
    public void pantallaRanking(){
        transaction.replace(R.id.layout_contenedor, new RankingFragment()).commit();
    }
    public void pantallaRecompensas(){
        transaction.replace(R.id.layout_contenedor, new PremiosFragment()).commit();
    }
    public void pantallaIntercambio(){
        transaction.replace(R.id.layout_contenedor, new IntercambioFragment()).commit();
    }
}
