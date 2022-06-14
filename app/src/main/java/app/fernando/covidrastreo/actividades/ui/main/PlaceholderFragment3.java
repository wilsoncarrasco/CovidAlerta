package app.fernando.covidrastreo.actividades.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import app.fernando.covidrastreo.R;

import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi0;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi1;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi2;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi3;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi4;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi5;

public class PlaceholderFragment3 extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {
        Fragment fragment = null;

        switch (index){
            case 1:
                fragment = new InstruccionAyudaFragmentXiaomi0();
                break;
            case 2:
                fragment = new InstruccionAyudaFragmentXiaomi1();
                break;
            case 3:
                fragment = new InstruccionAyudaFragmentXiaomi2();
                break;
            case 4:
                fragment = new InstruccionAyudaFragmentXiaomi3();
                break;
            case 5:
                fragment = new InstruccionAyudaFragmentXiaomi4();
                break;
            case 6:
                fragment = new InstruccionAyudaFragmentXiaomi5();
                break;
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contenedor_instrucciones_bt_huawei, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
