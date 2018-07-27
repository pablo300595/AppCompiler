package com.example.pablo.appcompiler;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditorActivity extends AppCompatActivity implements EnviarMensaje {

    private TabLayout tblayout;
    private ViewPager vwpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_fragment);

        tblayout=findViewById(R.id.tblayout);
        vwpager=findViewById(R.id.vwpager);

        tblayout.setupWithViewPager(vwpager);

        cambiaPager(vwpager);
    }

    private void cambiaPager(ViewPager p){
        PagerAdapter pa=new PagerAdapter(getSupportFragmentManager());
        pa.addFragment(new EditorFragment(),"Editor");
        pa.addFragment(new SalidaFragment(),"Salida");
        pa.addFragment(new IntermedioFragment(),"Intermedio");
        pa.addFragment(new OptimizacionFragment(),"Optimización");
        pa.addFragment(new ObjetoFragment(),"Objeto");
        vwpager.setAdapter(pa);
    }

    @Override
    public void enviarDatos(String mensaje){
        //SimbolosFragment fragment = (SimbolosFragment) getFragmentManager().findFragmentById(R.id);
    }
}
