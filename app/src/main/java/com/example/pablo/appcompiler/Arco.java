package com.example.pablo.appcompiler;

import java.util.ArrayList;

/**
 * Created by pablo on 5/07/18.
 */
//La clase define la transición
public class Arco {
    String letter;
    ArrayList<Nodo> nextNodo;

    /**
     * Constructor que permite crear un arco y definir su contenido
     * @return El arco
     */
    public Arco(String letter){
        this.letter=letter;
        nextNodo=new ArrayList<Nodo>();
    }

    /**
     * Método que permite crear un puntero de tipo nodo para un arco dado
     * @return El puntero del arco
     */
    public void createNextNodo(Nodo node){
        nextNodo.add(node);
    }
}
