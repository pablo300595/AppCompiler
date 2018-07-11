package com.example.pablo.appcompiler;

import java.util.ArrayList;

/**
 * Created by pablo on 5/07/18.
 */

public class Nodo {
    ArrayList <Arco> nextArco;
    String state;
    boolean isFinalState,isInitState;

    public Nodo(String state,boolean isFinalState,boolean isInitState){
        this.state=state;
        this.isInitState=isInitState;
        this.isFinalState=isFinalState;
        nextArco=new ArrayList<Arco>();
    }

    public void createNextArco(Arco arc){
        nextArco.add(arc);
    }
}
