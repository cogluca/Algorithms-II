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

        /**TODO LETTURA CON SCANNER
        //Scanner scanner = new Scanner(System.in);
        //filename = scanner.nextLine();
        //beginner = scanner.nextLine();
        **/

        //System.out.println("Would like to know which file you're trying to load");

        /**TODO CON LETTURA DA ARGS, RICORDARE GLI ARG NEL LAUNCH
        filename = args[0];
        //System.out.println("What city would you like to make your search start from ?");
        beginnerCity = args[1];
        **/

        /**TODO LETTURA CON BUFFERED READER
         * BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         * filename = reader.readLine();
         * beginnerCity = reader.readLine();
         *
         */





        Dijkstra<String,Float,?> dijkstra = new Dijkstra<>();

        try {

            dataFromFile = DataUtils.loadData("HARDCODA");
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
