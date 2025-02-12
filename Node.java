package pathPlot;

public class Node {
    Node parent;
    int col,row;
    double gCost,hCost,fCost;
    boolean start,goal, obstacle,open,checked,path;

    // Constructor to initialize node properties
    public Node(int col, int row){
        this.col=col;
        this.row=row;
    }

    // Methods to set different properties of the node
    public void setAsStart(){
        start = true;
    }

    public void setAsGoal(){
        goal = true;
    }

    public void setAsObstacle(){
        obstacle = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        checked = true;
    }

    public void setAsPath(){
        path = true;
    }

}
