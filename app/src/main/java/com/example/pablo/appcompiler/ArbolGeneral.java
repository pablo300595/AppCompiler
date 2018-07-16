package com.example.pablo.appcompiler;

import java.util.Arrays;
public class ArbolGeneral{
    NodoGeneral raiz;

    public ArbolGeneral(){
        raiz= null;
    }

    public boolean insertar(String ruta, Object valor){
        if(raiz==null){
            raiz= new NodoGeneral(valor);
            return true;
        }
        NodoGeneral padre = busquedaPorCamino(ruta);

        if(padre==null){
            return false;
        }
        NodoGeneral hijo = new NodoGeneral(valor);

        return padre.enlazar(hijo);
    }

    public boolean eliminar(String ruta){
        if(raiz == null){
            return false;
        }
        NodoGeneral hijo = busquedaPorCamino(ruta);

        if(hijo == raiz){
            if(raiz.esHoja()){
                raiz = null;
                return true;
            }
            return false;
        }
        NodoGeneral padre = busquedaPorCamino(ruta.substring(0,ruta.lastIndexOf("/")));

        if(padre==null || hijo==null){
            return false;
        }
        if(hijo.esHoja()){
            return padre.desenlazar(hijo);
        }

        return false;
    }

    public NodoGeneral busquedaPorCamino(String ruta){
        if(!ruta.startsWith("/")){
            return null;
        }
        ruta= ruta.substring(1);
        String arr[]= ruta.split("/");

        if(raiz.valor.equals(arr[0])){
            if(arr.length==1){
                return raiz;
            }
            return busquedaPorCamino(raiz, Arrays.copyOfRange(arr,1,arr.length));
        }

        return null;
    }

    public NodoGeneral busquedaPorCamino(NodoGeneral nodo, String[] arr){
        NodoGeneral temp= nodo.buscarNodo(arr[0]);

        if(temp == null){
            return null;
        }
        if(arr.length==1){
            return temp;
        }

        return busquedaPorCamino(temp, Arrays.copyOfRange(arr,1,arr.length));
    }
}
