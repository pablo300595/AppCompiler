package com.example.pablo.appcompiler;

/**
 * Created by pablo on 5/07/18.
 */
//Establece el esqueleto de un autómata en base a los nodos y arcos
public class Automata {
    Nodo initNode;
    Nodo[] states;
    Arco[] transitions;
    String componenteLexico;

    /**
     * Constructor que permite crear un autómata en base a una cadena de
     * entrada, no crea relaciones, solo crea todos los objetos de tipo
     * nodo y arco que lo conforman. Estos se guardan en los arreglos:
     * states y transitions.
     * @return El autómata
     */
    public Automata(String automataData){
        int ocurrencias=0,ultimaPos=0;
        String pointer="";
        String[] automataStatesTransitions;

        ultimaPos=automataData.indexOf("-")+1;
        ocurrencias++;
        pointer=automataData.substring(ultimaPos);
        if(pointer.indexOf("-")!=-1){
            ocurrencias++;
        }

        if(ocurrencias>1){
            automataStatesTransitions=automataData.split("#");
            componenteLexico="No definido";
        }else{
            automataStatesTransitions=automataData.split("-");
            componenteLexico="No definido";
        }

        /*System.out.println("------------------------CREATE AUTOMATA------------------------------");
            ----CODIGO DE PRUEBA PARA VERIFICAR UN ERROR, YA HA SIDO CORREGIDO-----
        System.out.println("automataData= "+automataData);
        System.out.println("automataStatesTransitions[0]= "+automataStatesTransitions[0]);
        System.out.println("automataStatesTransitions[1]= "+automataStatesTransitions[1]);*/

        String strAutomataStates=automataStatesTransitions[0];
        String strAutomataTransitions=automataStatesTransitions[1];
        String[] automataStates;
        String[] automataTransitions;
        if(ocurrencias>1){
            automataStates=strAutomataStates.split(":");
            automataTransitions=strAutomataTransitions.split(":");
        }else{
            automataStates=strAutomataStates.split(",");
            automataTransitions=strAutomataTransitions.split(",");
        }

        /*System.out.println("-----------------------------------------------");
        for(int i=0;i<automataStates.length;i++){
            System.out.println("automataStates["+i+"]= "+automataStates[i]);
        }
        System.out.println("-----------------------------------------------");
        for(int i=0;i<automataTransitions.length;i++){
            System.out.println("automataTransitions["+i+"]= "+automataTransitions[i]);
        }
        ----CODIGO DE PRUEBA PARA VERIFICAR UN ERROR, YA HA SIDO CORREGIDO-----
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
        sourceNode.createNextArco(sourceArc);
        sourceArc.createNextNodo(destinationNode);
        //sourceNode.nextArco=sourceArc; CODIGO VIEJO ANTES DE CAMBIO DE INGENIERÍA
        //sourceArc.nextNodo=destinationNode;
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

    /**
     * Método que permite imprimir el contenido de un Automata en consola
     * @return Impresión del automata en consola
     */
    public void printAutomata(){

        for(int i=0;i<states.length;i++){
            System.out.println("---------------------"+componenteLexico+"---------------------------");
            System.out.println("State=> "+states[i].state);
            System.out.println("init?=> "+states[i].isInitState);
            System.out.println("final?=> "+states[i].isFinalState);
            for(int j=0;j<states[i].nextArco.size();j++){
                System.out.println("transition=> "+states[i].nextArco.get(j).letter);
            }
            System.out.println("------------------------------------------------------");
        }
    }
}
