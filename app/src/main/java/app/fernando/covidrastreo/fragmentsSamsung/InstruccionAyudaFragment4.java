package app.fernando.covidrastreo.fragmentsSamsung;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.fernando.covidrastreo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstruccionAyudaFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstruccionAyudaFragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnFinInstruccion;
    View vista;
    Activity actividad;

    private InstruccionAyudaFragment4.OnFragmentInteractionListener mListener;

    public InstruccionAyudaFragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstruccionAyudaFragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static InstruccionAyudaFragment4 newInstance(String param1, String param2) {
        InstruccionAyudaFragment4 fragment = new InstruccionAyudaFragment4();
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
        vista = inflater.inflate(R.layout.fragment_instruccion_ayuda4, container, false);
        btnFinInstruccion = vista.findViewById(R.id.btnFinInstruccion);

        btnFinInstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actividad.finish();
            }
        });

        return vista;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            actividad = (Activity) context;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}