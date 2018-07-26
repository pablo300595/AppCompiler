package com.example.pablo.appcompiler;
import java.util.ArrayList;

public class CodigoIntermedio{
    //ArrayList de todas las llamadas
    ArrayList<ArrayList<String []>> calls;
    //ArrayList de las sentencias de las task
    ArrayList<String[]> sentencias;
    //ArrayList para guardar los temporales
    ArrayList<String[][]> temporales;

    //Arrays con ejemplos de lo que se recibirá de la fase de análisis
    String sentencia[]= {"3", "int _a$", "inicialización"};
    String sentencia1[]= {"3", "int _b$", "inicialización"};
    String sentencia2[]= {"8", "_a==9$", "asignación"};
    /*String sentencia3[]= {"8", "for(int _j==0;_j<10;_j++){\n" +
            "        _a== 5$\n"+
            "        _b== 2$\n"+
            "    }", "estructuraFor"};
    String sentencia4[]= {"8", "if(_a==_b){\n" +
            "        for(int _j==0;_j<10;_j++){\n" +
            "           _a== 5$\n"+
            "           _b== 2$\n"+
            "        }\n"+
            "        for(int _h==0;_h<10;_h++){\n" +
            "           _a== 15$\n"+
            "        }\n"+
            "    }", "estructuraIf"};
    String sentencia5[]= {"8", "_a++$", "incremento"};
    String sentencia6[]= {"8", "_b;;$", "decremento"};
    String sentencia7[]= {"8", "_b+=25$", "suma con asignación"};
    String sentencia8[]= {"8", "_a;=25$", "resta con asignación"};
    String sentencia9[]= {"8", "_b*=25$", "multiplicación con asignación"};
    String sentencia10[]= {"8", "_a/=25$", "división con asignación"};
    String sentencia11[]= {"8", "servo(_b,_a)$", "estructuraServo"};
    String sentencia12[]= {"8", "solenoid(_b,_a)$", "estructuraSolenoid"};
    String sentencia13[]= {"8", "light(_b,_a)$", "estructuraLight"};
    String sentencia14[]= {"8", "display(_b,_a,8)$", "estructuraDisplay"};
    String sentencia15[]= {"8", "sensor()$", "estructuraSensor"};*/

    //Cadena para mostrar la conversión de código fuente a intermedio
    String codigoIntermedio;

    //Cadenas para los nombres de temporales de id´s y palabras reservadas
    String tempId;
    String tempKeyWord;

    //Contadores para los nombres de los temporales
    int contTempId;
    int contTempKeyWord;
    int contTempServo;
    int contTempSolenoid;
    int contTempSensor;
    int contTempLight;
    int contTempDisplay;

    public CodigoIntermedio(){
        calls= new ArrayList<>();
        sentencias= new ArrayList<>();
        temporales= new ArrayList<>();

        //Agregar los array que contienen la información de las sentencias al ArrayList de sentencias
        sentencias.add(sentencia);
        sentencias.add(sentencia1);
        sentencias.add(sentencia2);
        //sentencias.add(sentencia3);
        //sentencias.add(sentencia4);
        /*sentencias.add(sentencia5);
        sentencias.add(sentencia6);
        sentencias.add(sentencia7);
        sentencias.add(sentencia8);
        sentencias.add(sentencia9);
        sentencias.add(sentencia10);
        sentencias.add(sentencia11);
        sentencias.add(sentencia12);
        sentencias.add(sentencia13);
        sentencias.add(sentencia14);
        sentencias.add(sentencia15);*/

        //Agregar todos los ArrayList de sentencias al ArrayList de llamadas
        calls.add(sentencias);

        codigoIntermedio= "";

        tempId= "t";
        tempKeyWord= "";

        contTempId= 0;
        contTempServo= 0;
        contTempSensor= 0;
        contTempSolenoid= 0;
        contTempLight= 0;
        contTempDisplay= 0;
    }

    public void generarCodigo(){
        //Cadena que obtendrá el tipo de sentencia del ArrayList de sentencias
        String tipoSentencia;
        //Cadena que obtendrá la sentencia en sí
        String sentencia;
        //for para recorrer todas las llamadas
        for(int i= 0;i<calls.size();i++){
            //for para recorrer todas las sentencias
            for(int j= 0;j<sentencias.size();j++){
                tipoSentencia= sentencias.get(j)[2];
                sentencia= sentencias.get(j)[1];
                //switch para tomar diferentes acciones dependiendo del tipo de sentencia
                switch(tipoSentencia){
                    case "inicialización":
                        casoInicializacion(sentencia);
                        break;
                    case "asignación":
                        casoAsignacion(sentencia);
                        break;
                    case "estructuraFor":
                        casoFor(sentencia);
                        break;
                    case "estructuraIf":
                        int index4= sentencia.indexOf("(");
                        int index5= sentencia.indexOf("=");
                        int index6= sentencia.indexOf(")");
                        String aux= sentencia.substring(index4+1, index5);
                        String aux3= sentencia.substring(index5+2, index6);

                        String aux7= sentencia.substring(0, index6+2);
                        buscarTempPorId(aux7, aux, aux3);
                        int index7= sentencia.indexOf("{")+1;
                        //Se separa cada sentencia dentro del for dentro de un arreglo
                        String aux8= sentencia.substring(index7, sentencia.lastIndexOf("\n"));

                        String arr1[]= aux8.split("\n");

                        ArrayList<String> arrayList= new ArrayList<>();
                        String cad= "";
                        boolean flag= false;
                        for(int h= 0;h<arr1.length;h++){
                            if(arr1[h].contains("for")){
                                cad+= arr1[h]+"\n";
                                flag= true;
                                continue;
                            }
                            if(flag){
                                cad+= "    "+arr1[h]+"\n";
                            }
                            if(arr1[h].contains("}")){
                                arrayList.add(cad);
                                cad= "";
                            }
                        }
                        int cont= 0;
                        //Se pasa cada sentencia del for al método casoAsignacion
                        boolean flag1= false;
                        for(int k= 1;k<arr1.length;k++){
                            if(arr1[k].contains("}")){
                                casoFor(arrayList.get(cont));
                                cont++;
                                flag1= false;
                                continue;
                            }
                            if(flag1){
                                continue;
                            }
                            if(arr1[k].contains("for")){
                                flag1= true;
                                continue;
                            }else if(arr1[k].contains("==")){
                                casoAsignacion(arr1[k]);
                            }
                        }
                        codigoIntermedio+= "}\n";
                        break;
                    case "estructuraSensor":
                        codigoIntermedio+= "sensor();";
                        break;
                    case "estructuraDisplay":
                        palabrasReservadas(sentencia, "display", "dp");
                        break;
                    case "estructuraSolenoid":
                        palabrasReservadas(sentencia, "solenoid", "sn");
                        break;
                    case "estructuraServo":
                        palabrasReservadas(sentencia, "servo", "sv");
                        break;
                    case "estructuraLight":
                        palabrasReservadas(sentencia, "light", "lg");
                        break;
                    case "incremento":
                        incrementoDecremento(sentencia, "+");
                        break;
                    case "decremento":
                        incrementoDecremento(sentencia, "-");
                        break;
                    case "suma con asignación":
                        opAritConAsignacion(sentencia, "+");
                        break;
                    case "resta con asignación":
                        opAritConAsignacion(sentencia, ";");
                        break;
                    case "multiplicación con asignación":
                        opAritConAsignacion(sentencia, "*");
                        break;
                    case "división con asignación":
                        opAritConAsignacion(sentencia, "/");
                        break;
                }
            }
        }
    }

    public void casoInicializacion(String sentencia){
        if(sentencia.contains("==")){
            String auxTemp= sentencia.replace(" ", "#");
            int indexTemp= auxTemp.indexOf("#");
            int indexTemp1= auxTemp.indexOf("=");
            //Obtener con substring únicamente el nombre de id
            auxTemp= auxTemp.substring(indexTemp+1, indexTemp1);
            //Agregar el nombre de id al ArrayList de temporales junto con su nombre de temporal
            temporales.add(new String[][]{{auxTemp, tempId+contTempId}});

            String aux= sentencia.replace("==", "=");
            int index= aux.indexOf("=");
            //Agregar a la cadena de código intermedio la conversión de la sentencia
            String aux1= tempId+contTempId+aux.substring(index)+"\n";
            aux1= aux1.replace("$", ";");
            codigoIntermedio+= aux1;
            contTempId++;
        }
    }

    public void casoInicializacion(String sentencia, String cabeceraFor){
        if(sentencia.contains("==")){
            //Lo mismo que el método anterior
            String auxTemp= sentencia.replace(" ", "#");
            int indexTemp= auxTemp.indexOf("#");
            int indexTemp1= auxTemp.indexOf("=");
            auxTemp= auxTemp.substring(indexTemp+1, indexTemp1);
            temporales.add(new String[][]{{auxTemp, tempId+contTempId}});
            //Se reemplaza el nombre del id por su nombre de temporal dentro de la cabecera del for
            cabeceraFor= cabeceraFor.replaceAll(auxTemp, tempId+contTempId);
            cabeceraFor= cabeceraFor.replace("==", "=");
            cabeceraFor= cabeceraFor.replace("int ", "");
            String arr[]= cabeceraFor.split(";");
            for(int n= 0;n<arr.length-1;n++){
                //Se agrega cabecera de for convertida a código intermedio
                codigoIntermedio+= arr[n]+";";
            }

            String aux= "";
            for(int i= 0;i<temporales.size();i++){
                if(arr[2].substring(0, arr[2].length()-4).equals(temporales.get(i)[0][1])){
                    aux= temporales.get(i)[0][0];
                }
            }
            incrementoDecremento(aux+"++$", "+f");
            codigoIntermedio+= "){\n";
            contTempId++;
        }
    }

    public String casoAsignacion(String sentencia){
        //Se obtiene únicamente el nombre del id dentro de la sentencia
        int index= sentencia.indexOf("=");
        int i= 0;
        while(sentencia.charAt(i)==' '){
            i++;
        }
        //javax.swing.JOptionPane.showMessageDialog(null, sentencia);
        String aux= sentencia.substring(i, index);
        //Se recorre el ArrayList de temporales para encontrar el nombre de temporal de ese id
        String espacios= "";
        for(int k= 0;k<temporales.size();k++){
            if(aux.equals(temporales.get(k)[0][0])){
                String aux1= temporales.get(k)[0][1]+sentencia.substring(index+1)+"\n";
                aux1= aux1.replace("$", ";");
                if(i!=0){
                    for(int h= 0;h<i-4;h++){
                        espacios+= " ";
                    }
                    aux1= espacios+aux1;
                }
                codigoIntermedio+= aux1;
            }
        }
        return espacios;
    }

    public void casoFor(String sentencia){
        //Se obtiene únicamente el nombre del id dentro de la sentencia

        int index1= sentencia.indexOf("(");
        int index2= sentencia.indexOf(";");
        String aux1= sentencia.substring(index1+1, index2);

        int index3= sentencia.indexOf("{")+1;

        casoInicializacion(aux1, sentencia.substring(0, index3));
        //Se separa cada sentencia dentro del for dentro de un arreglo
        String aux2= sentencia.substring(index3, sentencia.lastIndexOf("\n"));

        String arr[]= aux2.split("\n");
        String espacios= "";
        //Se pasa cada sentencia del for al método casoAsignacion
        for(int k= 1;k<arr.length;k++){
            if(arr[k].contains("}")){
                continue;
            }
            espacios= casoAsignacion(arr[k]);
        }
        codigoIntermedio+= espacios.substring(0, espacios.length()-4)+"}\n";
    }

    public void incrementoDecremento(String sentencia, String signo){
        String aux9= sentencia.substring(0, sentencia.length()-3);
        for(int k= 0;k<temporales.size();k++){
            if(aux9.equals(temporales.get(k)[0][0])){
                aux9= temporales.get(k)[0][1];
                if(signo.contains("f")){
                    aux9= aux9+"= "+aux9+signo.substring(0, signo.length()-1)+"1";
                }else{
                    aux9= aux9+"= "+aux9+signo+"1;\n";
                }
            }
        }
        codigoIntermedio+= aux9;
    }

    public void opAritConAsignacion(String sentencia, String signo){
        int index8= sentencia.indexOf(signo);
        String aux11= sentencia.substring(0, index8);
        String aux12= sentencia.substring(index8+2, sentencia.length()-1);
        if(signo.equals(";")){
            signo= "-";
        }
        sentencia= aux11+"= "+aux11+signo+aux12+";";
        buscarTempPorId(sentencia, aux11, aux12);
    }

    public void palabrasReservadas(String sentencia, String keyWord, String temp){
        sentencia= sentencia.replace(keyWord, temp);

        int index= sentencia.indexOf("(");
        int index1= sentencia.indexOf(",");
        int index2= sentencia.indexOf(")");

        String aux= sentencia.substring(index+1, index1);
        String aux1= sentencia.substring(index1+1, index2);

        if(keyWord.equals("display")){
            int index3= sentencia.lastIndexOf(",");
            String aux2= sentencia.substring(index1+1, index3);
            buscarTempPorId(sentencia.substring(0, sentencia.length()-1)+";", aux, aux2, aux1);
        }else{
            buscarTempPorId(sentencia.substring(0, sentencia.length()-1)+";", aux, aux1);
        }
    }

    public void buscarTempPorId(String sentencia, String id, String id1){
        for(int k= 0;k<temporales.size();k++){
            if(id.equals(temporales.get(k)[0][0]) || id1.equals(temporales.get(k)[0][0])){
                if(id.equals(temporales.get(k)[0][0])){
                    sentencia= sentencia.replace(id, temporales.get(k)[0][1]);
                }
                if(id1.equals(temporales.get(k)[0][0])){
                    sentencia= sentencia.replace(id1, temporales.get(k)[0][1]);
                }
            }
        }
        codigoIntermedio+= sentencia+"\n";
    }

    public void buscarTempPorId(String sentencia, String id, String id1, String id2){
        for(int k= 0;k<temporales.size();k++){
            if(id.equals(temporales.get(k)[0][0]) || id1.equals(temporales.get(k)[0][0])){
                if(id.equals(temporales.get(k)[0][0])){
                    sentencia= sentencia.replace(id, temporales.get(k)[0][1]);
                }
                if(id1.equals(temporales.get(k)[0][0])){
                    sentencia= sentencia.replace(id1, temporales.get(k)[0][1]);
                }
                if(id2.equals(temporales.get(k)[0][0])){
                    sentencia= sentencia.replace(id1, temporales.get(k)[0][1]);
                }
            }
        }
        codigoIntermedio+= sentencia+"\n";
    }

    public static void main(String arg[]){
        CodigoIntermedio c= new CodigoIntermedio();
        c.generarCodigo();
        System.out.println(c.codigoIntermedio);
    }
}
