package com.example.pablo.appcompiler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pablo on 8/07/18.
 */
//Clase que permite validar un Código fuente a través de Autómatas
public class AnalisisLexico {
    ArrayList sourceCode;
    String[] sourceCodeSeparatedInLines;
    ArrayList <String[]> tokenList,errorList,validTokenList;
    CreadorAutomatas automatas;

    String componenteLexico="";
    /**
     * Constructor que permite procesar un código fuente.
     * Primero quita metacaracteres inutiles (Como salto de línea).
     * Posteriormente separa los símbolos de 1 solo caracter cómo $
     * Finalmente se guardan en una tabla de tokens
     */
    public AnalisisLexico(String preSourceCode){
        sourceCode=new ArrayList();
        tokenList=new ArrayList();
        errorList=new ArrayList();
        validTokenList=new ArrayList();

        //Mecanismo que asigna números de línea
        //Se captura cada linea de código y se separan los pretokens
        sourceCodeSeparatedInLines=preSourceCode.split("\n");
        for(int i=0;i<sourceCodeSeparatedInLines.length;i++){
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\{"," \\{ ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\}"," \\} ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\+"," \\+ ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll(";"," ; ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll(","," , ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\*"," \\* ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\/"," \\/ ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\$"," \\$ ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\("," \\( ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\)"," \\) ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\&"," \\& ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\|"," \\| ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\!"," \\! ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\=" ,"\\= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\= \\=|\\=  \\=" ,"==");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\< \\=|\\<  \\=" ,"<=");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\> \\=|\\>  \\=" ,">=");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\=="," \\== ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\>="," \\<= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\<="," \\<= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\¡="," \\¡= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\>>"," \\>> ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\<<"," \\<< ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\+ \\+ |\\+  \\+","++ ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\; \\; |\\;  \\;",";; ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\+ \\= |\\+  \\=","+= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\; \\= |\\;  \\=",";= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\* \\= |\\*  \\=","*= ");
            sourceCodeSeparatedInLines[i]=sourceCodeSeparatedInLines[i].replaceAll("\\/ \\= |\\/  \\=","/= ");

        }
        //Mecanismo que separa los simbolos para poder procesarlos mediante el método split
        preSourceCode=preSourceCode.replaceAll("\n"," ");
        preSourceCode=preSourceCode.replaceAll("\\{"," \\{ ");
        preSourceCode=preSourceCode.replaceAll("\\}"," \\} ");
        preSourceCode=preSourceCode.replaceAll("\\+"," \\+ ");
        preSourceCode=preSourceCode.replaceAll("\\-"," \\- ");
        preSourceCode=preSourceCode.replaceAll("\\*"," \\* ");
        preSourceCode=preSourceCode.replaceAll("\\/"," \\/ ");
        preSourceCode=preSourceCode.replaceAll("\\$"," \\$ ");
        preSourceCode=preSourceCode.replaceAll("\\("," \\( ");
        preSourceCode=preSourceCode.replaceAll("\\)"," \\) ");
        preSourceCode=preSourceCode.replaceAll("\\&"," \\& ");
        preSourceCode=preSourceCode.replaceAll("\\|"," \\| ");
        preSourceCode=preSourceCode.replaceAll("\\!"," \\! ");
        preSourceCode=preSourceCode.replaceAll("\\=="," \\== ");
        preSourceCode=preSourceCode.replaceAll("\\<="," \\<= ");
        preSourceCode=preSourceCode.replaceAll("\\>="," \\>= ");
        preSourceCode=preSourceCode.replaceAll("\\¡="," \\¡= ");
        preSourceCode=preSourceCode.replaceAll("\\<="," \\<< ");
        preSourceCode=preSourceCode.replaceAll("\\>>"," \\>> ");
        preSourceCode=preSourceCode.replaceAll("\\@"," \\@ ");
        preSourceCode=preSourceCode.replaceAll("\\+ \\+"," ++");

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
     * Método que permite validar cada token individualmente
     * @return boolean si es válido el token
     * obsoleto Ya se usó algo mejor
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
                        componenteLexico=automatas.keyword[i].componenteLexico;
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }
        //Mecanismo que revisa si el token es un simbolo
        pointerIndex=0;
        for(int i=0;i<automatas.symbols.length;i++){
            for(Nodo j=automatas.symbols[i].states[0];j.nextArco.size()!=0;j=j.nextArco.get(0).nextNodo.get(0)){
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
                        componenteLexico=automatas.symbols[i].componenteLexico;
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }
        //Mecanismo que revisa si el token es operador relacional
        pointerIndex=0;
        for(int i=0;i<automatas.opRelational.length;i++){
            for(Nodo j=automatas.opRelational[i].states[0];j.nextArco.size()!=0;j=j.nextArco.get(0).nextNodo.get(0)){
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
                        componenteLexico=automatas.opRelational[i].componenteLexico;
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }
        //Mecanismo que revisa si el token es coma
        if(Pattern.compile(",").matcher(token).matches()){
            componenteLexico="Comma";
            return true;
        }
        //Mecanismo que revisa si el token es incremento
        if(Pattern.compile("\\+\\+").matcher(token).matches()){
            componenteLexico="incremento";
            return true;
        }

        if(Pattern.compile("\\;\\;").matcher(token).matches()){
            componenteLexico="decremento";
            return true;
        }

        if(Pattern.compile("\\+\\=").matcher(token).matches()){
            componenteLexico="Suma acumulada";
            return true;
        }

        if(Pattern.compile("\\;\\=").matcher(token).matches()){
            componenteLexico="Diferencia acumulada";
            return true;
        }

        if(Pattern.compile("\\*\\=").matcher(token).matches()){
            componenteLexico="Producto acumulado";
            return true;
        }

        if(Pattern.compile("\\/\\=").matcher(token).matches()){
            componenteLexico="Division acumulada";
            return true;
        }
        //Mecanismo que revisa si el token es operador aritmetico
        pointerIndex=0;
        for(int i=0;i<automatas.opArithmetic.length;i++){
            for(Nodo j=automatas.opArithmetic[i].states[0];j.nextArco.size()!=0;j=j.nextArco.get(0).nextNodo.get(0)){
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
                        componenteLexico=automatas.opArithmetic[i].componenteLexico;
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }
        //Mecanismo que revisa si el token es un operador logico
        pointerIndex=0;
        for(int i=0;i<automatas.opLogic.length;i++){
            for(Nodo j=automatas.opLogic[i].states[0];j.nextArco.size()!=0;j=j.nextArco.get(0).nextNodo.get(0)){
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
                        componenteLexico=automatas.opLogic[i].componenteLexico;
                        return isValid;
                    }
                    pointerIndex++;
                    pointer=token.substring(pointerIndex,pointerIndex+1);
                }
            }
        }

        //Mecanismo que revisa si el token es un identificador
        pointerIndex=0;
        outer:
        for(Nodo i=automatas.identifier.states[0];pointerIndex<token.length();){
            for(int k=0;k<i.nextArco.size();k++){
                pointer=token.substring(pointerIndex,pointerIndex+1);
                isValid=pointer.equals(i.nextArco.get(k).letter);
                if(!isValid){
                    if(pointerIndex==0 || k==i.nextArco.size()-1){
                        i=automatas.identifier.states[0];
                        isValid=false;
                        break outer;
                    }
                }else{
                    pointerIndex++;
                    i=i.nextArco.get(k).nextNodo.get(0);
                    isValid=true;
                    break;
                }
            }
        }
        if(isValid){
            componenteLexico=automatas.identifier.componenteLexico  ;
            return true;
        }

        //Mecanismo que revisa si el token es un numero
        pointerIndex=0;
        Nodo i=automatas.number.states[0];
        for(;pointerIndex<token.length();){
            for(int k=0;k<i.nextArco.size();k++){
                pointer=token.substring(pointerIndex,pointerIndex+1);
                isValid=pointer.equals(i.nextArco.get(k).letter);
                if(isValid){
                    pointerIndex++;
                    i=i.nextArco.get(k).nextNodo.get(0);
                    break;
                }
            }
            if(!isValid){
                break;
            }
        }
        if(i.isFinalState){
            componenteLexico=automatas.number.componenteLexico;
            return true;
        }

        //Mecanismo que revisa si el token es cadena
        pointerIndex=0;
        i=automatas.string.states[0];
        for(;pointerIndex<token.length();){
            for(int k=0;k<i.nextArco.size();k++){
                pointer=token.substring(pointerIndex,pointerIndex+1);
                isValid=pointer.equals(i.nextArco.get(k).letter);
                if(isValid){
                    pointerIndex++;
                    if(k==62){
                        i=i.nextArco.get(k).nextNodo.get(1);
                        break;
                    }else{
                        i=i.nextArco.get(k).nextNodo.get(0);
                        break;
                    }

                }
            }
            if(!isValid){
                break;
            }
        }
        if(i.isFinalState){
            componenteLexico=automatas.string.componenteLexico;
            isValid=true;
        }

        return isValid;
    }
    /**
     * Método que permite obtener la lista de tokens
     * @return tokens
     */
    public String printValidTokens(){
        String tokenInfo="";
        for(int i=0;i<tokenList.size();i++){
            tokenInfo+="Token: "+tokenList.get(i)[0]+"\n";
            tokenInfo+="No linea: "+tokenList.get(i)[1]+"\n";
            tokenInfo+= tokenList.get(i)[2]+"\n";
            tokenInfo+="C. Lexico: "+tokenList.get(i)[3]+"\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }
    /**
     * Método que permite obtener la lista de tokens de error
     * @return tokens
     */
    public String createErrorList(){
        String tokenInfo="";
        for(int i=0;i<tokenList.size();i++){
            if(!tokenList.get(i)[2].equals("Valido")){
                errorList.add(tokenList.get(i));
            }
        }
        for(int i=0;i<errorList.size();i++){
            tokenInfo+="Token: "+errorList.get(i)[0]+"\n";
            tokenInfo+="No linea: "+errorList.get(i)[1]+"\n";
            tokenInfo+="Error Léxico id: EL000001\n";
            tokenInfo+="Secuencia de caracteres no válida\n";
            tokenInfo+="Solución: Reescribir secuencia\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }
    /**
     * Método que permite obtener la lista de tokens de error
     * @return tokens
     */
    public String createValidTokenList(){
        String tokenInfo="";
        for(int i=0;i<tokenList.size();i++){
            if(tokenList.get(i)[2].equals("Valido")){
                validTokenList.add(tokenList.get(i));
            }
        }
        for(int i=0;i<validTokenList.size();i++){
            tokenInfo+="Token: "+validTokenList.get(i)[0]+"\n";
            tokenInfo+="No linea: "+validTokenList.get(i)[1]+"\n";
            tokenInfo+="___________________________"+"\n";
        }
        return tokenInfo;
    }
    /**
     * Método que permite Generar tokens sin asignar validad
     * @return nada
     */
    public void generateTokens(){
        String tokenGenerator="",elementTokenPointer="";
        String[] tokenContent;
        boolean firstLetterFound=false;

        for(int i=0;i<sourceCodeSeparatedInLines.length;i++){
            sourceCodeSeparatedInLines[i]+=" ";
        }

        for(int i=0;i<sourceCodeSeparatedInLines.length;i++){
            for(int j=0;j<sourceCodeSeparatedInLines[i].length();j++){
                elementTokenPointer=sourceCodeSeparatedInLines[i].substring(j,j+1);
                if(!elementTokenPointer.equals(" ")){
                    firstLetterFound=true;
                    tokenGenerator+=elementTokenPointer;
                }else{
                    if(firstLetterFound){
                        tokenContent=(tokenGenerator+"-"+(i+1)+"-ND-SC").split("-");
                        if(validateToken(tokenGenerator)){
                            tokenContent[2]="Valido";
                            tokenContent[3]=componenteLexico;
                        } else{
                            tokenContent[2]="Error Léxico: Símbolo no válido";
                            tokenContent[3]="id: EL000001";
                        }
                        firstLetterFound=false;
                        tokenList.add(tokenContent);
                        tokenGenerator="";
                    }
                }
            }
        }
    }
}
