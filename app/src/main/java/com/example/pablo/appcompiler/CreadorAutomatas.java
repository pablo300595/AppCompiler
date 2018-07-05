package com.example.pablo.appcompiler;

/**
 * Created by pablo on 5/07/18.
 */

public class CreadorAutomatas {
    static Automata keyword[];
    static Automata lang;
    static String expTask="q0,q1,q2,q3,q4 sql-t,a,s,k";
    static String expNew="q0,q1,q2,q3 sql-n,e,w";
    static String language="q0,q1 sql-a,b";


    public CreadorAutomatas(){
        keyword=new Automata[2];
        createAllAutomatas();
    }

    public void createAllAutomatas(){
        //KEYWORD tasky
        keyword[0]=new Automata(expTask);
        keyword[0].initNode=keyword[0].states[0];
        keyword[0].linkNode(keyword[0].states[0], keyword[0].transitions[0], keyword[0].states[1]);
        keyword[0].linkNode(keyword[0].states[1], keyword[0].transitions[1], keyword[0].states[2]);
        keyword[0].linkNode(keyword[0].states[2], keyword[0].transitions[2], keyword[0].states[3]);
        keyword[0].linkNode(keyword[0].states[3], keyword[0].transitions[3], keyword[0].states[4]);

        //KEYWORD new
        keyword[1]=new Automata(expNew);
        keyword[1].initNode=keyword[1].states[0];
        keyword[1].linkNode(keyword[1].states[0], keyword[1].transitions[0], keyword[1].states[1]);
        keyword[1].linkNode(keyword[1].states[1], keyword[1].transitions[1], keyword[1].states[2]);
        keyword[1].linkNode(keyword[1].states[2], keyword[1].transitions[2], keyword[1].states[3]);
        //keyword[1].printAutomata();
        //Language ab*
        lang=new Automata(language);
        lang.initNode=lang.states[0];
        lang.linkNode(lang.states[0], lang.transitions[0], lang.states[1]);
        lang.linkNode(lang.states[1], lang.transitions[1], lang.states[1]);

        lang.printAutomata();
    }

    public static void main(String ar[]){

        CreadorAutomatas validador=new CreadorAutomatas();


    }
}
