/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Graph.WeightedGraph.vertex;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Anshu
 */
public class Dijkstra {

    public static void main(String[] args) {
        int noOfNodes = 5;
        WeightedGraph<Integer, Integer> wg = new WeightedGraph<Integer, Integer>(noOfNodes);

        //add labels for each graph vertex
        for (int i = 0; i < noOfNodes; i++) {
            wg.vrtxLbl[i] = "vrtx-" + Integer.toString(i);
        }
        //add adjacent vertex for each vertex in the graph..
        wg.addVertex(0, 1, 7);
        wg.addVertex(0, 2, 3);
        wg.addVertex(1, 0, 7);
        wg.addVertex(1, 2, 1);
        wg.addVertex(1, 3, 2);
        wg.addVertex(2, 0, 3);
        wg.addVertex(2, 1, 1);
        wg.addVertex(2, 3, 2);
        wg.addVertex(3, 2, 2);
        wg.addVertex(3, 1, 2);
        wg.addVertex(3, 4, 4);
        wg.addVertex(4, 1, 6);
        wg.addVertex(4, 3, 4);

        DijkstraAlgo(wg, 0,4);
    }

    /**
     *
     * @param wg
     * @param strtVrtx
     */
    public static void DijkstraAlgo(WeightedGraph wg, int strtVrtx, int destVrtx) {
        int noVrtx = wg.V;
        boolean[] visited = new boolean[noVrtx];  // track all visited nodes
        int[] prevNode = new int[noVrtx];         // track previous node for visited node
        int[] dist = new int[noVrtx];             // track distance for each vertex

        for (int i = 0; i < noVrtx; i++) {//intialize arrays for distance with max value and all vertices as unvisited   
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        dist[strtVrtx] = 0; // starting vertex will have  0 distance from itself

        for (int cnt = 0; cnt < noVrtx - 1; cnt++) {

            int min_idx = minDistance(wg, dist, visited); //get vertex with minm distance

            visited[min_idx] = true;                      // update vertex as visisted

            // Using min_idx , get all adjacent vertex 
            // Use iterator to iterate in Linkedlist for all adjacent vertex...
            Iterator i = wg.adj[min_idx].listIterator();
            while (i.hasNext()) {
                vertex vtx = (vertex) i.next();
                int lbl = (int) vtx.label;  // get index of adjacent vertex
                int dst = (int) vtx.weight; // weight
                if ((dist[min_idx] + dst) < dist[lbl]) {  // if current pasth is less than existing path
                    
                    prevNode[lbl] = min_idx;            // Update previous node
                    dist[lbl] = dist[min_idx] + dst;    //Update new distance
                      
                }

            }
        }

        
        // Print the distance related data
        System.out.println("Vertex   ||  Shortest dist. from "+wg.vrtxLbl[strtVrtx] +" || Previous Vertex");
        for(int i=0; i< noVrtx; i++){
            System.out.println(wg.vrtxLbl[i] +"         "+dist[i]+"                           "+wg.vrtxLbl[prevNode[i]] );
        }
        
        // get shortest path using stack
        Stack<Integer> stack = new Stack<Integer>();
        
        int startpoint = strtVrtx;
        int destpoint = destVrtx;
        stack.push(destpoint);  // final destination vertex loaded in stack
        int i =-1;
        while(i!=startpoint ){            
            i= prevNode[destpoint];
            destpoint = i;
            stack.push(i); // add to stack
        }
        System.out.println();
        System.out.println();
        System.out.println("Shortest path by Dijsktra algorithm:--");
        while(!stack.empty()){
            int idx = (int) stack.pop();
            System.out.println(wg.vrtxLbl[idx]);
        }
    }

    /**
     * Find vertex with minimum distance which is not yet visited
     *
     * @param indxVrtx
     * @param wg
     * @param dist
     * @param visited
     * @return
     */
    public static int minDistance(WeightedGraph wg, int[] dist, boolean[] visited) {
        int min_index = -1;         //no adjacent vetex
        int min = Integer.MAX_VALUE;

        for (int g = 0; g < dist.length; g++) {
            if (!visited[g] && dist[g] < min) {
                min = dist[g]; // new lowest distance
                min_index = g; // index with lowest value
            }
        }
        return min_index;

    }

    /* Prints the array */
    /**
     * 
     * @param arr 
     */
    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
