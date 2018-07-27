package com.example.pablo.appcompiler;

import android.app.Activity;
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
    static String tokenList,errorList,validTokenList,tablaSimbolos;
    AnalisisLexico analizadorLexico;
    AnalisisSintactico analizadorSintactico;
    AnalisisSemantico analizadorSemantico;
    EnviarMensaje EM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_editor,container,false);
        btnCompilar=v.findViewById(R.id.btnCompilar);
        editCod=v.findViewById(R.id.editCod);

        final DataValidator data=new DataValidator();

        btnCompilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Evento de click a compilar que manda a procesar el texto escrito
                if(data.component.equals(editCod.getText().toString())){
                    analizadorLexico=new AnalisisLexico(editCod.getText().toString());
                    analizadorLexico.generateTokens();
                    tokenList=analizadorLexico.printValidTokens();
                    SalidaFragment.tvTokens.setText(tokenList);
                    SalidaFragment.tvErrors.setText("No hay error");
                }else{
                    analizadorLexico=new AnalisisLexico(editCod.getText().toString());
                    analizadorLexico.generateTokens();
                    analizadorLexico.createErrorList();
                    tokenList=analizadorLexico.printValidTokens();
                    validTokenList=analizadorLexico.createValidTokenList();
                    analizadorSintactico=new AnalisisSintactico(analizadorLexico.validTokenList,analizadorLexico.automatas,analizadorLexico.errorList);
                    analizadorSemantico=new AnalisisSemantico(analizadorSintactico.errorList,analizadorSintactico.sentencias,analizadorSintactico.inicializaciones,analizadorSintactico.automatas);
                    errorList=analizadorSemantico.createErrorList();
                    tablaSimbolos=analizadorSemantico.getSymbolElements();
                    SalidaFragment.tvTokens.setText(tokenList);
                    SalidaFragment.tvErrors.setText(errorList);
                }
                if(errorList.length()>0){
                    
                }


                //EM.enviarDatos(tablaSimbolos);

            }
        });

        return v;
    }

    public void onAttach(Activity activity){
            super.onAttach(activity);
            EM=(EnviarMensaje) activity;
    }


}
