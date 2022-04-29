import data_utils.DataRecord;
import data_utils.DataUtils;
import datastructure.Graph;

import java.util.Collection;
import java.util.Scanner;

public class DijkstraEntrypoint {


    public static void main(String args[]) {

        Collection<DataRecord> dataFromFile;
        Graph<String, Float> graphFromData;
        Graph<String, Float> returnedForest;

        String filename;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would like to know which file you're trying to load");
        filename = scanner.nextLine();

        Dijkstra<String, Float> dijkstra = new Dijkstra<>();

        try {
            dataFromFile = DataUtils.loadData(filename);
            graphFromData = DataUtils.loadGraph(dataFromFile);
            returnedForest = dijkstra.dijkstraAlgorithm(graphFromData);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




    }







}
