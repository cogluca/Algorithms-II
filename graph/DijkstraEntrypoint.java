import data_utils.DataRecord;
import data_utils.DataUtils;
import datastructure.Graph;
import datastructure.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class DijkstraEntrypoint {


    public static void main(String args[]) {

        Collection<DataRecord> dataFromFile;
        List<Node<String,Float>> returnedTree;
        Graph<String, Float> graphFromData;

        String filename;
        String beginnerCity;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would like to know which file you're trying to load");

        //filename = scanner.nextLine();
        System.out.println("What city would you like to make your search start from ?");

        //beginnerCity = scanner.nextLine();
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

            //System.out.println(returnedForest.getSpecificNode("catania").getFloatDistance());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }








}
