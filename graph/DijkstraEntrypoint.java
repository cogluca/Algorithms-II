import data_utils.DataRecord;
import data_utils.DataUtils;
import datastructure.Graph;
import datastructure.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class DijkstraEntrypoint {


    public static void main(String[] args) {

        Collection<DataRecord> dataFromFile;
        List<Node<String,Float>> returnedTree;
        Graph<String, Float> graphFromData;

        String filename;
        String beginnerCity;


        Dijkstra<String,Float,?> dijkstra = new Dijkstra<>();

        try {

            dataFromFile = DataUtils.loadData("/Users/frankacarkan/Desktop/Algo/ex1_new/italian_dist_graph.csv");
            graphFromData = DataUtils.loadGraph(dataFromFile);

            returnedTree = dijkstra.dijkstraAlgorithm(graphFromData,"torino", Float.class);


            for(Node<String,Float> toSearch: returnedTree){

                if(toSearch.getValue().equals("catania")){
                    System.out.print("Distance to Catania from Torino is: " + toSearch.getFloatDistance());
                }

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }








}
