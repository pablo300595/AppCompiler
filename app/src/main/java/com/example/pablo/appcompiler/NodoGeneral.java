package com.example.pablo.appcompiler;

public class NodoGeneral{
    NodoLista ini,fin;
    Object valor;

    public NodoGeneral(Object valor){
        this.valor = valor;
        ini = null;
        fin = null;
    }

    public boolean esHoja(){
        return ini==null && fin==null;
    }

    public boolean esRama(){
        return !esHoja();
    }

    public boolean enlazar(NodoGeneral hijo){
        NodoLista temp = new NodoLista(hijo);

        if(ini == null && fin == null){
            ini=temp;
            fin=temp;
            return true;
        }
        fin.sig=temp;
        temp.ant=fin;
        fin=temp;

        return true;
    }

    public boolean desenlazar(NodoGeneral hijo){
        if(ini==null && fin==null){
            return false;
        }
        if(ini==fin){
            if(ini.dirHijo.equals(hijo)){
                ini.dirHijo = null;
                ini = null;
                fin = null;
                return true;
            }
            return false;
        }
        NodoLista temp;

        if(ini.dirHijo.equals(hijo)){
            temp=ini;
            ini=temp.sig;
            temp.sig=null;
            ini.ant=null;
            temp.dirHijo=null;
            temp=null;
            return true;
        }
        if(fin.dirHijo.equals(hijo)){
            temp = fin;
            fin = temp.ant;
            fin.sig = null;
            temp.ant = null;
            temp.dirHijo=null;
            temp = null;
            return true;
        }
        for(temp= ini.sig;temp!=fin;temp= temp.sig){
            if(temp.dirHijo.equals(hijo)){
                temp.ant.sig = temp.sig;
                temp.sig.ant = temp.ant;
                temp.ant = temp.sig = null;
                temp.dirHijo=null;
                temp = null;
                return true;
            }
        }
        return false;
    }

    public NodoGeneral buscarNodo(String valorHijo){
        NodoLista i;

        for(i=ini;i!=null;i =i.sig){
            if(i.dirHijo.valor == valorHijo){
                return i.dirHijo;
            }
        }
        return null;
    }
}
