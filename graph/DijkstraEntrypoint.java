import data_utils.DataRecord;
import data_utils.DataUtils;
import datastructure.Graph;
import datastructure.Node;

import java.util.Collection;
import java.util.Scanner;

public class DijkstraEntrypoint {


    public static void main(String args[]) {

        Collection<DataRecord> dataFromFile;
        Graph<String, Float> returnedForest;
        Graph<String, Float> graphFromData;

        String filename;
        String beginnerCity;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would like to know which file you're trying to load");
        filename = scanner.nextLine();

        System.out.println("What city would you like to make your search start from ?");
        beginnerCity = scanner.nextLine();




        Dijkstra<String,Float,?> dijkstra = new Dijkstra<>();

        try {
            dataFromFile = DataUtils.loadData(filename);
            graphFromData = DataUtils.loadGraph(dataFromFile);

            System.out.println(graphFromData.graphNodeSize());

            returnedForest = dijkstra.dijkstraAlgorithm(graphFromData,beginnerCity, Float.class);

            System.out.println(returnedForest.graphNodeSize());

            //System.out.println(returnedForest.getSpecificNode("catania").getFloatDistance());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }







}
