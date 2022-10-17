//Jarod Gomberg
//April 1, 2022
//COP3503
//RP4 - Kattis - Shortest path 2

import java.util.*;

/*
    Solution to Kattis problem Single source shortest path, time table.
      For each query, output a single line containing the minimum time to reach the node queried,
        assuming we start in node s at time 0,
      Output "Impossible" if there is no path from s to that node.
*/

public class shortestpath2 {

  public static void main(String[] args) {

    Scanner stdin = new Scanner(System.in);

    //Process all test cases
    while(stdin.hasNext()) {
      int n, m, q, s;

      //End if no more cases to solve
      n = stdin.nextInt();
      if(n == 0)
        break;

      //Get all necessary data
      m = stdin.nextInt();
      q = stdin.nextInt();
      s = stdin.nextInt();

      //Create array of type node for all nodes in graph
      Node[] nodes = new Node[n];
      for(int i = 0; i < n; i++) {
        nodes[i] = new Node(i);
      }

      //Loop over edges and process all data to store them in adjacency list
      for(int i = 0; i < m; i++) {
        int u = stdin.nextInt();
        int v = stdin.nextInt();
        int to = stdin.nextInt();
        int p = stdin.nextInt();
        int d = stdin.nextInt();

        nodes[u].adj.add(new Edge(nodes[v], d, to, p));
      }

      //Solve using modified dijkstra's algorithm to incorporate time table
      dijkstras(nodes[s]);

      //Process the queries and obtain shortest paths
      for(int i = 0; i < q; i++) {
        int query = stdin.nextInt();

        int shortest = nodes[query].dist;

        //Print according to output specifications
        if(shortest == Integer.MAX_VALUE) {
          System.out.println("Impossible");
        }
        else {
          System.out.println(shortest);
        }
      }
    }
  }

  //Method that applies dijkstra's algorithm to solve shortest path problem
  public static void dijkstras(Node source) {

    //initialize start node distance to 0
    source.dist = 0;

    //custom comparator to return node with shorter distance
    Comparator<Node> comparePoints = new Comparator<Node>() {
      @Override
      public int compare(Node a, Node b) {
        return Integer.compare(a.dist, b.dist);
      }
    };

    //Declare and initialize Queue. Add the source node
    PriorityQueue<Node> pq = new PriorityQueue<>(comparePoints);
    pq.add(source);

    //Now process all nodes and prioritize those with shortest distance
    while(!pq.isEmpty()) {
      Node u = pq.poll();

      //Process all edges an continuously check distance/time of each
      for (Edge e: u.adj) {

        int distance = e.to + e.weight;

        if(u.dist > e.to) {
          if(e.p == 0) { //Can't get here
            distance = Integer.MAX_VALUE;
          }
          else { //So get the next time we can go through path
            int path = 1 + (((u.dist - e.to)-1) / e.p);
            distance = e.to + path * e.p + e.weight;
          }
        }

        //upate queue for each node 
        Node v = e.target;
        if(distance < v.dist) {
          pq.remove(v);
          v.dist = distance;
          v.prev = u;
          pq.add(v);
        }
      }
    }
  }
}

//edge class to get edges in graph
class Edge {
  //Stores edge points and weight of connection
  public Node target;
  public int weight;
  public int to;
  public int p;

  public Edge(Node target, int weight, int to, int p) {
    this.target = target;
    this.weight = weight;
    this.to = to;
    this.p = p;
  }
}

//Node class for node object
class Node {
  //gets and stores all data for given point/node
  //And makes list of adjacent nodes as well as distance between and previous nodes
  public int index;
  public Vector<Edge> adj;
  public int dist;
  public Node prev;

  public Node(int index) {
    this.index = index;
    this.adj = new Vector<>();
    this.dist = Integer.MAX_VALUE;
    this.prev = null;
  }
}
