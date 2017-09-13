package club.luckynow.www.luckynow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecargarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecargarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecargarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;


    private OnFragmentInteractionListener mListener;

    public RecargarFragment() {
        //this.padre = padre;

        // Required empty public constructor
        //cantidad = 0;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecargarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecargarFragment newInstance(String param1, String param2) {
        RecargarFragment fragment = new RecargarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recargar, container, false);

        Button btnAgregarMonedas = (Button) view.findViewById(R.id.btnRecargar);

        final EditText editText = (EditText) view.findViewById(R.id.editTextCantidadRrecargar);


        btnAgregarMonedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Usuario.monedas += cantidad;
                //HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                //cantidad = 0;
                String texto = editText.getText().toString().toLowerCase();
                //String mensaje = "";


                new ConsultarCodigos(getContext(), texto).execute("");

                /*if(texto.equals("andrea")){
                    Usuario.monedas+=100;
                    HomeActivity.textViewCantidadMonedas.setText(""+Usuario.monedas);
                    mensaje= "Se acreditaron 100 luckymonedas";
                    Usuario.actualizarPuntos();
                }else{
                    mensaje= "Código incorrecto :(";
                }*/
                editText.setText("");

            }
        });

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
