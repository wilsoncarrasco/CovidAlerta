package app.fernando.covidrastreo.actividades;

import android.net.Uri;
import android.os.Bundle;

import app.fernando.covidrastreo.R;
import app.fernando.covidrastreo.actividades.ui.main.SectionsPagerAdapter3;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi0;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi1;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi2;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi3;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi4;
import app.fernando.covidrastreo.fragmentsXiaomi.InstruccionAyudaFragmentXiaomi5;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContenedorInstruccionesBTXiaomi extends AppCompatActivity implements InstruccionAyudaFragmentXiaomi0.OnFragmentInteractionListener,
        InstruccionAyudaFragmentXiaomi1.OnFragmentInteractionListener, InstruccionAyudaFragmentXiaomi2.OnFragmentInteractionListener,
        InstruccionAyudaFragmentXiaomi3.OnFragmentInteractionListener, InstruccionAyudaFragmentXiaomi4.OnFragmentInteractionListener,
        InstruccionAyudaFragmentXiaomi5.OnFragmentInteractionListener{

    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones_b_t_xiaomi);

        SectionsPagerAdapter3 sectionsPagerAdapter = new SectionsPagerAdapter3(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos = findViewById(R.id.idLinearPuntos);

        agregarIndicadorPuntos(0);

        viewPager.addOnPageChangeListener(viewListener);
    }

    private void agregarIndicadorPuntos(int pos){
        puntosSlide = new TextView[6];
        linearPuntos.removeAllViews();

        for(int i = 0; i < puntosSlide.length; i++){
            puntosSlide[i] = new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(35);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.color_blanco));
            linearPuntos.addView(puntosSlide[i]);
        }

        if(puntosSlide.length > 0){
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            agregarIndicadorPuntos(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}