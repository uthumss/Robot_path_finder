package pathPlot;

public class Grid {
    int maxCol,maxRow ; // maximum number of columns and rows in the grid
    Node[][] node; //2D array to represent grid nodes
    Node startNode, goalNode, currentNode; //References to the start, goal, and current nodes
    CustomArrayList openList = new CustomArrayList(); // List to store open nodes
    RobotState robotState; // object to manage robot state
    boolean goalReached = false; // indication if the goal has been reached

    // Constructor to initialize the grid with specified dimensions
    public Grid(int maxCol, int maxRow) {
        if (maxCol<0 || maxRow<0){
            System.out.println("Please enter a positive number for the no of columns and rows");
            return;
        }
        this.maxCol =  maxCol;
        this.maxRow = maxRow;
        robotState = new RobotState(this);


        // Initialize node 2D array
        node = new Node[maxCol][maxRow];

        int col = 0;
        int row = 0;

        // Assigning a node for each cell in the Grid
        while (col < this.maxCol && row < this.maxRow) {
            node[col][row] = new Node(col, row);

            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }

    }

    // Method to generate a visual representation of the grid
    public void GridGenerator(){
        int steps=0;
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                Node current = node[col][row];

                // Display different symbols based on node properties
                if (current == startNode){
                    System.out.print("  S  "); // Start node symbol
                } else if (current == goalNode) {
                    System.out.print("  G  "); // Goal node symbol
                } else if (current.obstacle) {
                    System.out.print("  █  "); // Obstacle node symbol
                } else if (current.path) {
                    steps++;
                    System.out.print("  P  "); // Path node symbol
                } else{
                    System.out.print("  .  "); // Empty node symbol
                }
            }
            System.out.println();

        }

        // Print the number of steps if any paths are present
        if (steps !=0){
            System.out.println("No of steps: " + (steps+1));
        }
        else{
            System.out.println("No of steps: " + steps);
        }
    }

    // Method to set the start node at the specified column & row
    public void setStartNode(int col, int row) {
        if((col<maxCol && col >= 0) && (row<maxRow && row >= 0)) {
            node[col][row].setAsStart();
            startNode = node[col][row];
        }
        else{
            System.out.println("Node does not exist, select a existing node");
        }
    }

    // Method to set the goal node at the specified column & row
    public void setGoalNode(int col, int row) {
        if ((col<maxCol && col >= 0) && (row<maxRow && row >= 0)){
            node[col][row].setAsGoal();
            goalNode = node[col][row];
        }
        else{
            System.out.println("Node does not exist, select a existing node");
        }

    }

    // Method to set an obstacle node at the specified column & row
    public void setObstacleNode(int col, int row) {
        if(col<maxCol  && row<maxRow ){
                if ((node[col][row] != startNode) && (node[col][row] != goalNode)) {
                    node[col][row].setAsObstacle();
                } else {
                    System.out.println("Cannot set Start node or Goal node as an Obstacle");
                }
        }
        else{
            System.out.println("Set a valid Node as an Obstacle");
        }


    }

    // Method to calculate the heuristic and total cost
    public void generateFcost(int col,int row){
        Node currentNode = node[col][row];
        if (!currentNode.obstacle) { // Ignore obstacle nodes
            // Get G cost - distance from start node
            int xDistance = Math.abs(currentNode.col - startNode.col);
            int yDistance = Math.abs(currentNode.row - startNode.row);
            currentNode.gCost = Math.sqrt((xDistance * xDistance) + Math.sqrt(yDistance * yDistance));

            // Get H cost - distance from goal node
            xDistance = Math.abs(currentNode.col - goalNode.col);
            yDistance = Math.abs(currentNode.row - goalNode.row);
            currentNode.hCost = Math.sqrt((xDistance * xDistance) + Math.sqrt(yDistance * yDistance));

            // Get F cost - sum of H & G cost
            currentNode.fCost = currentNode.gCost + currentNode.hCost;
        }
    }


    // Method to execute the A* algorithm to find the shortest path
    public void Astar() {

        if (startNode==null || goalNode==null){
            return;
        }
        currentNode=startNode;

        while (!goalReached && currentNode != null) {
            int col = currentNode.col;
            int row = currentNode.row;

            // Mark the current node as checked, indicating it has been evaluated
            currentNode.setAsChecked();

            // Remove the current node from the open list as it has been evaluated
            openList.remove(currentNode);

            // Explore neighboring nodes in given order inside robot orientation
            robotState.orientation(col,row);

            // Find the best node
            int bestNodeIndex = 0;
            double bestNodeFcost = 10000000.0;

            for (int i=0; i< openList.size(); i++){
                if(openList.get(i).fCost < bestNodeFcost) { //check if this node's Fcost is lesser
                    bestNodeFcost = openList.get(i).fCost;
                }
                else if(openList.get(i).fCost == bestNodeFcost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;
                    }
                }
            }

            // set the best Node as currentNode
            currentNode = openList.get(bestNodeIndex);

            // Check if the goal node is reached
            if (currentNode == goalNode) {
                goalReached=true;
                trackThePath();
            }

        }
    }

    // Method to add a node to the open list
    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.obstacle) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);

        }
    }

    public void getOpenNode(int col,int row){
        openNode(node[col][row]);
    }

    // Method to backtrack and mark the path from goal to start node
    private void trackThePath() {

        // Backtrack and draw the best path
        Node current = goalNode; // Start from the goal Node

        while (current != startNode) { // Until Start Node is reached
            current = current.parent;  // Keep marking the parent of the current node as the path

            if (current != startNode) {
                current.setAsPath();
            }
        }
    }
}

