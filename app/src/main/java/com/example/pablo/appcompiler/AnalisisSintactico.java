package com.example.pablo.appcompiler;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by pablo on 9/07/18.
 */

public class AnalisisSintactico {
    ArrayList<String[]> tokenList,grammarList,errorList,sentencias,inicializaciones;
    CreadorAutomatas automatas;

    public AnalisisSintactico(ArrayList<String[]> tokenList,CreadorAutomatas automatas,ArrayList<String[]> errorList){
        grammarList=new ArrayList();
        sentencias=new ArrayList();
        inicializaciones=new ArrayList();
        this.tokenList=tokenList;
        this.errorList=errorList;
        this.automatas=automatas;
        validateProductionTask();
    }
    /**
     * Metodo que permite analizar los tokens generados
     * en la etapa anterior, se realiza en base a un autómata
     *
     */
    public void validateProductionTask(){
        boolean isValid=false;
        String error[];
        Nodo j=automatas.bloqueTask.states[0];
        for(int i=0;i<tokenList.size();i++){
            for(int k=0;k<j.nextArco.size();k++){
                if(j.state.equals("q5")){
                    i=(validateProductionCodeBlock(j,i)-1);
                    j=j.nextArco.get(k).nextNodo.get(0);
                    i++;
                }
                isValid=checkIfTokenFollowSequence(j.nextArco.get(k).letter,tokenList.get(i)[0]);
                if(isValid){
                    j=j.nextArco.get(k).nextNodo.get(0);
                    isValid=true;
                    break;
                }
            }
            if(!j.state.equals("q5") && !isValid) {
                error=("Elemento: "+tokenList.get(i)[0]+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000001: Secuencia task no valida").split("-");
                errorList.add(error);
                j=automatas.bloqueTask.states[5];
                i=posicionarIndiceTokenAbloque(i);
                if(i==0){
                    error=("Elemento no definido "+"-Numero de linea"+tokenList.get(i)[1]+"-Error Sintactico-Id ES000002: Secuencia task irreparable").split("-");
                    errorList.add(error);
                    return;
                }
            }
        }
    }

    public boolean isId(String txt){;
        return Pattern.compile("\\_([a-zA-Z0-9]){1,20}").matcher(txt).matches();
    }

    public boolean checkIfTokenFollowSequence(String rule,String tokenContent){
        /*   \\_([a-zA-Z0-9]){1,20}     */
        System.out.println("rule= "+rule);
        System.out.println("tokenContent= "+tokenContent);
        System.out.println("ValidoClasico-> "+Pattern.compile("==(([0-9])+.([0-9])+)|([0-9])+\\$").matcher(tokenContent).matches());
        System.out.println("ValidoRule-> "+Pattern.compile(rule).matcher(tokenContent).matches());

        return Pattern.compile(rule).matcher(tokenContent).matches();
    }

    public int validateProductionCodeBlock(Nodo j,int index){
        boolean foundValidSequence=false;
        int lastLineAnalyzed=1;
        String sentencia="";
        String id="\\_([a-zA-Z0-9])+";
        String num="(([0-9])+.([0-9])+)|([0-9])+";
        String cad="'([a-zA-Z0-9])+'";
        String asignacion0=id+" == ("+num+") \\$ ";
        String asignacion1=id+" == ("+cad+") \\$ ";
        String asignacion2=id+" == ("+id+") \\$ ";
        String inicializacion0="int "+id+" == ("+num+") \\$ ";
        String inicializacion1="int "+id+" \\$ ";

        String error[];
        int cantOprSentencia=0;
        boolean foundAtLeastOne=false;

        for(;index<tokenList.size();index++){
            sentencia+=tokenList.get(index)[0]+" ";
            cantOprSentencia++;

            if(foundAtLeastOne){
                lastLineAnalyzed=Integer.parseInt(tokenList.get(index)[1]);
                if(!(tokenList.get(index)[0].equals("}") && index==tokenList.size()-1)){
                    foundAtLeastOne=false;
                }
            }

            if(tokenList.get(index)[0].equals("}")){
                if(index==tokenList.size()-1){
                    if(!foundAtLeastOne){
                        error=("Error de secuencia a partir de "+tokenList.get(posicionarIndiceTokenAultimaLinea(lastLineAnalyzed))[0]+"-|Numero de linea "+tokenList.get(posicionarIndiceTokenAultimaLinea(lastLineAnalyzed))[1]+"-|Error Sintactico-|Id ES000003: Secuencia bloque sentencial").split("-");
                        errorList.add(error);
                        index=posicionarIndiceTokenAultimaLinea(lastLineAnalyzed)+1;
                        cantOprSentencia=0;
                        sentencia="";
                        break;
                    }
                    break;
                }
            }
            if(cantOprSentencia==3){
                if(Pattern.compile(asignacion1).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(index)[1]+"@"+sentencia+"@asignacion ").split("@");
                    inicializaciones.add(sent);
                    cantOprSentencia=0;
                    sentencia="";
                    foundAtLeastOne=true;
                }else{
                    foundAtLeastOne=false;
                }
            }
            if(cantOprSentencia==4){
                if(Pattern.compile(asignacion0).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(index)[1]+"@"+sentencia+"@asignacion ").split("@");
                    sentencias.add(sent);
                    cantOprSentencia=0;
                    sentencia="";
                    foundAtLeastOne=true;
                }
                else if(Pattern.compile(asignacion1).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(index)[1]+"@"+sentencia+"@asignacion ").split("@");
                    sentencias.add(sent);
                    cantOprSentencia=0;
                    sentencia="";
                    foundAtLeastOne=true;
                }
                else if(Pattern.compile(asignacion2).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(index)[1]+"@"+sentencia+"@asignacion ").split("@");
                    sentencias.add(sent);
                    cantOprSentencia=0;
                    sentencia="";
                    foundAtLeastOne=true;
                }else{
                    foundAtLeastOne=false;
                }
            }
            if(cantOprSentencia==5){
                if(Pattern.compile(inicializacion0).matcher(sentencia).matches()){
                    String[] sent=(tokenList.get(index)[1]+"@"+sentencia+"@inicializacion ").split("@");
                    inicializaciones.add(sent);
                    cantOprSentencia=0;
                    sentencia="";
                    foundAtLeastOne=true;
                }else{
                    foundAtLeastOne=false;
                }
            }
        }
        return index;
    }

    public int posicionarIndiceTokenAbloque(int pos){
        boolean foundPivot=false;
        for(;pos<tokenList.size();pos++){
            if(tokenList.get(pos)[0].equals("{")){
                foundPivot=true;
                break;
            }
        }
        if(!foundPivot){
            pos=0;
        }
        return pos;
    }

    public int posicionarIndiceTokenAultimaLinea(int pos){
        int indice=0;
        String posicion=pos+"";

        for(int i=0;i<tokenList.size();i++){
            if(tokenList.get(i)[1].equals(posicion)){
                indice=i;
                break;
            }
        }
        return indice;
    }
}
