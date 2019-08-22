/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.LinkedList;

/**
 * This is a weighted graph
 *
 * @author Anshu
 */
public class WeightedGraph<AnyType1, AnyType2> {

    public int V;
    public LinkedList<vertex>[] adj;
    public String[] vrtxLbl;

    public WeightedGraph(int noOfVertex) {
        V = noOfVertex;

        //initialize adj list with linked list for each vertex
        adj = new LinkedList[V];
        vrtxLbl = new String[V];
        
        for (int i = 0; i < V; ++i) {
            adj[i] = new LinkedList();
        }
    }

    public void addVertex(int indx, AnyType1 lbl, AnyType2 wt) {
        //create vertex
        vertex vtx = new vertex(lbl, wt);
        //add vertex in right place
        adj[indx].add(vtx);
    }

    //__________________________________________________________________________
    /**
     * This class will acts as vertex for the graph.
     *
     * @param <AnyType1>
     * @param <AnyType2>
     */
    public class vertex {

        AnyType1 label;
        AnyType2 weight;

        vertex(AnyType1 lbl, AnyType2 wt) {
            label = lbl;
            weight = wt;
        }
    }

    //__________________________________________________________________________
}
