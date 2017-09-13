package club.luckynow.www.luckynow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RankingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Usuario.ganadores.clear();
        final View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        //https://ksantacrwordpresscom.000webhostapp.com/ganadores.php
        new ConsultarGanadores(getContext(), view).execute("");


        /*final ArrayList<Ganador> ganadores = Usuario.ganadores;

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
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView);

            imageView.getLayoutParams().height = 175;
            imageView.getLayoutParams().width = 175;

        }
        if(!ganadores.get(1).getFoto().contains("foto_perfil_usuario.png")){
            //Log.d("Hola2", ""+ganadores.get(1).getFoto());
            Uri myUri = Uri.parse(ganadores.get(1).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView2);
        }
        if(!ganadores.get(2).getFoto().contains("foto_perfil_usuario.png")){
            //Log.d("Holaasd", ""+ganadores.get(2).getFoto());
            Uri myUri = Uri.parse(ganadores.get(2).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView3);
        }
        if(!ganadores.get(3).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(3).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView4);
        }
        if(!ganadores.get(4).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(4).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView5);
        }
        if(!ganadores.get(5).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(5).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView6);
        }
        if(!ganadores.get(6).getFoto().contains("foto_perfil_usuario.png")){
            Uri myUri = Uri.parse(ganadores.get(6).getFoto());
            Glide.with(getContext()).load(myUri).apply(RequestOptions.circleCropTransform()).into(imageView7);
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

*/
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
             //       + " must implement OnFragmentInteractionListener");
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
