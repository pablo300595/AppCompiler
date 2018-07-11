package com.example.pablo.appcompiler;

import java.util.ArrayList;

/**
 * Created by pablo on 8/07/18.
 */
//Clase que permite validar un Código fuente a través de Autómatas
public class AnalisisLexico {
    ArrayList sourceCode;
    ArrayList tokenList;
    CreadorAutomatas automatas;
    String[] manejadorError;
    /**
     * Constructor que permite procesar un código fuente.
     * Primero quita metacaracteres inutiles (Como salto de línea).
     * Posteriormente separa los símbolos de 1 solo caracter cómo $
     * Finalmente se guardan en una tabla de tokens
     */
    public AnalisisLexico(String preSourceCode){
        sourceCode=new ArrayList();
        tokenList=new ArrayList();
        manejadorError=new String[5];manejadorError[0]="Error Léxico ";manejadorError[1]="ID: ";
        manejadorError[2]="Descripción: ";manejadorError[3]="Linea: ";manejadorError[4]="Token: ";
        manejadorError[5]="Columna: ";
        //Mecanismo que separa los simbolos para poder procesarlos mediante el método split
        preSourceCode=preSourceCode.replaceAll("\n","");
        preSourceCode=preSourceCode.replaceAll("\\{"," \\{ ");
        preSourceCode=preSourceCode.replaceAll("\\}"," \\} ");
        preSourceCode=preSourceCode.replaceAll("\\+"," \\+ ");
        preSourceCode=preSourceCode.replaceAll("\\-"," \\- ");
        preSourceCode=preSourceCode.replaceAll("\\*"," \\* ");
        preSourceCode=preSourceCode.replaceAll("\\/"," \\/ ");
        preSourceCode=preSourceCode.replaceAll("\\$"," \\$ ");
        preSourceCode=preSourceCode.replaceAll("\\("," \\( ");
        preSourceCode=preSourceCode.replaceAll("\\)"," \\) ");
        preSourceCode=preSourceCode.replaceAll("\\="," \\= ");

        String[] preSourceCodeArray=preSourceCode.split(" ");
        //Mecanismo que permitirá guardar los tokens ignorando espacios en blanco
        for(int i=0;i<preSourceCodeArray.length;i++){
            if(!preSourceCodeArray[i].equals("")){
                sourceCode.add(preSourceCodeArray[i]);
            }
        }
        //Instancia de la clase CreadorAutómatas que permite crear todos los autómatas.
        automatas=new CreadorAutomatas();

    }
    /**
     * Método que permite validar los lexemas del código fuente en base
     * a los autómatas del lenguajes. Si el token cumple con algún patrón
     * será añadido a una tabla de tokens tokenList
     * @return nada
     */
    public String validateLexemas(){
        String validationMessage="IS_INVALIDO";

        for(int i=0;i<sourceCode.size();i++){
            if(validateToken(sourceCode.get(i).toString())){
                tokenList.add(sourceCode.get(i).toString());
            }else{
                tokenList.add("Error Lexico");
            }
        }

        return validationMessage;
    }
    /**
     * Método que permite validar cada token individualmente
     * @return boolean si es válido el token
     */
    public boolean validateToken(String token){
        boolean isValid=false;
        int pointerIndex=0;
        String pointer=token.substring(pointerIndex,pointerIndex+1);
        //Mecanismo que revisa si el token es una palabra reservada
        for(int i=0;i<automatas.keyword.length;i++){
            for(Nodo j=automatas.keyword[i].states[0];j.nextArco.size()!=0;j=j.nextArco.get(0).nextNodo.get(0)){

                isValid=pointer.equals(j.nextArco.get(0).letter);
                if(!isValid){
                    pointerIndex=0;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                    break;
                }else{
                    if(j.nextArco.get(0).nextNodo.get(0).isFinalState){
                        if(pointerIndex<token.length()-1){
                            isValid=false;
                        }
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }
        pointerIndex=0;
        //Mecanismo que revisa si el token es un identificador
        for(Nodo i=automatas.identifier.states[0];pointerIndex<token.length();){
            for(int k=0;k<i.nextArco.size();k++){
                pointer=token.substring(pointerIndex,pointerIndex+1);
                isValid=pointer.equals(i.nextArco.get(k).letter);
                if(!isValid){
                    if(pointerIndex==0 || k==i.nextArco.size()-1){
                        i=automatas.identifier.states[0];
                        return false;
                    }
                }else{
                    pointerIndex++;
                    i=i.nextArco.get(k).nextNodo.get(0);
                    isValid=true;
                    break;
                }
            }
        }
        return isValid;
    }
    /**
     * Método que permite imprimir la lista de tokens en consola
     * @return nada
     */
    public void printValidTokens(){
        System.out.println("----------------------TOKENS-------------------------");
        for(int i=0;i<tokenList.size();i++){
            System.out.println(tokenList.get(i).toString());
        }
    }

}
