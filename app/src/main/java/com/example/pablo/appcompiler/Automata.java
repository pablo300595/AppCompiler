package com.example.pablo.appcompiler;

/**
 * Created by pablo on 5/07/18.
 */

public class Automata {
    Nodo initNode;
    Nodo[] states;
    Arco[] transitions;


    public Automata(String automataData){
        String[] automataStatesTransitions=automataData.split("-");
        /*System.out.println("------------------------CREATE AUTOMATA------------------------------");

        System.out.println("automataData= "+automataData);
        System.out.println("automataStatesTransitions[0]= "+automataStatesTransitions[0]);
        System.out.println("automataStatesTransitions[1]= "+automataStatesTransitions[1]);*/

        String strAutomataStates=automataStatesTransitions[0];
        String strAutomataTransitions=automataStatesTransitions[1];
        String[] automataStates=strAutomataStates.split(",");
        String[] automataTransitions=strAutomataTransitions.split(",");
        /*System.out.println("-----------------------------------------------");
        for(int i=0;i<automataStates.length;i++){
            System.out.println("automataStates["+i+"]= "+automataStates[i]);
        }
        System.out.println("-----------------------------------------------");
        for(int i=0;i<automataTransitions.length;i++){
            System.out.println("automataTransitions["+i+"]= "+automataTransitions[i]);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("\n\n\n");*/
        Nodo[] newStates= new Nodo[automataStates.length];
        Arco[] newTransitions = new Arco[automataTransitions.length];

        for(int i=0;i<newStates.length;i++){
            if(i==0){
                newStates[i]=new Nodo(automataStates[i],false,true);
                continue;
            }

            if(automataStates[i].contains("sql")){
                newStates[i]=new Nodo(automataStates[i],true,false);
            }else{
                newStates[i]=new Nodo(automataStates[i],false,false);
            }
        }

        for(int i=0;i<newTransitions.length;i++){
            newTransitions[i]=new Arco(automataTransitions[i]);
        }

        this.states=newStates;
        this.transitions=newTransitions;
    }


    /**
     * Método que permite enlazar 2 nodos existentes y 1 arco
     * @return Nada
     */
    public void linkNode(Nodo sourceNode,Arco sourceArc,Nodo destinationNode){
        sourceNode.nextArco=sourceArc;
        sourceArc.nextNodo=destinationNode;
    }

    /**
     * Método que permite crear un nodo y definir su contenido
     * @return El nodo
     */
    public Nodo createNode(String state,boolean isInitState, boolean isFinalState){
        return new Nodo(state,isInitState,isFinalState);
    }
    /**
     * Método que permite crear un arco y definir su contenido
     * @return El arco
     */
    public Arco createArc(String letter){
        return new Arco(letter);
    }

    public void printAutomata(){
        for(Nodo apuntador=this.states[0];apuntador.nextArco!=null;apuntador=apuntador.nextArco.nextNodo){
            System.out.println("----------------------------------------------------------------");
            System.out.println("State= "+apuntador.state);
            System.out.println("Next Transition= "+apuntador.nextArco.letter);
            System.out.println("isFinalState? "+apuntador.isFinalState);
            System.out.println("isInitState? "+apuntador.isInitState);
        }
    }

    public static void main(String ar[]){
        //Automata keywordTask=new Automata();

        /*Nodo q0=keywordTask.createNode("q0",true,false);
        Nodo q1=keywordTask.createNode("q1",false,false);
        Nodo q2=keywordTask.createNode("q2",false,false);
        Nodo q3=keywordTask.createNode("q3",false,true);
        Nodo q4=keywordTask.createNode("q4",false,true);
        Arco t0=keywordTask.createArc("n");
        Arco a0=keywordTask.createArc("e");
        Arco s0=keywordTask.createArc("w");
        Arco k0=keywordTask.createArc("k");
        initNode=q0;
        keywordTask.linkNode(q0, t0, q1);
        keywordTask.linkNode(q1, a0, q2);
        keywordTask.linkNode(q2, s0, q3);
        keywordTask.linkNode(q3, k0, q4);
        printAutomata(initNode);*/

    }
}
