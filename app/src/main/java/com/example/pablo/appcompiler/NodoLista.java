package com.example.pablo.appcompiler;

public class NodoLista{
    NodoGeneral dirHijo;
    NodoLista ant,sig;

    public NodoLista(NodoGeneral n){
        dirHijo= n;
        ant= null;
        sig= null;
    }
}
