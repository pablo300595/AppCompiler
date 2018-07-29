package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimbolosFragment extends Fragment{
    static TextView tvTablaSimbolos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_simbolos,container,false);
        tvTablaSimbolos=v.findViewById(R.id.tvTablaSimbolos);
        return v;
    }
}
