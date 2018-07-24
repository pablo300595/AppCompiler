package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SalidaFragment extends Fragment{
    static TextView tvTokens,tvErrors;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_salida,container,false);
        tvTokens=v.findViewById(R.id.tvTokens);
        tvErrors=v.findViewById(R.id.tvErrors);
        return v;
    }
}
