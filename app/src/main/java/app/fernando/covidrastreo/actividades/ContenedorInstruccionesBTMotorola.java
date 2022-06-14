package app.fernando.covidrastreo.actividades;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.fernando.covidrastreo.actividades.ui.main.SectionsPagerAdapter2;
import app.fernando.covidrastreo.R;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola0;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola1;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola2;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola3;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola4;
import app.fernando.covidrastreo.fragmentsMotorola.InstruccionAyudaMotorola5;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


public class ContenedorInstruccionesBTMotorola extends AppCompatActivity implements InstruccionAyudaMotorola0.OnFragmentInteractionListener,
        InstruccionAyudaMotorola1.OnFragmentInteractionListener, InstruccionAyudaMotorola2.OnFragmentInteractionListener,
        InstruccionAyudaMotorola3.OnFragmentInteractionListener, InstruccionAyudaMotorola4.OnFragmentInteractionListener,
        InstruccionAyudaMotorola5.OnFragmentInteractionListener{

    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones_bt_motorola);

        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());

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