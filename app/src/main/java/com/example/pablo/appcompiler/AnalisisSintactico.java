package com.example.pablo.appcompiler;

import java.util.ArrayList;

/**
 * Created by pablo on 9/07/18.
 */

public class AnalisisSintactico {
    ArrayList productions;

    public AnalisisSintactico(){
        initProductions();
    }

    public void initProductions(){
        productions.add("S->meta | serialTask | maincode");
        productions.add("serialTask->task | serialTask");
        productions.add("Etask->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");
        productions.add("S->meta | serialTask | maincode");

    }

    public boolean validateString(String data){
        boolean isValid=false;

        for(int i=0;i<productions.size();i++){
            for(int k=0;k<productions.size();k++){
                for(int j=0;j<productions.size();j++){
                    if(productions.get(i).equals(data)){
                        isValid=true;
                    }
                }
            }
        }

        return isValid;

    }
}
