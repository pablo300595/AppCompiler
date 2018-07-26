package com.example.pablo.appcompiler;

import java.util.ArrayList;

public class Optimizacion {
    ArrayList<String> sim;
    ArrayList<String> cod;

    public Optimizacion(String _e){
        cod=new ArrayList<>();
        String[] h=_e.split("\n");
        for(int i=0;i<h.length;i++){
            cod.add(h[i]);
        }
        sim=new ArrayList<>();
        opt();
    }

    void opt(){
        //==========Eliminar sentencias con declaraciones que no se inicializan==========
        //encontrar variables declaradas y no inicializadas
        for(int i=0;i<cod.size();i++){
           if(cod.get(i).matches("t(\\d)+;")) {
               String temp = cod.get(i);
               sim.add(temp);
           }
        }
        //buscar que esas variables se hayan inicializado en el código
        for(int i=0;i<cod.size();i++){
            if(cod.get(i).matches("t(\\d)+=(\\w)+;")||cod.get(i).matches("t(\\d)+=\"(\\w)+\";")){
                String inicializado=cod.get(i);

                    int t=0;
                    while(!(inicializado.substring(t,t+1).equals("="))){
                        t++;
                    }
                for(int q=0;q<sim.size();q++){
                    if(sim.get(q).equals(inicializado.substring(0,t)+";")){
                        sim.remove(sim.indexOf(sim.get(q)));
                    }
                }
            }
        }
        //eliminar lineas de variables que se quedaron sin inicialización
        for(int i=0;i<sim.size();i++){
            cod.remove(cod.indexOf(sim.get(i)));
        }

        //=========Simplificar expresiones donde se multiplica por 1 a la izquierda==========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='1'){
                        if((aux.charAt(c-1)=='=')&&(aux.charAt(c+1)=='*')){
                            aux = ((aux.substring(0,c))+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        //==========Simplificar expresiones donde se multiplica por 1 a la derecha==========
        for(int i=0;i<cod.size();i++) {
            String aux = "";
            if (cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")) {
                aux = cod.get(i);
                for (int c = 0; c < aux.length(); c++) {
                    if (aux.charAt(c) == '1') {
                        if (((aux.charAt(c - 1) == '*') && (aux.charAt(c + 1) == ';'))) {
                            aux = ((aux.substring(0, c - 1)) + (aux.substring(c + 1, aux.length())));
                            cod.remove(i);
                            cod.add(i, aux);
                        }
                    }
                }
            }
        }

        //=========Simplificar expresiones donde se realiza una multiplicación por 0 por la derecha==========
        for(int i=0;i<cod.size();i++){
            String aux;
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='0'){
                        if(((aux.charAt(c-1)=='*')&&(aux.charAt(c+1)==';'))) {
                            aux = ((aux.substring(0,c-1))+(aux.substring(c+1 ,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }
        //=========Simplificar expresiones donde se realiza una multiplicación por 0 por la izquierda==========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='0'){
                        if((aux.charAt(c-1)=='=')&&(aux.charAt(c+1)=='*')){
                            aux = ((aux.substring(0,c))+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        //==========Simplificar expresión donde se realiza una suma de 0 y x número por la izquierda==========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='0'){
                        if((aux.charAt(c-1)=='=')&&(aux.charAt(c+1)=='+')){
                            aux = ((aux.substring(0,c))+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        //==========simplificar una expresión donde se suma 0 con x número a la derecha==========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='0'){
                        if((aux.charAt(c-1)=='+')&&(aux.charAt(c+1)==';')){
                            aux = ((aux.substring(0,c))+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        //=========simplificar expresión matemática donde se realiza una suma con 0==========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='0'){
                        if((aux.charAt(c-1)=='+')&&(aux.charAt(c+1)=='+')){
                            aux = ((aux.substring(0,c-1))+"+"+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        //========simplificar expresión matemática donde se realiza una multiplicacion con 1=========
        for(int i=0;i<cod.size();i++){
            String aux="";
            if(cod.get(i).matches("t(\\d)+=(\\d||\\+||\\*||\\-||\\/)+;")){
                aux=cod.get(i);
                for(int c=0;c<aux.length();c++){
                    if(aux.charAt(c)=='1'){
                        if((aux.charAt(c-1)=='*')&&(aux.charAt(c+1)=='*')){
                            aux = ((aux.substring(0,c-1))+"*"+(aux.substring(c+2,aux.length())));
                            cod.remove(i);
                            cod.add(i,aux);
                        }
                    }
                }
            }
        }

        for(int i=0;i<cod.size();i++){
            System.out.println(cod.get(i));
        }
    }

    public static void main(String[] args) {
        CodigoIntermedio intermediocodigo=new CodigoIntermedio();
        intermediocodigo.generarCodigo();
        System.out.println(intermediocodigo.codigoIntermedio);
        Optimizacion o=new Optimizacion(intermediocodigo.codigoIntermedio);
    }

}
