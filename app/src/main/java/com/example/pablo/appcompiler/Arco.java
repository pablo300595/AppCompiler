package com.example.pablo.appcompiler;

import java.util.ArrayList;

/**
 * Created by pablo on 5/07/18.
 */

public class Arco {
    String letter;
    ArrayList<Nodo> nextNodo;

    public Arco(String letter){
        this.letter=letter;
        nextNodo=new ArrayList<Nodo>();
    }

    public void createNextNodo(Nodo node){
        nextNodo.add(node);
    }
}
