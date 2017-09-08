package club.luckynow.www.luckynow;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JuegoTragamonedasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JuegoTragamonedasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JuegoTragamonedasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private TextView msg;
    private ImageView img1, img2, img3;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }


    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JuegoTragamonedasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JuegoTragamonedasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JuegoTragamonedasFragment newInstance(String param1, String param2) {
        JuegoTragamonedasFragment fragment = new JuegoTragamonedasFragment();
        Bundle args = new Bundle();
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


        View view = inflater.inflate(R.layout.fragment_juego_tragamonedas, container, false);
//        ImageView imageView = (ImageView) view.findViewById(R.id.slot_1);
//        Log.d("Imaga view:", ""+imageView);

        img1 = (ImageView) view.findViewById(R.id.slot_1);
        img2 = (ImageView) view.findViewById(R.id.slot_2);
        img3 = (ImageView) view.findViewById(R.id.slot_3);
        btn = (Button) view.findViewById(R.id.btnLanzar);
        final TextView textViewMonedasSiGana = (TextView)view.findViewById(R.id.textViewMonedasSiGana);
        //msg = (TextView) view.findViewById(R.id.msg);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(Usuario.monedas>= 50) {
                btn.setBackgroundResource(R.drawable.bg_btn_lanzado);
                    btn.setText("Espere");

                    HomeActivity.bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    HomeActivity.bottomNavigationView.getMenu().getItem(1).setEnabled(false);
                    HomeActivity.bottomNavigationView.getMenu().getItem(2).setEnabled(false);
                    HomeActivity.bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    HomeActivity.bottomNavigationView.getMenu().getItem(4).setEnabled(false);



                    Usuario.monedas -= 50;
                    Random rnd = new Random();
                    int numero1 = (int)(rnd.nextDouble()*1000)+100;
                    int numero2 = (int)(rnd.nextDouble()*1000)+150;
                    int numero3 = (int)(rnd.nextDouble()*1000)+200;


                    HomeActivity.textViewCantidadMonedas.setText("" + Usuario.monedas);
                    wheel1 = new Wheel(new Wheel.WheelListener() {
                            @Override
                            public void newImage(final int img) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        img1.setImageResource(img);
                                    }
                                });
                            }
                        }, 200, randomLong(0, numero1));
                    wheel1.currentIndex = 2;
                        wheel1.start();
                        wheel2 = new Wheel(new Wheel.WheelListener() {
                            @Override
                            public void newImage(final int img) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        img2.setImageResource(img);
                                    }
                                });
                            }
                        }, 200, randomLong(0,numero2));
                        wheel2.start();
                        wheel3 = new Wheel(new Wheel.WheelListener() {
                            @Override
                            public void newImage(final int img) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        img3.setImageResource(img);
                                    }
                                });
                            }
                        }, 200, randomLong(0, numero3));

                        wheel3.start();
                        btn.setEnabled(false);
                        isStarted = true;


                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Log.d("<--------------", "----------------->");
                                wheel1.stopWheel();

                                //wheel2.stopWheel();
                                //wheel3.stopWheel();
                            }
                        }, 3000);
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Log.d("<--------------", "----------------->");
                                //wheel1.stopWheel();
                                wheel2.stopWheel();

                                //wheel3.stopWheel();
                            }
                        }, 4000);
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //Log.d("<--------------", "----------------->");
                                wheel3.stopWheel();



                                int ultimaPos = wheel3.currentIndex;
                                ultimaPos+=1;
                                if(ultimaPos == 6){
                                    ultimaPos=0;
                                }
                                if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == ultimaPos) {
                                    //
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("Felicitaciones")
                                            .setView(R.layout.felicitaciones_layout)
                                            .setCancelable(true)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                }
                                            });
                                    Usuario.puntos+=25;
                                    Usuario.monedas+= 500;
                                    textViewMonedasSiGana.setText("Haz ganado : 500");
                                    Usuario.actualizarPuntos();
                                    HomeActivity.textViewCantidadMonedas.setText("" + Usuario.monedas);
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }else if(wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == ultimaPos || wheel1.currentIndex == ultimaPos){

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //btn.setEnabled(true);
                                            Usuario.puntos+=10;
                                            Usuario.monedas+=50;
                                            Usuario.actualizarPuntos();
                                            HomeActivity.textViewCantidadMonedas.setText("" + Usuario.monedas);
                                            textViewMonedasSiGana.setText("Haz ganado : 050");
                                        }
                                    }, 450);
                                }
                                else{

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //btn.setEnabled(true);
                                            Usuario.puntos+=5;
                                            Usuario.actualizarPuntos();
                                            textViewMonedasSiGana.setText("Haz ganado : 000");

                                        }
                                    }, 450);

                                }
                            }
                        }, 5500);
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            HomeActivity.bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                            HomeActivity.bottomNavigationView.getMenu().getItem(1).setEnabled(true);
                            HomeActivity.bottomNavigationView.getMenu().getItem(2).setEnabled(true);
                            HomeActivity.bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                            HomeActivity.bottomNavigationView.getMenu().getItem(4).setEnabled(true);


                            btn.setText("Lanzar");
                            btn.setBackgroundResource(R.drawable.bg_btn_lanzar);
                            btn.setEnabled(true);

                        }
                    }, 6550);
                }else{
                    Toast.makeText(getActivity(), "No te quedan luckymonedas :(", Toast.LENGTH_LONG).show();
                }
            }
        });

        //btn.setOnClickListener(this);

        return view;
        //return inflater.inflate(R.layout.fragment_juego_tragamonedas, container, false);
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
