package com.example.pablo.appcompiler;

/**
 * Created by pablo on 5/07/18.
 */

public class Nodo {
    Arco nextArco;
    String state;
    boolean isFinalState,isInitState;

    public Nodo(String state,boolean isFinalState,boolean isInitState){
        this.nextArco=null;
        this.state=state;
        this.isInitState=isInitState;
        this.isFinalState=isFinalState;

    }
}
