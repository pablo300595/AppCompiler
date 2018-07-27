package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IntermedioFragment extends Fragment{
    static TextView cod;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_intermedio,container,false);

        cod=v.findViewById(R.id.editIntermedio);
        cod.setText("hola");

        return v;
    }
}