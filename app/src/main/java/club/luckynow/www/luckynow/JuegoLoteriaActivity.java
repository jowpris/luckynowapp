package club.luckynow.www.luckynow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JuegoLoteriaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static int ticketsComprados=0;
    private Button btnAgregarTicket;
    private TextView txtCronometro, txtTicketsComprados,txtNumTicketsComprados, txtTienes, txtParticipando;
    private BottomNavigationView bottomNavigationView1;
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_loteria);
        //ticketsComprados=0;

        txtCronometro = (TextView) findViewById(R.id.txtCronometro);
        //txtTicketsComprados = (TextView) findViewById(R.id.txtti);
        txtNumTicketsComprados = (TextView) findViewById(R.id.txtNumTicketsComprados);
        btnAgregarTicket = (Button) findViewById(R.id.btnParticipar);

        txtTienes = (TextView)findViewById(R.id.txtTienes);
        txtParticipando = (TextView)findViewById(R.id.txtParticipando);

        btnAgregarTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Hola", Toast.LENGTH_LONG).show();
                ticketsComprados++;
                //txtTicketsComprados.setText("Haz comprado: "+ticketsComprados+" tickets");
                txtNumTicketsComprados.setText(""+(JuegoLoteriaActivity.ticketsComprados));
            }
        });

        //txtTicketsComprados.setText("Haz comprado: "+ticketsComprados+" tickets");
        txtNumTicketsComprados.setText(""+(JuegoLoteriaActivity.ticketsComprados));


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/luckynow.ttf");
        btnAgregarTicket.setTypeface(custom_font);
        txtCronometro.setTypeface(custom_font);
        txtNumTicketsComprados.setTypeface(custom_font);
        txtTienes.setTypeface(custom_font);
        txtParticipando.setTypeface(custom_font);

        bottomNavigationView1= (BottomNavigationView) findViewById(R.id.bottomNavigationViewTicket);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.inicio1){
                    //Toast.makeText(contexto, "Te vas", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(JuegoLoteriaActivity.this, DrawerActivity.class);
                    startActivity(i);

                }else if (item.getItemId()==R.id.recarga){

                }else if (item.getItemId()==R.id.premio){

                }
                return false;
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
