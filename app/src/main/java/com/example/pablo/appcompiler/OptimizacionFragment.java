package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;

public class OptimizacionFragment extends Fragment{
    EditText cod;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_optimizacion,container,false);
        cod=v.findViewById(R.id.editOptimizacion);

        return v;
    }
}
