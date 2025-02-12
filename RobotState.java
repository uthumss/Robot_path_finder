package pathPlot;

public class RobotState {
    private Grid grid;

    // Constructor to initialize robot state with the grid
    public RobotState(Grid grid){
        this.grid=grid;
    }

    // Methods to move the robot in different directions and add neighboring nodes to the open list
    public void moveUp(int col, int row) {
        if (row - 1 >= 0) {
            grid.getOpenNode(col, row - 1);
            grid.generateFcost(col, row - 1);
        }
    }

    public void moveUpRight(int col, int row) {
        if (row - 1 >= 0) {
            if (col + 1 < grid.maxCol){
                grid.getOpenNode(col + 1,row - 1);
                grid.generateFcost(col + 1,row - 1);
            }

        }
    }

    public void moveUpLeft(int col, int row) {
        if (col - 1 >= 0) {
            if (row - 1 >=0){
                grid.getOpenNode(col - 1,row - 1);
                grid.generateFcost(col - 1,row - 1);
            }

        }
    }

    public void moveLeft(int col, int row) {
        if (col - 1 >= 0) {
            grid.getOpenNode(col -1, row );
            grid.generateFcost(col -1, row );
        }
    }

    public void moveRight(int col, int row) {
        if (col + 1 < grid.maxCol) {
            grid.getOpenNode(col +1, row);
            grid.generateFcost(col +1, row);
        }
    }

    public void moveDown(int col, int row) {
        if (row +1 < grid.maxRow) {
            grid.getOpenNode(col, row + 1);
            grid.generateFcost(col, row + 1);
        }
    }

    public void moveDownLeft(int col, int row) {
        if (row + 1 < grid.maxRow) {
            if (col - 1 >= 0) {
                grid.getOpenNode(col - 1, row + 1);
                grid.generateFcost(col - 1, row + 1);
            }
        }
    }

    public void moveDownRight(int col, int row) {
        if (row + 1 < grid.maxRow) {
            if (col + 1 < grid.maxCol) {
                grid.getOpenNode(col +1, row + 1);
                grid.generateFcost(col +1, row + 1);
            }
        }
    }

    // The order in which the robot orientation would happen
    public void orientation(int col, int row){
        // Order in which the neighboring nodes of the current mode is added to the open List
        moveRight(col, row);
        moveDown(col, row);
        moveUp(col, row);
        moveLeft(col, row);
        moveDownRight(col, row);
        moveUpRight(col, row);
        moveUpLeft(col, row);
        moveDownLeft(col, row);

    }
}
