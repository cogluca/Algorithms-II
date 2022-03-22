    package KruskalOnGraph.utils;

    import KruskalOnGraph.datastructure.Edge;
    import KruskalOnGraph.datastructure.Graph;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Scanner;

    public class DataUtils {


        public static Collection<DataRecord> loadData(String filename) {

            Collection<DataRecord> loadedData = new ArrayList<>();
            Scanner scan = new Scanner(System.in);

            Path pathToFile = Paths.get(filename);


            // create an instance of BufferedReader
            // using try with resource, Java 7 feature to close resources
            try (BufferedReader br = Files.newBufferedReader(pathToFile,
                    StandardCharsets.UTF_8)) {

                // read the first line from the text file
                String line = br.readLine();

                // loop until all lines are read
                while (line != null) {

                    // use string.split to load a string array with the values from
                    // each line of
                    // the file, using a comma as the delimiter
                    String[] attributes = line.split(",");

                    DataRecord toInsertRecord = new DataRecord(attributes[0], attributes[1], Float.parseFloat(attributes[2]));

                    // adding book into ArrayList
                    loadedData.add(toInsertRecord);

                    // read next line before looping
                    // if end of file reached, line would be null
                    line = br.readLine();
                }

            } catch (IOException ioe) {
                ioe.getMessage();
            }

            return loadedData;
        }


        public static Graph<String, Float> loadGraph(Collection<DataRecord> dataToLoad) throws Exception {

            Graph<String,Float> loadedGraph = new Graph<>(false);

            for (DataRecord record : dataToLoad) {

                if (!loadedGraph.containsNode(record.getCityFrom())) {
                    loadedGraph.addNode(record.getCityFrom());
                }

                if (!loadedGraph.containsNode(record.getCityTo())) {
                    loadedGraph.addNode(record.getCityTo());
                }

                if (!loadedGraph.containsEdge(record.getCityFrom(), record.getCityTo()))
                    loadedGraph.addEdge(record.getCityFrom(), record.getCityTo(), record.getDistance());
            }

            return loadedGraph;

        }


        public static float getTotalForestDistance(Graph<String, Float> kruskalResult) {

            float totalDistance = 0;

            Collection<Edge<String, Float>> edges = kruskalResult.getEdges();

            for (Edge<String, Float> edge : edges) {
                totalDistance += edge.getLabel();
            }

            return totalDistance/2;
        }


    }
