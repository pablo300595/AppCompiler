package com.example.pablo.appcompiler;

/**
 * Created by pablo on 26/07/18.
 */

public class DataValidator {
    final String component;

    public DataValidator(){
        component="task _rutinaUno params none{\n" +
                "\tint _tiempoMovimiento==20$\n" +
                "\tint _mostrarTemperatura==1$\n" +
                "\tfloat _centigrados$\n" +
                "\tint _foco1==0$\n" +
                "\tint _foco2==0$\n" +
                "\tint _servo1==0$\n" +
                "\tint _servo2==0$\n" +
                "\tint _solenoide==0$\n" +
                "\tint _gradosMinimo1==65$\n" +
                "\tint _gradosMaximo1==135$\n" +
                "\tint _gradosMinimo2==65$\n" +
                "\tint _gradosMaximo2==135$\n" +
                "\n" +
                "\tif(_mostrarTemperatura==1){\n" +
                "\t\t_centigrados==leerTemperatura()$\n" +
                "\t\tdisplay(0,0,'INCUBAMEX 2.0')$\n" +
                "\t\tdisplay(0,1,_centigrados)$\n" +
                "\t\tdisplay(6,1,'Grados')$\n" +
                "\t\tdelay(1000)$\n" +
                "\t}\n" +
                "        \n" +
                "\tif(_servo1==1){\n" +
                "\t\tfor(int _pos==_gradosMinimo1$_pos<=_gradosMaximo1$_pos++){  \n" +
                "\t\t\tservo(13,_pos)$\n" +
                "\t\t\tdelay(_tiempoMovimiento)$                       \n" +
                "\t\t} \n" +
                "\t\tfor (int _pos1==_gradosMaximo1$_pos1>=_gradosMinimo1$_pos1;;){\n" +
                "\t\t\tservo(13,_pos1)$\n" +
                "\t\t\tdelay(_tiempoMovimiento)$                       \n" +
                "\t\t}\n" +
                "\t}\n" +
                "        \n" +
                "\tif(_servo2==1){\n" +
                "\t\tfor(int _pos2==_gradosMinimo2$_pos2<=_gradosMaximo2$_pos2;;){  \n" +
                "\t\t\tservo(7,_pos2)$\n" +
                "            delay(_tiempoMovimiento)$                       \n" +
                "\t\t} \n" +
                "        for(int _pos3==_gradosMaximo2$_pos3>=_gradosMinimo2$_pos3;;){\n" +
                "\t\t\tservo(7,_pos3)$\n" +
                "            delay(_tiempoMovimiento);                       \n" +
                "\t\t}\n" +
                "\t}\n" +
                "\n" +
                "\tif(_foco1==1){\n" +
                "\t\tlight(8,1);\n" +
                "\t}\n" +
                "          \n" +
                "\tif(_foco2==1){\n" +
                "\t\tlight(9,1);\n" +
                "\t}\n" +
                "\n" +
                "\tif(_solenoide==1){\n" +
                "\t\tsolenoid(10,1);\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "call _rutinaUno";
    }
}
