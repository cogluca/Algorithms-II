# Dijkstra algorithm applied on a nondirected graph

## Generalities
This exercises revolves around finding the shortest path from a specific location to the other 
ones listed in the dataset, for such purpose I organized the dataset as a graph by loading the entries with a file reader.

The graph produced by the reader gets fed onto the algorithm entrypoint, a specific node get's chosen from command line to initiate search from and the algorithm runs.

The procedure follows as such:
- Nodes from graph get loaded onto the priority queue
- Entrypoint node distance gets marked as 0
- The minimum, therefore entrypoint gets extracted for exploration
- The node's frontier gets expanded, meaning the adjacent node distances are relaxed by calculating the edge connecting them
plus the node's distance that they come from
- If among those nodes we find one that is the shortest path we explore that one, otherwise the minimum extraction from queue
selects a previous connection stemming from another node
- Algorithm finishes when all reachable nodes are explored
- If we care for the distance from the entry point node to another specific one we extract the latter from a simple list with explored nodes and read it's distance

## Some mentions to the Algorithm invention
According to Dijkstra it was a 20 minute invention while taking a coffee with his fiance.

His original intentions were to display a solution used on the ARMAC computer system but he had the problem of explaning the solution in layman terms

So he modularized the algorithm to find the distance between two cities in the Netherlands.

## Usage of Dijkstra
Dijkstra's algorithm has many usages in modern systems for determining the shortest path between nodes in a directed or non directed graph. The important part is that these costs of travel or edge weights
need to be non negative.

Common usages are found in:

- Google Maps
- Social networks (e.g determining the degree of connection to another member of the network, as with LinkedIn)
- IP Routing (e.g used to update forwarding tables of a router)
- Flight calculators (e.g finding the path taking the least amount of flights or costs)

## Used data structures

### Node element consisting of: 
- a value
- an HashMap referencing the adjacent nodes and the connecting edges

### Edge element consisting of:
- Edge weight
- First node from which edge starts
- Second node onto which edge ends

### Graph itself consisting of:
- An HashMap with Node value as key and the node itself as value
- The edge's are embedded into nodes to display adjacency

### Min Heap used as Priority Queue
- Consisting of an Arraylist with the elements
- An HashMap mapping element and it's index as key-value pairs for O(logn)retrieval


## Full Description of dataset

The dataset is organized in rows displaying a location, another location and the distance between those two expressed in metres

Localities : locations around italy all consisting of non-null values
Size: 56640 rows

The loading of the dataset produces 18640 nodes and the number of edges is consistent with the number of rows if the graph is directed otherwise the number of edges is doubled

## Results

Result explores 18640 nodes, by "explored" we mean extracted from the heap and leaves 4 localities untouched, possible cause is their unconnectedness to the rest of the graph.

Taking Torino as an entrypoint and calculating its distance to Catania yields a distance of 1207.738 Km, a value in line with the one provided by the professors




