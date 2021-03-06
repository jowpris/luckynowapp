package club.luckynow.www.luckynow;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.sql.BatchUpdateException;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JuegoLoteriaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JuegoLoteriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JuegoLoteriaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JuegoLoteriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JuegoLoteriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JuegoLoteriaFragment newInstance(String param1, String param2) {
        JuegoLoteriaFragment fragment = new JuegoLoteriaFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //final String FORMAT = "%02d:%02d:%02d";
        //int seconds , minutes;
        View view = inflater.inflate(R.layout.fragment_juego_loteria, container, false);

        final TextView text1, textViewTicketsComprados;
        text1 = (TextView)view.findViewById(R.id.txtHora);
        textViewTicketsComprados = (TextView)view.findViewById(R.id.textViewTicketsComprados);


        //Log.d("PARTICIPANDO <-------------------- : ", ""+Usuario.participando);
        final Button button = (Button) view.findViewById(R.id.btn_participar_loteria);

        Log.d("Usuarop esta participando=?", ""+Usuario.participando);
        if(Usuario.participando){

            button.setEnabled(false);
            button.setBackgroundResource(R.drawable.bg_btn_lanzado);
            button.setPadding(0,0,0,0);
            button.setText("Ya estas participando");
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        }else{
        }
        textViewTicketsComprados.setText(""+Usuario.cantidadParticipantes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "clic "+Usuario.participando, Toast.LENGTH_LONG).show();

                if(Usuario.monedas>=200){
                    Usuario.monedas-=200;
                    Usuario.puntos+=50;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    Usuario.actualizarPuntos();

                    new ParticiparLoteria(getContext(), button, textViewTicketsComprados).execute("Ya estas participando");

                }else{

                    Toast.makeText(getContext(), "No te quedan luckymonedas :(", Toast.LENGTH_LONG).show();
                }

            }
        });
        //ConsultarMilisegundos consultarMilisegundos = (ConsultarMilisegundos)

        new ConsultarMilisegundos(getContext(), text1).execute("Se está preparando el sorteo");
        LayoutInflater inflater2 = LayoutInflater.from(getContext());

        final LinearLayout linear = (LinearLayout)view.findViewById(R.id.ganadoresLoteria);
        for (Ganador ganador: Usuario.ganadores_sorteo) {

        //}
        //for (int i = 0; i < Usuario.ganadores_sorteo.size(); i++){
            //array[i] = data;
            final LinearLayout layout = (LinearLayout) inflater2.inflate(R.layout.ganador_layout, null, false);

            //layout.setBackgroundColor(Color.BLUE);

            /*linear.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));*/

            final ImageView foto = (ImageView)layout.findViewById(R.id.img_ganador);
            final TextView nombre = (TextView)layout.findViewById(R.id.nombre_ganador);

            Log.d("Nombre", ""+ganador.getNombre());
            Log.d("Foto", ""+ganador.getFoto());

            if(!ganador.getFoto().contains("foto_perfil_usuario.png")){
                Uri myUri = Uri.parse(ganador.getFoto());
                Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(foto);
            }
            nombre.setText(""+ganador.getNombre());

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
