package club.luckynow.www.luckynow;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PremiosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PremiosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PremiosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;
    //public int array[] = new int [10];
    //public int data= 0;

    public PremiosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PremiosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PremiosFragment newInstance(String param1, String param2) {
        PremiosFragment fragment = new PremiosFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_premios, container, false);


        LinearLayout linearLayoutPlay = (LinearLayout)view.findViewById(R.id.btn_gift_play_store);
        linearLayoutPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Usuario.monedas-50000>=0){

                    Usuario.puntos+=500;
                    Usuario.monedas-=50000;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    //actualizar en la bd
                    Usuario.actualizarPuntos();
                    new EnviarCorreo(getContext()).execute("");

                    //Log.d("enviar correo", "");
                }else{
                    Toast.makeText(getContext(), "Te faltan "+ (50000 - Usuario.monedas) +" luckymonedas :c", Toast.LENGTH_LONG).show();
                }
            }
        });

        LinearLayout linearLayoutPaypal = (LinearLayout)view.findViewById(R.id.btn_gift_paypal);
        linearLayoutPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Usuario.monedas-100000>=0){

                    Usuario.puntos+=1000;
                    Usuario.monedas-=100000;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    //actualizar en la bd
                    Usuario.actualizarPuntos();
                    new EnviarCorreo(getContext()).execute("");


                    Log.d("enviar correo", "");
                }else{
                    Toast.makeText(getContext(), "Te faltan "+ (100000 - Usuario.monedas) +" luckymonedas :c", Toast.LENGTH_LONG).show();
                }
            }
        });


        LinearLayout linearLayoutAmazon = (LinearLayout)view.findViewById(R.id.btn_gift_amazon);
        linearLayoutAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Usuario.monedas-150000>=0){


                    Usuario.puntos+=1500;
                    Usuario.monedas-=150000;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    //actualizar en la bd
                    Usuario.actualizarPuntos();
                    new EnviarCorreo(getContext()).execute("");


                    Log.d("enviar correo", "");
                }else{
                    Toast.makeText(getContext(), "Te faltan "+ (150000 - Usuario.monedas) +" luckymonedas :c", Toast.LENGTH_LONG).show();
                }
            }
        });


        LinearLayout linearLayoutPizza = (LinearLayout)view.findViewById(R.id.btn_gift_pizza);
        linearLayoutPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Usuario.monedas-200000>=0){

                    Usuario.puntos+=2000;
                    Usuario.monedas-=200000;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    //actualizar en la bd
                    Usuario.actualizarPuntos();
                    new EnviarCorreo(getContext()).execute("");


                    Log.d("enviar correo", "");
                }else{
                    Toast.makeText(getContext(), "Te faltan "+ (200000 - Usuario.monedas) +" luckymonedas :c", Toast.LENGTH_LONG).show();
                }
            }
        });



        LayoutInflater inflater2 = LayoutInflater.from(getContext());

        final TextView textView = (TextView)view.findViewById(R.id.mensaje_ups);
        if(Usuario.cantidadPremios==0){


            textView.setVisibility(View.VISIBLE);
        }
        else{
            //TextView textView = (TextView)view.findViewById(R.id.mensaje_ups);
            textView.setVisibility(View.GONE);
        }
        for (int i = 0; i < Usuario.cantidadPremios; i++){
            //array[i] = data;
            final RelativeLayout layout = (RelativeLayout) inflater2.inflate(R.layout.premio_solo_layout, null, false);
            final LinearLayout linear = (LinearLayout)view.findViewById(R.id.layout_lista_premios);
            //layout.setBackgroundColor(Color.BLUE);

            /*linear.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));*/

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            linear.setVisibility(View.VISIBLE);
            params.setMargins(0, 15, 0, 0);
            layout.setLayoutParams(params);


            /*TextView tv = new TextView(this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            llview.addView(tv);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(2,25);
            tv.setText(chat);*/


            //linear.setBackgroundColor(Color.RED);
            linear.setTag(i);

            final Button btn = (Button)layout.findViewById(R.id.btn_reclamar_premio);
            btn.setTag(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), ""+btn.getTag()+ "", Toast.LENGTH_LONG).show();
                   // btn.setVisibility(View.GONE);
                    //btn.getParent()
                    Usuario.monedas += 200;
                    Usuario.puntos +=10;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    Usuario.cantidadPremios-=1;
                    Usuario.guardarPremios();
                    Usuario.actualizarPuntos();
                    //Log.d("Cantidad Premios", ""+Usuario.cantidadPremios);
                    //if(linear.getTag()==btn.getTag()){
                     //   layout.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);

                    if(Usuario.cantidadPremios ==0 ){

                        //TextView textView = (TextView)view.findViewById(R.id.mensaje_ups);
                        textView.setVisibility(View.VISIBLE);
                        //linear.setVisibility(View.GONE);

                    }

                    //}
                }
            });
            //data+=1;

            linear.addView(layout);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
