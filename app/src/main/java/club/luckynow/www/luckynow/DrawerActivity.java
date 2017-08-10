package club.luckynow.www.luckynow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static java.security.AccessController.getContext;


public class DrawerActivity extends AppCompatActivity


implements NavigationView.OnNavigationItemSelectedListener,PerfilFragment.OnFragmentInteractionListener {

    private BottomNavigationView bottomNavigationView;
    private Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        contexto = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //setTitle("Luckynow");

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/luckynow.ttf");
        Button btnLoteria = (Button)findViewById(R.id.btnLoteria);
        btnLoteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showLoteria= new Intent(view.getContext(), JuegoLoteriaActivity.class);
                startActivity(showLoteria);

//                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainContainer);
//                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View layout = inflater.inflate(R.layout.fragment_juego_loteria, null);
//                mainLayout.removeAllViews();
//                mainLayout.addView(layout);

            }
        });
        btnLoteria.setTypeface(custom_font);
        Button btnSorteo = (Button)findViewById(R.id.btnSorteo);
        btnSorteo.setTypeface(custom_font);

        btnSorteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showSorteo= new Intent(view.getContext(), LotteryActivity.class);
                startActivity(showSorteo);


                //        Menu inferior

                bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId()==R.id.inicio){

                            //Toast.makeText(contexto, "Inicio", Toast.LENGTH_LONG).show();


                        }else if (item.getItemId()==R.id.recarga){

                        }else if (item.getItemId()==R.id.premio){

                        }
                        return false;
                    }
                });

                //LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainContainer);
                //LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //View layout = inflater.inflate(R.layout.activity_lottery, null);
                //mainLayout.removeAllViews();
                //mainLayout.addView(layout);

            }
        });
        setTitle("Luckynow");



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_share) {

           Intent i = new Intent(this, CanjearActivity.class);

           startActivity(i);

        }
        else if (id == R.id.nav_perfil) {


          LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainContainer);
           LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View layout = inflater.inflate(R.layout.activity_perfil, null);
           mainLayout.removeAllViews();
           mainLayout.addView(layout);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
