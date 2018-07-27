package com.example.pablo.appcompiler;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by pablo on 23/07/18.
 */

public class AnalisisSemantico {
    ArrayList<String[]> errorList,sentencias,inicializaciones,tablaSimbolos;
    CreadorAutomatas automatas;
    ArrayList<ArrayList<String []>> calls;
    int a=0;

    public AnalisisSemantico(ArrayList<String[]> errorList,ArrayList <String[]>sentencias,ArrayList<String[]> inicializaciones,CreadorAutomatas automatas){
        this.errorList=errorList;
        this.sentencias=sentencias;
        this.inicializaciones=inicializaciones;
        this.automatas=automatas;
        tablaSimbolos=new ArrayList();
        initTablaSimbolos();
        validateDataType();
        a=10;

        calls= new ArrayList<>();

    }

    public void initTablaSimbolos(){
        String[] content;
        for(int i=0;i<sentencias.size();i++){
            if(sentencias.get(i)[2].equals("inicializacion ")){
                content=(sentencias.get(i)[0]+"-"+getType(sentencias.get(i)[1])+"-"+
                        getId(sentencias.get(i)[1])+"-"+getValue(sentencias.get(i)[1])).split("-");
                tablaSimbolos.add(content);
            }
        }
    }

    public String getType(String sentencia){
        String type="null";
        String[] sentenceElementPerElement=sentencia.split(" ");
        for(int i=0;i<sentenceElementPerElement.length;i++){
            if(sentenceElementPerElement[i].equals("int")){
                type="int";
                break;
            }
            if(sentenceElementPerElement[i].equals("text")){
                type="text";
                break;
            }
        }
        return type;
    }

    public String getId(String sentencia){
        String id="";
        String[] sentenceElementPerElement=sentencia.split(" ");
        for(int i=0;i<sentenceElementPerElement.length;i++){
            if(Pattern.compile("\\_([a-zA-Z0-9])+").matcher(sentenceElementPerElement[i]).matches()){
                id=sentenceElementPerElement[i];
                break;
            }
        }
        return id;
    }

    public String getValue(String sentencia){
        String value="";
        String[] sentenceElementPerElement=sentencia.split(" ");
        for(int i=0;i<sentenceElementPerElement.length;i++){
            if(Pattern.compile("(([0-9])+.([0-9])+)|([0-9])+").matcher(sentenceElementPerElement[i]).matches()||
                    Pattern.compile("'([a-zA-Z0-9])+'").matcher(sentenceElementPerElement[i]).matches()){
                value=sentenceElementPerElement[i];
                break;
            }
        }
        return value;
    }

    public boolean validateDataType(){
        String error[];
        for(int i=0;i<tablaSimbolos.size();i++){
            if(tablaSimbolos.get(i)[1].equals("int")){
                if(Pattern.compile("(([0-9])+.([0-9])+)").matcher(tablaSimbolos.get(i)[3]).matches()
                || Pattern.compile("'([a-zA-Z0-9])+'").matcher(tablaSimbolos.get(i)[3]).matches()){
                    error=("Tipos no compatibles el valor "+tablaSimbolos.get(i)[3]+" no es entero para la variable "+tablaSimbolos.get(i)[2]+"-Numero de linea "+tablaSimbolos.get(i)[0]+"-Error Semantico-Id ESE00004: Datos no compatibles").split("-");
                    errorList.add(error);
                }
            }
        }
        return false;
    }

    /**
     * Método que permite obtener la lista de tokens de error
     * @return tokens
     */
    public String createErrorList(){
        String tokenInfo="";

        for(int i=0;i<errorList.size();i++){
            tokenInfo+=errorList.get(i)[0]+"\n";
            tokenInfo+=errorList.get(i)[1]+"\n";
            tokenInfo+=errorList.get(i)[2]+"\n";
            tokenInfo+=errorList.get(i)[3]+"\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }

    /**
     * Método que permite obtener los elementos de la tabla de simbolos
     * @return tokens
     */
    public String getSymbolElements(){
        String tokenInfo="";

        for(int i=0;i<tablaSimbolos.size();i++){
            tokenInfo+=tablaSimbolos.get(i)[0]+"\n";
            tokenInfo+=tablaSimbolos.get(i)[1]+"\n";
            tokenInfo+=tablaSimbolos.get(i)[2]+"\n";
            tokenInfo+=tablaSimbolos.get(i)[3]+"\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }

    public void generateSourcreCode(){

        calls.add(sentencias);
    }


}
