package com.example.pablo.appcompiler;

import android.provider.FontRequest;

/**
 * Created by pablo on 5/07/18.
 */

public class CreadorAutomatas {
    static Automata keyword[];
    static Automata opAsignation[];
    static Automata opLogic[];
    static Automata opRelational[];
    static Automata opArithmetic[];
    static Automata identifier;
    static Automata symbols[];
    static Automata digits[];
    static Automata AB;
    //KEYWORDS
    static String expTask="q0,q1,q2,q3,q4 sql-t,a,s,k";
    static String expMetadata= "q0,q1,q2,q3,q4,q5,q6,q7,q8 sql-m,e,t,a,d,a,t,a";
    static String expServo= "q0,q1,q2,q3,q4,q5 sql-s,e,r,v,o";
    static String expLight="q0,q1,q2,q3,q4,q5 sql-l,i,g,h,t";
    static String expSolenoid="q0,q1,q2,q3,q4,q5,q6,q7,q8 sql-s,o,l,e,n,o,i,d";
    static String expDisplay="q0,q1,q2,q3,q4,q5,q6,q7 sql-d,i,s,p,l,a,y";
    static String expSensor="q0,q1,q2,q3,q4,q5,q6 sql-s,e,n,s,o,r";
    static String expWhile="q0,q1,q2,q3,q4,q5 sql-w,h,i,l,e";
    static String expFor="q0,q1,q2,q3 sql-f,o,r";
    static String expIf="q0,q1,q2 sql-i,f";
    static String expNone="q0,q1,q2,q3,q4 sql-n,o,n,e";
    static String expParams="q0,q1,q2,q3,q4,q5,q6 sql-p,a,r,a,m,s";
    static String expMaincode="q0,q1,q2,q3,q4,q5,q6,q7,q8 sql-m,a,i,n,c,o,d,e";
    static String expInt="q0,q1,q2,q3 sql-i,n,t";
    static String expFloat="q0,q1,q2,q3,q4,q5 sql-f,l,o,a,t";
    static String expText="q0,q1,q2,q3,q4 sql-t,e,x,t";

    static String expId="q0,q1 sql-_,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z" +
            ",A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9";


    static String expNot="q0,q1 sql-!";
    static String expOr="q0,q1,q2 sql-|,|";
    static String expAnd="q0,q1,q2 sql-&,&";

    static String expLessThan="q0,q1 sql-<";
    static String expLessEqualThan="q0,q1,q2 sql-<,=";
    static String expMoreThan="q0,q1 sql->";
    static String expMoreEqualThan="q0,q1,q2 sql->,=";
    static String expEqual="q0,q1,q2 sql-=,=";
    static String expDifferent="q0,q1,q2 sql-!,=";

    static String expDollar="q0,q1 sql-$";
    static String expOpenBrace="q0,q1 sql-{";
    static String expClosedBrace="q0,q1 sql-}";
    static String expOpenParenthesis="q0,q1 sql-(";
    static String expClosedParenthesis="q0,q1 sql-)";

    static String exp0="q0,q1 sql-0";
    static String exp1="q0,q1 sql-1";
    static String exp2="q0,q1 sql-2";
    static String exp3="q0,q1 sql-3";
    static String exp4="q0,q1 sql-4";
    static String exp5="q0,q1 sql-5";
    static String exp6="q0,q1 sql-6";
    static String exp7="q0,q1 sql-7";
    static String exp8="q0,q1 sql-8";
    static String exp9="q0,q1 sql-9";

    static String expAB="q0,q1 sql,q2 sql,q3 sql-a,b";

    public CreadorAutomatas(){
        keyword=new Automata[16];
        opLogic=new Automata[3];
        symbols=new Automata[5];
        digits=new Automata[10];
        createAllAutomatas();
    }

    public void createAllAutomatas(){
        //KEYWORD task
        keyword[0]=new Automata(expTask);
        keyword[0].initNode=keyword[0].states[0];
        keyword[0].componenteLexico="Palabra reservada";
        keyword[0].linkNode(keyword[0].states[0], keyword[0].transitions[0], keyword[0].states[1]);
        keyword[0].linkNode(keyword[0].states[1], keyword[0].transitions[1], keyword[0].states[2]);
        keyword[0].linkNode(keyword[0].states[2], keyword[0].transitions[2], keyword[0].states[3]);
        keyword[0].linkNode(keyword[0].states[3], keyword[0].transitions[3], keyword[0].states[4]);

        //KEYWORD metadata
        keyword[1]=new Automata(expMetadata);
        keyword[1].initNode=keyword[1].states[0];
        keyword[1].componenteLexico="Palabra reservada";
        keyword[1].linkNode(keyword[1].states[0], keyword[1].transitions[0], keyword[1].states[1]);
        keyword[1].linkNode(keyword[1].states[1], keyword[1].transitions[1], keyword[1].states[2]);
        keyword[1].linkNode(keyword[1].states[2], keyword[1].transitions[2], keyword[1].states[3]);
        keyword[1].linkNode(keyword[1].states[3], keyword[1].transitions[3], keyword[1].states[4]);
        keyword[1].linkNode(keyword[1].states[4], keyword[1].transitions[4], keyword[1].states[5]);
        keyword[1].linkNode(keyword[1].states[5], keyword[1].transitions[5], keyword[1].states[6]);
        keyword[1].linkNode(keyword[1].states[6], keyword[1].transitions[6], keyword[1].states[7]);
        keyword[1].linkNode(keyword[1].states[7], keyword[1].transitions[7], keyword[1].states[8]);

        //KEYWORD servo
        keyword[2]=new Automata(expServo);
        keyword[2].initNode=keyword[2].states[0];
        keyword[2].componenteLexico="Palabra reservada";
        keyword[2].linkNode(keyword[2].states[0], keyword[2].transitions[0], keyword[2].states[1]);
        keyword[2].linkNode(keyword[2].states[1], keyword[2].transitions[1], keyword[2].states[2]);
        keyword[2].linkNode(keyword[2].states[2], keyword[2].transitions[2], keyword[2].states[3]);
        keyword[2].linkNode(keyword[2].states[3], keyword[2].transitions[3], keyword[2].states[4]);
        keyword[2].linkNode(keyword[2].states[4], keyword[2].transitions[4], keyword[2].states[5]);

        //KEYWORD lightpointerIndex++;
        keyword[3]=new Automata(expLight);
        keyword[3].initNode=keyword[3].states[0];
        keyword[3].componenteLexico="Palabra reservada";
        keyword[3].linkNode(keyword[3].states[0], keyword[3].transitions[0], keyword[3].states[1]);
        keyword[3].linkNode(keyword[3].states[1], keyword[3].transitions[1], keyword[3].states[2]);
        keyword[3].linkNode(keyword[3].states[2], keyword[3].transitions[2], keyword[3].states[3]);
        keyword[3].linkNode(keyword[3].states[3], keyword[3].transitions[3], keyword[3].states[4]);
        keyword[3].linkNode(keyword[3].states[4], keyword[3].transitions[4], keyword[3].states[5]);

        //KEYWORD solenoid
        keyword[4]=new Automata(expSolenoid);
        keyword[4].initNode=keyword[4].states[0];
        keyword[4].componenteLexico="Palabra reservada";
        keyword[4].linkNode(keyword[4].states[0], keyword[4].transitions[0], keyword[4].states[1]);
        keyword[4].linkNode(keyword[4].states[1], keyword[4].transitions[1], keyword[4].states[2]);
        keyword[4].linkNode(keyword[4].states[2], keyword[4].transitions[2], keyword[4].states[3]);
        keyword[4].linkNode(keyword[4].states[3], keyword[4].transitions[3], keyword[4].states[4]);
        keyword[4].linkNode(keyword[4].states[4], keyword[4].transitions[4], keyword[4].states[5]);
        keyword[4].linkNode(keyword[4].states[5], keyword[4].transitions[5], keyword[4].states[6]);
        keyword[4].linkNode(keyword[4].states[6], keyword[4].transitions[6], keyword[4].states[7]);
        keyword[4].linkNode(keyword[4].states[7], keyword[4].transitions[7], keyword[4].states[8]);

        //KEYWORD display
        keyword[5]=new Automata(expDisplay);
        keyword[5].initNode=keyword[5].states[0];
        keyword[5].componenteLexico="Palabra reservada";
        keyword[5].linkNode(keyword[5].states[0], keyword[5].transitions[0], keyword[5].states[1]);
        keyword[5].linkNode(keyword[5].states[1], keyword[5].transitions[1], keyword[5].states[2]);
        keyword[5].linkNode(keyword[5].states[2], keyword[5].transitions[2], keyword[5].states[3]);
        keyword[5].linkNode(keyword[5].states[3], keyword[5].transitions[3], keyword[5].states[4]);
        keyword[5].linkNode(keyword[5].states[4], keyword[5].transitions[4], keyword[5].states[5]);
        keyword[5].linkNode(keyword[5].states[5], keyword[5].transitions[5], keyword[5].states[6]);
        keyword[5].linkNode(keyword[5].states[6], keyword[5].transitions[6], keyword[5].states[7]);

        //KEYWORD sensor
        keyword[6]=new Automata(expSensor);
        keyword[6].initNode=keyword[6].states[0];
        keyword[6].componenteLexico="Palabra reservada";
        keyword[6].linkNode(keyword[6].states[0], keyword[6].transitions[0], keyword[6].states[1]);
        keyword[6].linkNode(keyword[6].states[1], keyword[6].transitions[1], keyword[6].states[2]);
        keyword[6].linkNode(keyword[6].states[2], keyword[6].transitions[2], keyword[6].states[3]);
        keyword[6].linkNode(keyword[6].states[3], keyword[6].transitions[3], keyword[6].states[4]);
        keyword[6].linkNode(keyword[6].states[4], keyword[6].transitions[4], keyword[6].states[5]);
        keyword[6].linkNode(keyword[6].states[5], keyword[6].transitions[5], keyword[6].states[6]);

        //KEYWORD while
        keyword[7]=new Automata(expWhile);
        keyword[7].initNode=keyword[7].states[0];
        keyword[7].componenteLexico="Palabra reservada";
        keyword[7].linkNode(keyword[7].states[0], keyword[7].transitions[0], keyword[7].states[1]);
        keyword[7].linkNode(keyword[7].states[1], keyword[7].transitions[1], keyword[7].states[2]);
        keyword[7].linkNode(keyword[7].states[2], keyword[7].transitions[2], keyword[7].states[3]);
        keyword[7].linkNode(keyword[7].states[3], keyword[7].transitions[3], keyword[7].states[4]);
        keyword[7].linkNode(keyword[7].states[4], keyword[7].transitions[4], keyword[7].states[5]);

        //KEYWORD for
        keyword[8]=new Automata(expFor);
        keyword[8].initNode=keyword[8].states[0];
        keyword[8].componenteLexico="Palabra reservada";
        keyword[8].linkNode(keyword[8].states[0], keyword[8].transitions[0], keyword[8].states[1]);
        keyword[8].linkNode(keyword[8].states[1], keyword[8].transitions[1], keyword[8].states[2]);
        keyword[8].linkNode(keyword[8].states[2], keyword[8].transitions[2], keyword[8].states[3]);

        //KEYWORD if
        keyword[9]=new Automata(expIf);
        keyword[9].initNode=keyword[9].states[0];
        keyword[9].componenteLexico="Palabra reservada";
        keyword[9].linkNode(keyword[9].states[0], keyword[9].transitions[0], keyword[9].states[1]);
        keyword[9].linkNode(keyword[9].states[0], keyword[9].transitions[0], keyword[9].states[2]);

        //KEYWORD none
        keyword[10]=new Automata(expNone);
        keyword[10].initNode=keyword[10].states[0];
        keyword[10].componenteLexico="Palabra reservada";
        keyword[10].linkNode(keyword[10].states[0], keyword[10].transitions[0], keyword[10].states[1]);
        keyword[10].linkNode(keyword[10].states[1], keyword[10].transitions[1], keyword[10].states[2]);
        keyword[10].linkNode(keyword[10].states[2], keyword[10].transitions[2], keyword[10].states[3]);
        keyword[10].linkNode(keyword[10].states[3], keyword[10].transitions[3], keyword[10].states[4]);

        //KEYWORD params
        keyword[11]=new Automata(expParams);
        keyword[11].initNode=keyword[11].states[0];
        keyword[11].componenteLexico="Palabra reservada";
        keyword[11].linkNode(keyword[11].states[0], keyword[11].transitions[0], keyword[11].states[1]);
        keyword[11].linkNode(keyword[11].states[1], keyword[11].transitions[1], keyword[11].states[2]);
        keyword[11].linkNode(keyword[11].states[2], keyword[11].transitions[2], keyword[11].states[3]);
        keyword[11].linkNode(keyword[11].states[3], keyword[11].transitions[3], keyword[11].states[4]);
        keyword[11].linkNode(keyword[11].states[4], keyword[11].transitions[4], keyword[11].states[5]);
        keyword[11].linkNode(keyword[11].states[5], keyword[11].transitions[5], keyword[11].states[6]);


        //KEYWORD maincode
        keyword[12]=new Automata(expMaincode);
        keyword[12].initNode=keyword[12].states[0];
        keyword[12].componenteLexico="Palabra reservada";
        keyword[12].linkNode(keyword[12].states[0], keyword[12].transitions[0], keyword[12].states[1]);
        keyword[12].linkNode(keyword[12].states[1], keyword[12].transitions[1], keyword[12].states[2]);
        keyword[12].linkNode(keyword[12].states[2], keyword[12].transitions[2], keyword[12].states[3]);
        keyword[12].linkNode(keyword[12].states[3], keyword[12].transitions[3], keyword[12].states[4]);
        keyword[12].linkNode(keyword[12].states[4], keyword[12].transitions[4], keyword[12].states[5]);
        keyword[12].linkNode(keyword[12].states[5], keyword[12].transitions[5], keyword[12].states[6]);
        keyword[12].linkNode(keyword[12].states[6], keyword[12].transitions[6], keyword[12].states[7]);
        keyword[12].linkNode(keyword[12].states[7], keyword[12].transitions[7], keyword[12].states[8]);


        //KEYWORD int
        keyword[13]=new Automata(expInt);
        keyword[13].initNode=keyword[13].states[0];
        keyword[13].componenteLexico="Palabra reservada";
        keyword[13].linkNode(keyword[13].states[0], keyword[13].transitions[0], keyword[13].states[1]);
        keyword[13].linkNode(keyword[13].states[1], keyword[13].transitions[1], keyword[13].states[2]);
        keyword[13].linkNode(keyword[13].states[2], keyword[13].transitions[2], keyword[13].states[3]);

        //KEYWORD float
        keyword[14]=new Automata(expFloat);
        keyword[14].initNode=keyword[14].states[0];
        keyword[14].componenteLexico="Palabra reservada";
        keyword[14].linkNode(keyword[14].states[0], keyword[14].transitions[0], keyword[14].states[1]);
        keyword[14].linkNode(keyword[14].states[1], keyword[14].transitions[1], keyword[14].states[2]);
        keyword[14].linkNode(keyword[14].states[2], keyword[14].transitions[2], keyword[14].states[3]);
        keyword[14].linkNode(keyword[14].states[3], keyword[14].transitions[3], keyword[14].states[4]);
        keyword[14].linkNode(keyword[14].states[4], keyword[14].transitions[4], keyword[14].states[5]);

        //KEYWORD text
        keyword[15]=new Automata(expText);
        keyword[15].initNode=keyword[15].states[0];
        keyword[15].componenteLexico="Palabra reservada";
        keyword[15].linkNode(keyword[15].states[0], keyword[15].transitions[0], keyword[15].states[1]);
        keyword[15].linkNode(keyword[15].states[1], keyword[15].transitions[1], keyword[15].states[2]);
        keyword[15].linkNode(keyword[15].states[2], keyword[15].transitions[2], keyword[15].states[3]);
        keyword[15].linkNode(keyword[15].states[3], keyword[15].transitions[3], keyword[15].states[4]);


        //IDENTIFICADOR 63
        identifier=new Automata(expId);
        identifier.initNode=identifier.states[0];
        identifier.componenteLexico="Identificador";
        identifier.linkNode(identifier.states[0],identifier.transitions[0],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[1],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[2],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[3],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[4],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[5],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[6],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[7],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[8],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[9],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[10],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[11],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[12],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[13],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[14],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[15],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[16],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[17],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[18],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[19],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[20],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[21],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[22],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[23],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[24],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[25],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[26],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[27],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[28],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[29],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[30],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[31],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[32],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[33],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[34],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[35],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[36],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[37],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[38],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[39],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[40],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[41],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[42],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[43],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[44],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[45],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[46],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[47],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[48],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[49],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[50],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[51],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[52],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[53],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[54],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[55],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[56],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[57],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[58],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[59],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[60],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[61],identifier.states[1]);
        identifier.linkNode(identifier.states[1],identifier.transitions[62],identifier.states[1]);

        //LOGIC
            //not
        opLogic[0]=new Automata(expNot);
        opLogic[0].initNode=opLogic[0].states[0];
        opLogic[0].componenteLexico="Operador Lógico";
        opLogic[0].linkNode(opLogic[0].states[0], opLogic[0].transitions[0], opLogic[0].states[1]);
            //or
        opLogic[1]=new Automata(expOr);
        opLogic[1].initNode=opLogic[1].states[0];
        opLogic[1].componenteLexico="Operador Lógico";
        opLogic[1].linkNode(opLogic[1].states[0], opLogic[1].transitions[0], opLogic[1].states[1]);
        opLogic[1].linkNode(opLogic[1].states[1], opLogic[1].transitions[1], opLogic[1].states[2]);
            //and
        opLogic[2]=new Automata(expAnd);
        opLogic[2].initNode=opLogic[2].states[0];
        opLogic[2].componenteLexico="Operador Lógico";
        opLogic[2].linkNode(opLogic[2].states[0], opLogic[2].transitions[0], opLogic[2].states[1]);
        opLogic[2].linkNode(opLogic[2].states[1], opLogic[2].transitions[1], opLogic[2].states[2]);

        //SYMBOLS
            //dollar
        symbols[0]=new Automata(expDollar);
        symbols[0].initNode=symbols[0].states[0];
        symbols[0].componenteLexico="Simbolo terminador de linea";
        symbols[0].linkNode(symbols[0].states[0], symbols[0].transitions[0], symbols[0].states[1]);
            //openbrace
        symbols[1]=new Automata(expOpenBrace);
        symbols[1].initNode=symbols[1].states[0];
        symbols[1].componenteLexico="Signo de agrupación";
        symbols[1].linkNode(symbols[1].states[0], symbols[1].transitions[0], symbols[1].states[1]);
            //closedbrace
        symbols[2]=new Automata(expClosedBrace);
        symbols[2].initNode=symbols[2].states[0];
        symbols[2].componenteLexico="Signo de agrupación";
        symbols[2].linkNode(symbols[2].states[0], symbols[2].transitions[0], symbols[2].states[1]);
            //openparenthesis
        symbols[3]=new Automata(expOpenParenthesis);
        symbols[3].initNode=symbols[3].states[0];
        symbols[3].componenteLexico="Signo de agrupación";
        symbols[3].linkNode(symbols[3].states[0], symbols[3].transitions[0], symbols[3].states[1]);
            //closedparenthesis
        symbols[4]=new Automata(expClosedParenthesis);
        symbols[4].initNode=symbols[4].states[0];
        symbols[4].componenteLexico="Signo de agrupación";
        symbols[4].linkNode(symbols[4].states[0], symbols[4].transitions[0], symbols[4].states[1]);

        //DIGITS
        digits[0]=new Automata(exp0);
        digits[0].initNode=digits[0].states[0];
        digits[0].componenteLexico="Digito";
        digits[0].linkNode(digits[0].states[0], digits[0].transitions[0], digits[0].states[1]);

        digits[1]=new Automata(exp1);
        digits[1].initNode=digits[1].states[0];
        digits[1].componenteLexico="Digito";
        digits[1].linkNode(digits[1].states[0], digits[1].transitions[0], digits[1].states[1]);

        digits[2]=new Automata(exp2);
        digits[2].initNode=digits[2].states[0];
        digits[2].componenteLexico="Digito";
        digits[2].linkNode(digits[2].states[0], digits[2].transitions[0], digits[2].states[1]);

        digits[3]=new Automata(exp3);
        digits[3].initNode=digits[3].states[0];
        digits[3].componenteLexico="Digito";
        digits[3].linkNode(digits[3].states[0], digits[3].transitions[0], digits[3].states[1]);

        digits[4]=new Automata(exp4);
        digits[4].initNode=digits[4].states[0];
        digits[4].componenteLexico="Digito";
        digits[4].linkNode(digits[4].states[0], digits[4].transitions[0], digits[4].states[1]);

        digits[5]=new Automata(exp5);
        digits[5].initNode=digits[5].states[0];
        digits[5].componenteLexico="Digito";
        digits[5].linkNode(digits[5].states[0], digits[5].transitions[0], digits[5].states[1]);

        digits[6]=new Automata(exp6);
        digits[6].initNode=digits[6].states[0];
        digits[6].componenteLexico="Digito";
        digits[6].linkNode(digits[6].states[0], digits[6].transitions[0], digits[6].states[1]);

        digits[7]=new Automata(exp7);
        digits[7].initNode=digits[7].states[0];
        digits[7].componenteLexico="Digito";
        digits[7].linkNode(digits[7].states[0], digits[7].transitions[0], digits[7].states[1]);

        digits[8]=new Automata(exp8);
        digits[8].initNode=digits[8].states[0];
        digits[8].componenteLexico="Digito";
        digits[8].linkNode(digits[8].states[0], digits[8].transitions[0], digits[8].states[1]);

        digits[9]=new Automata(exp9);
        digits[9].initNode=digits[9].states[0];
        digits[9].componenteLexico="Digito";
        digits[9].linkNode(digits[9].states[0], digits[9].transitions[0], digits[9].states[1]);

    }

}
