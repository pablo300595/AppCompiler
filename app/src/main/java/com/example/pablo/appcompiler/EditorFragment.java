package com.example.pablo.appcompiler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Define las acciones del fragment de editor de código
public class EditorFragment extends Fragment{
    Button btnCompilar;
    EditText editCod;
    AnalisisLexico analizadorLexico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_editor,container,false);
        btnCompilar=v.findViewById(R.id.btnCompilar);
        editCod=v.findViewById(R.id.editCod);

        btnCompilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Evento de click a compilar que manda a procesar el texto escrito
            analizadorLexico=new AnalisisLexico(editCod.getText().toString());
            analizadorLexico.validateLexemas();
            analizadorLexico.printValidTokens();
            }
        });

        return v;
    }


}
