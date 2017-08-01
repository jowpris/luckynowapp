package club.luckynow.www.luckynow;

import android.content.Intent;

import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnJugar;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnJugar = (Button) findViewById(R.id.btnLogin);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/luckynow.ttf");
        btnJugar.setTypeface(custom_font);


        btnJugar = (Button) findViewById(R.id.btnLogin);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jugarIntent = new Intent(view.getContext(), DrawerActivity.class);
                startActivity(jugarIntent);
            }
        });
    }
}
