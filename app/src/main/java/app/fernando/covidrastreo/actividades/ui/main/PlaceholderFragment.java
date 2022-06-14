package app.fernando.covidrastreo.actividades.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

//import com.example.covidrastreo.actividades.R;
import app.fernando.covidrastreo.fragmentsHuawei.InstruccionAyudaFragment0Huawei;
import app.fernando.covidrastreo.R;
import app.fernando.covidrastreo.fragmentsHuawei.InstruccionAyudaFragment1Huawei;
import app.fernando.covidrastreo.fragmentsHuawei.InstruccionAyudaFragment2Huawei;
import app.fernando.covidrastreo.fragmentsHuawei.InstruccionAyudaFragment3Huawei;
import app.fernando.covidrastreo.fragmentsHuawei.InstruccionAyudaFragment4Huawei;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {
        Fragment fragment = null;

        switch (index){
            case 1:
                fragment = new InstruccionAyudaFragment0Huawei();
                break;
            case 2:
                fragment = new InstruccionAyudaFragment1Huawei();
                break;
            case 3:
                fragment = new InstruccionAyudaFragment2Huawei();
                break;
            case 4:
                fragment = new InstruccionAyudaFragment3Huawei();
                break;
            case 5:
                fragment = new InstruccionAyudaFragment4Huawei();
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