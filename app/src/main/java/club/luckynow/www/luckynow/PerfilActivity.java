package club.luckynow.www.luckynow;

import android.os.CpuUsageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView txtViewNombre = (TextView) findViewById(R.id.activity_perfil_nombre);
        TextView txtViewCorreo = (TextView) findViewById(R.id.activity_perfil_correo);
        ImageView imageView = (ImageView) findViewById(R.id.activity_perfil_foto);


        TextView txtCantidadMonedas = (TextView) findViewById(R.id.txtCantidadMonedas);
        txtCantidadMonedas.setText(""+Usuario.monedas);
        txtViewCorreo.setText(Usuario.correo);
        txtViewNombre.setText(Usuario.nombre);


        if(Usuario.imagen.toString() != "foto_perfil_usuario.png"){
            Glide.with(this).load(Usuario.imagen).apply(RequestOptions.circleCropTransform()).into(imageView);
            //Glide.with(this).load(Usuario.imagen).into(imageViewFotoPerfil);
            //Log.d("Soy la imagen:", ""+Usuario.imagen);
        }



    }
    public void retroceder(View v){
        this.finish();
    }
}
